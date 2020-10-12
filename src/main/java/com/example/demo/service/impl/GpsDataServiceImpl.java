/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoApplication;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.config.SystemConstant;
import com.example.demo.controller.dto.TrkDTO;
import com.example.demo.controller.dto.TrksegDTO;
import com.example.demo.controller.dto.WptDTO;
import com.example.demo.controller.request.LatestListRequest;
import com.example.demo.controller.request.UploadGpsFileRequest;
import com.example.demo.controller.response.LatestListResponse;
import com.example.demo.controller.response.UploadGpsFileResponse;
import com.example.demo.entity.Gps;
import com.example.demo.entity.Link;
import com.example.demo.entity.TrackInfo;
import com.example.demo.entity.User;
import com.example.demo.entity.Waypoint;
import com.example.demo.entity.mapper.GpsMapper;
import com.example.demo.entity.mapper.WaypointMapper;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.repository.GpsRepository;
import com.example.demo.repository.LinkRepository;
import com.example.demo.repository.RouteRepository;
import com.example.demo.repository.TrackInfoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WaypointRepository;
import com.example.demo.service.GpsDataService;
import com.example.demo.service.MetadataService;
import com.example.demo.service.TrackInfoService;
import com.example.demo.xml.element.GpxType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Service
public class GpsDataServiceImpl extends CrudServiceImpl<Gps, Integer, GpsRepository> implements GpsDataService{

	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	
	
	@Autowired
	private GpsMapper gpsMapper;
	
