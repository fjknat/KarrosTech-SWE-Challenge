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

import com.example.demo.entity.Link;
import com.example.demo.entity.Track;
import com.example.demo.entity.TrackSegment;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.LinkType;
import com.example.demo.xml.element.TrkType;
import com.example.demo.xml.element.TrksegType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class TrackMapper {

	@Autowired
	private TrackSegmentMapper trackSegmentMapper;
	@Autowired
	private LinkMapper linkMapper;

	public Track toTrackEntity(TrkType trkType) {
		if (trkType == null) {
			return null;
		}

		Track track = new Track();

		track.setLinks(linkTypeListToLinkList(trkType.getLink()));
		track.setTrackSegments(trksegTypeListToTrackSegmentList(trkType.getTrkseg()));
		track.setName(trkType.getName());
		track.setCmt(trkType.getCmt());
		track.setDesc(trkType.getDesc());
		track.setSrc(trkType.getSrc());
		track.setNumber(trkType.getNumber());
		track.setType(trkType.getType());
		track.setExtensions(ConvertUtil.convertObjectToJson(trkType.getExtensions()));

		return track;
	}

	protected List<Link> linkTypeListToLinkList(List<LinkType> list) {
		if (list == null) {
			return null;
		}

		List<Link> list1 = new ArrayList<Link>(list.size());
		for (LinkType linkType : list) {
			list1.add(linkMapper.linkTypeToLink(linkType));
		}

		return list1;
	}

	protected List<TrackSegment> trksegTypeListToTrackSegmentList(List<TrksegType> list) {
		if (list == null) {
			return null;
		}

		List<TrackSegment> list1 = new ArrayList<TrackSegment>(list.size());
		for (TrksegType trksegType : list) {
			list1.add(trackSegmentMapper.toWaypointEntity(trksegType));
		}

		return list1;
	}
}
