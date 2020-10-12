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

import com.example.demo.entity.TrackSegment;
import com.example.demo.entity.Waypoint;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.TrksegType;
import com.example.demo.xml.element.WptType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class TrackSegmentMapper {

	@Autowired
	private WaypointMapper waypointMapper;

	public TrackSegment toWaypointEntity(TrksegType trksegType) {
		if (trksegType == null) {
			return null;
		}

		TrackSegment trackSegment = new TrackSegment();

		trackSegment.setTrackPoints(wptTypeListToWaypointList(trksegType.getTrkpt()));
		trackSegment.setExtensions(ConvertUtil.convertObjectToJson(trksegType.getExtensions()));

		return trackSegment;
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
}
