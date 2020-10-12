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

import com.example.demo.controller.dto.WptDTO;
import com.example.demo.entity.Link;
import com.example.demo.entity.Waypoint;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.LinkType;
import com.example.demo.xml.element.WptType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class WaypointMapper {

	@Autowired
	private LinkMapper linkMapper;

	public Waypoint toWaypointEntity(WptType wptType) {
		if (wptType == null) {
			return null;
		}

		Waypoint waypoint = new Waypoint();

		waypoint.setLinks(linkTypeListToLinkList(wptType.getLink()));
		waypoint.setTime(ConvertUtil.convertXMLGregorianCalendarToInstant(wptType.getTime()));
		waypoint.setEle(wptType.getEle());
		waypoint.setMagvar(wptType.getMagvar());
		waypoint.setGeoidheight(wptType.getGeoidheight());
		waypoint.setName(wptType.getName());
		waypoint.setCmt(wptType.getCmt());
		waypoint.setDesc(wptType.getDesc());
		waypoint.setSrc(wptType.getSrc());
		waypoint.setSym(wptType.getSym());
		waypoint.setType(wptType.getType());
		waypoint.setFix(wptType.getFix());
		waypoint.setSat(wptType.getSat());
		waypoint.setHdop(wptType.getHdop());
		waypoint.setVdop(wptType.getVdop());
		waypoint.setPdop(wptType.getPdop());
		waypoint.setAgeofdgpsdata(wptType.getAgeofdgpsdata());
		waypoint.setDgpsid(wptType.getDgpsid());
		waypoint.setLat(wptType.getLat());
		waypoint.setLon(wptType.getLon());
		waypoint.setExtensions(ConvertUtil.convertObjectToJson(wptType.getExtensions()));

		return waypoint;
	}
	
	public WptDTO toWptDTO (Waypoint waypoint) {
		if (waypoint == null) {
			return null;
		}

		WptDTO wptDTO = new WptDTO();

		wptDTO.setAgeofdgpsdata(waypoint.getAgeofdgpsdata());
		wptDTO.setDgpsid(waypoint.getDgpsid());
		wptDTO.setEle(waypoint.getEle());
		wptDTO.setFix(waypoint.getFix());
		wptDTO.setGeoidheight(waypoint.getGeoidheight());
		wptDTO.setHdop(waypoint.getHdop());
		wptDTO.setLat(waypoint.getLat());
		wptDTO.setLon(waypoint.getLon());
		wptDTO.setMagvar(waypoint.getMagvar());
		wptDTO.setPdop(waypoint.getPdop());
		wptDTO.setSat(waypoint.getSat());
		wptDTO.setTime(waypoint.getTime());
		wptDTO.setType(waypoint.getType());
		wptDTO.setVdop(waypoint.getVdop());
		return wptDTO;
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