	@Autowired
	private WaypointMapper waypointMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TrackInfoService trackInfoService;
	
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
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@CacheEvict(cacheNames = "gps", allEntries = true)
	public UploadGpsFileResponse uploadGpsData(GpxType gpxType, UploadGpsFileRequest request, String rawGpsFile) {
		UploadGpsFileResponse uploadGpsFileResponse ;
		try {
			Optional<User> user = userRepository.findByUserName(request.getUserName());
			if (!user.isPresent()) {
				user = Optional.of(new User());
				user.get().setUserName(request.getUserName());
				user.get().setFullName(StringUtils.substringBefore(request.getUserName(), "@"));
				user = Optional.ofNullable(userRepository.save(user.get()));
			}
			Gps gps = gpsMapper.toGpsEntity(gpxType);

			TrackInfo trackInfo = trackInfoService.createTrackInfo(request.getTrackTitle(), user.get(), gps, gpxType, rawGpsFile);
			gps.setTrackInfo(trackInfo);
			reloadGps(gps);
			Gps newGps = this.add(gps);
			newGps.setMetadata(metadataService.createContentOfMetadata(newGps.getMetadata()));
			newGps.setWaypoints(waypointRepository.saveAll(newGps.getWaypoints()));
			newGps.setRoutes(routeRepository.saveAll(newGps.getRoutes()));			
			newGps = insertLinkDataOfRoute(gps);

			uploadGpsFileResponse = UploadGpsFileResponse.builder()
					.status(SystemConstant.RESPONSE_STATUS_OK)
					.trackInfoId(trackInfo.getId())
					.build();
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
		return uploadGpsFileResponse;
	}

	@Override
	@Cacheable(cacheNames = "gps", key = "#latestListRequest.pageSize.toString() + #latestListRequest.pageIndex.toString() + (#latestListRequest.userName!=null?#latestListRequest.userName:'')")
	public List<LatestListResponse> loadLatestTracks(LatestListRequest latestListRequest) {
		List<LatestListResponse> latestListResponses = new ArrayList<LatestListResponse>();
		
		try {
			Pageable pageable = trackInfoService.createPageRequest(latestListRequest.getPageIndex(),
					latestListRequest.getPageSize(), new String[] { "datePost" }, "DESC");
			List<TrackInfo> trackInfos = trackInfoRepository
					.getLatestListTrackInfo(pageable, latestListRequest.getUserName()).getContent();
			latestListResponses = trackInfos.stream().map(trackInfo -> {
				Optional<Gps> gps = gpsRepository.findByTrackInfoIdSQL(trackInfo.getId());

				List<TrkDTO> trkDTOs = gps.get().getTracks().stream().map(track -> {

					List<TrksegDTO> trksegDTOs = track.getTrackSegments().stream().map(trackSeg -> {
						TrksegDTO trksegDTO = new TrksegDTO();
						List<WptDTO> trkpt = trackSeg.getTrackPoints().stream().map(trackPoint -> {
							WptDTO wptDTO = waypointMapper.toWptDTO(trackPoint);
							
							return wptDTO;
						}).collect(Collectors.toList());
						trksegDTO.setTrkpt(trkpt);
						return trksegDTO;
					}).collect(Collectors.toList());
					return TrkDTO.builder().number(track.getNumber()).type(track.getType()).trkseg(trksegDTOs).build();
				}).collect(Collectors.toList());

				return LatestListResponse.builder()
						.userName(trackInfo.getUser().getFullName())
						.createdDate(trackInfo.getDatePost())
						.trackDesc(trackInfo.getDescription())
						.trackId(trackInfo.getId())
						.trackTitle(trackInfo.getTitle())
						.trk(trkDTOs).build();
			}).collect(Collectors.toList());
			return latestListResponses;
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
	}

	@Override
	protected String getDefaultColumnSort() {
		return "id";
	}
	
	private void reloadGps(Gps gps) {
		gps = reloadMetadataValue(gps);				
		gps = reloadTrackData(gps);
		gps = reloadWayPointData(gps);		
		gps = reloadRouteData(gps);
		
	}
	
	private Gps reloadMetadataValue(Gps gps) {
		if (gps.getMetadata() != null) {
			gps.getMetadata().setGps(gps);

			if (gps.getMetadata().getLinks() != null) {
				gps.getMetadata().getLinks().forEach(link -> link.setMetadata(gps.getMetadata()));
			}
		}
		
		return gps;
	}
	
	private Gps reloadTrackData(Gps gps) {
		if (gps.getTracks() != null) {
			gps.getTracks().forEach(track -> {
				track.setGps(gps);
				if (track.getTrackSegments() != null) {
					track.getTrackSegments().forEach(trackSeg -> {
						trackSeg.setTrack(track);
						if (trackSeg.getTrackPoints() != null) {
							trackSeg.getTrackPoints().forEach(trackPoint -> {
								trackPoint.setTrackSegment(trackSeg);
								if (trackPoint.getLinks() != null) {
									trackPoint.getLinks().forEach(linkTP -> linkTP.setWaypoint(trackPoint));
								}
							});
						}
					});
				}

				List<Link> links = track.getLinks();
				if (links != null && links.size() > 0) {
					links.forEach(link -> {
						link.setTrack(track);						
					});
				}
			});
		}
		return gps;
	}
	
	private Gps reloadWayPointData(Gps gps) {
		if (gps.getWaypoints() != null) {
			gps.getWaypoints().forEach(waypoint -> {
				waypoint.setGps(gps);
				
				if(waypoint.getLinks() != null) {
					waypoint.getLinks().forEach(linkWPT -> linkWPT.setWaypoint(waypoint));
				}
			});
		}
		return gps;
	}
	
	private Gps reloadRouteData(Gps gps) {
		if(gps.getRoutes() != null) {
			gps.getRoutes().forEach(router -> {
				router.setGps(gps);
				List<Waypoint> waypoints = router.getRoutePoints();
				if(waypoints != null) {
					waypoints.forEach(routerPoint -> routerPoint.setRoute(router));
				}				
			});
		}
		return gps;
	}
	
	private Gps insertLinkDataOfRoute(Gps gps) {
		if(gps.getRoutes() != null) {
			gps.getRoutes().forEach(router -> {				
				List<Link> links = router.getLinks();
				if (links != null && links.size() > 0) {
					links.forEach(link -> {
						link.setRoute(router);
						linkRepository.save(link);
					});
				}
			});
		}
		return gps;
	}	

}
