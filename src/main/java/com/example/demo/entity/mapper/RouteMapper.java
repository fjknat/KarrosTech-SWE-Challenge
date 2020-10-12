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
import com.example.demo.entity.Route;
import com.example.demo.entity.Waypoint;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.LinkType;
import com.example.demo.xml.element.RteType;
import com.example.demo.xml.element.WptType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class RouteMapper {

	@Autowired
	private WaypointMapper waypointMapper;
	@Autowired
	private LinkMapper linkMapper;

	public Route toRouteEntity(RteType rteType) {
		if (rteType == null) {
			return null;
		}

		Route route = new Route();

		route.setRoutePoints(wptTypeListToWaypointList(rteType.getRtept()));
		route.setLinks(linkTypeListToLinkList(rteType.getLink()));
		route.setName(rteType.getName());
		route.setCmt(rteType.getCmt());
		route.setDesc(rteType.getDesc());
		route.setSrc(rteType.getSrc());
		route.setNumber(rteType.getNumber());
		route.setType(rteType.getType());
		route.setExtensions(ConvertUtil.convertObjectToJson(rteType.getExtensions()));

		return route;
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
}
