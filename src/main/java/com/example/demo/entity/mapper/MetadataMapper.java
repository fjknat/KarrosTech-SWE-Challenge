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
import com.example.demo.entity.Metadata;
import com.example.demo.util.ConvertUtil;
import com.example.demo.xml.element.LinkType;
import com.example.demo.xml.element.MetadataType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class MetadataMapper {

	@Autowired
	private LinkMapper linkMapper;
	
	@Autowired
	private CopyrightMapper copyrightMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	BoundMapper boundMapper;

	public Metadata toMetadateEntity(MetadataType metadataType) {
		if (metadataType == null) {
			return null;
		}

		Metadata metadata = new Metadata();

		metadata.setDatePost(ConvertUtil.convertXMLGregorianCalendarToInstant(metadataType.getTime()));
		metadata.setLinks(linkTypeListToLinkList(metadataType.getLink()));
		metadata.setName(metadataType.getName());
		metadata.setDesc(metadataType.getDesc());
		metadata.setKeywords(metadataType.getKeywords());
		metadata.setExtensions(ConvertUtil.convertObjectToJson(metadataType.getExtensions()));
		metadata.setCopyright(copyrightMapper.copyrightTypeToCopyright(metadataType.getCopyright()));
		metadata.setPerson(personMapper.personTypeToPerson(metadataType.getAuthor()));
		metadata.setBound(boundMapper.boundsTypeToBound(metadataType.getBounds()));
		return metadata;
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
