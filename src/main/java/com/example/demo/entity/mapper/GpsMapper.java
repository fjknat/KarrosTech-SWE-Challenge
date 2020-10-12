/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Gps;
import com.example.demo.entity.Route;
import com.example.demo.entity.Track;
import com.example.demo.entity.Waypoint;
import com.example.demo.xml.element.GpxType;
import com.example.demo.xml.element.RteType;
import com.example.demo.xml.element.TrkType;
import com.example.demo.xml.element.WptType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class GpsMapper {
	@Autowired
	private WaypointMapper waypointMapper;
	@Autowired
	private TrackMapper trackMapper;
	@Autowired
	private RouteMapper routeMapper;
	@Autowired
	private MetadataMapper metadataMapper;

	public Gps toGpsEntity(GpxType gpxType) {
		if (gpxType == null) {
			return null;
		}

		Gps gps = new Gps();

		gps.setWaypoints(wptTypeListToWaypointList(gpxType.getWpt()));
		gps.setTracks(trkTypeListToTrackList(gpxType.getTrk()));
		gps.setRoutes(rteTypeListToRouteList(gpxType.getRte()));
		gps.setVersion(gpxType.getVersion());
		gps.setCreator(gpxType.getCreator());
		gps.setMetadata(metadataMapper.toMetadateEntity(gpxType.getMetadata()));

		return gps;
	}

	protected List<Waypoint> wptTypeListToWaypointList(List<WptType> list) {
		if (list == null) {
			return null;
		}

		List<Waypoint> list1 = new ArrayList<Waypoint>(list.size());
		for (WptType wptType : list) {
			list1.add(waypointMapper.toWaypointEntity(wptType));
		}

		return list1;
	}

	protected List<Track> trkTypeListToTrackList(List<TrkType> list) {
		if (list == null) {
			return null;
		}

		List<Track> list1 = new ArrayList<Track>(list.size());
		for (TrkType trkType : list) {
			list1.add(trackMapper.toTrackEntity(trkType));
		}

		return list1;
	}

	protected List<Route> rteTypeListToRouteList(List<RteType> list) {
		if (list == null) {
			return null;
		}

		List<Route> list1 = new ArrayList<Route>(list.size());
		for (RteType rteType : list) {
			list1.add(routeMapper.toRouteEntity(rteType));
		}

		return list1;
	}

}
