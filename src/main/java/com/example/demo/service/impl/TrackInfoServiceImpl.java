/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.DemoApplication;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.config.SystemConstant;
import com.example.demo.controller.request.TrackDetailRequest;
import com.example.demo.controller.response.TrackDetailResponse;
import com.example.demo.entity.Bound;
import com.example.demo.entity.Copyright;
import com.example.demo.entity.Gps;
import com.example.demo.entity.Person;
import com.example.demo.entity.Route;
import com.example.demo.entity.TrackInfo;
import com.example.demo.entity.User;
import com.example.demo.entity.Waypoint;
import com.example.demo.entity.mapper.GpsMapper;
import com.example.demo.entity.mapper.WaypointMapper;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.repository.BoundRepository;
import com.example.demo.repository.CopyrightRepository;
import com.example.demo.repository.GpsRepository;
import com.example.demo.repository.LinkRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RouteRepository;
import com.example.demo.repository.TrackInfoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WaypointRepository;
import com.example.demo.service.MetadataService;
import com.example.demo.service.TrackInfoService;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.GpxType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Service
public class TrackInfoServiceImpl extends CrudServiceImpl<TrackInfo, Integer, TrackInfoRepository> implements TrackInfoService{
	
	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	MetadataService metadataService;
	
	@Autowired
	LinkRepository linkRepository;
	
	@Autowired
	WaypointRepository waypointRepository;
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	TrackInfoRepository trackInfoRepository;
	
	@Autowired
	GpsRepository gpsRepository;
	
	@Autowired
	CopyrightRepository copyrightRepository;
	
	@Autowired
	BoundRepository boundRepository;
	
	@Override
	public TrackInfo createTrackInfo(String title, User user, Gps gps, GpxType gpxType, String rawGpsFile) {
		String timeZone = SystemConstant.UTC_TIMEZONE_VALUE;
		TrackInfo newTrackInfo = null;
		try {
			try {
				timeZone = ConvertUtil
						.getTimeZoneCode(gpxType.getTrk().get(0).getTrkseg().get(0).getTrkpt().get(0).getTime());
			} catch (NullPointerException ex) {
				if (gpxType.getWpt() != null && gpxType.getWpt().size() > 0) {
					timeZone = ConvertUtil.getTimeZoneCode(gpxType.getWpt().get(0).getTime());
				}
			}

			TrackInfo trackInfo = new TrackInfo();
			trackInfo.setGps(gps);
			trackInfo.setTimeZone(timeZone);
			trackInfo.setTitle(title);
			trackInfo.setDescription(title);
			trackInfo.setUser(user);
			trackInfo.setRawFile(rawGpsFile);
			trackInfo.setDatePost(Instant.now());
			newTrackInfo = this.add(trackInfo);
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
		return newTrackInfo;
	}

	@Override
	protected String getDefaultColumnSort() {
		return "id";
	}

	@Override
	@Cacheable(cacheNames = "gpsdetail", key = "#trackDetailRequest.trackId.toString()", unless = "#result == null")
	public TrackDetailResponse getTrackDetail(TrackDetailRequest trackDetailRequest) {
		TrackDetailResponse trackDetailResponse = null;
		try {
			
			Optional<TrackInfo> trackInfo = trackInfoRepository.getWithUserByTrackInfoId(trackDetailRequest.getTrackId());
			if(trackInfo.isPresent()) {
				Optional<Gps> gps = null;
				gps = gpsRepository.findByTrackInfoIdSQL(trackDetailRequest.getTrackId());
				
				if(gps.isPresent()) {
					
					List<Waypoint> waypoints = waypointRepository.getByGpsId(gps.get().getId());
					gps.get().setWaypoints(waypoints);
					
					List<Route> routes = routeRepository.getByGpsId(gps.get().getId());
					gps.get().setRoutes(routes);
					
					//perfomance ?
					if (gps.get().getRoutes() != null) {
						gps.get().getRoutes().forEach(r -> {
							r.setLinks(linkRepository.getByRouteId(r.getId()));
						});
					}
					
					if(gps.get().getMetadata() != null) {
						Person person = personRepository.getByMetadataId(gps.get().getMetadata().getId());
						gps.get().getMetadata().setPerson(person);
						
						Copyright copyright = copyrightRepository.getByMetadataId(gps.get().getMetadata().getId());
						gps.get().getMetadata().setCopyright(copyright);
						
						Bound bound = boundRepository.getByMetadataId(gps.get().getMetadata().getId());
						gps.get().getMetadata().setBound(bound);
					}
					
					
					
					trackDetailResponse = TrackDetailResponse.builder()
							.userName(trackInfo.get().getUser().getFullName())
							.createdDate(trackInfo.get().getDatePost())
							.trackDesc(trackInfo.get().getDescription())
							.trackName(trackInfo.get().getTitle())
							.trackTitle(trackInfo.get().getTitle())
							.trk(gps.get()).build();
				}
			}
		
		} catch (PersistenceException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
		return trackDetailResponse;
	}

}
