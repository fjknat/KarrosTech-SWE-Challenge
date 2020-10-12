/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Link;
import com.example.demo.xml.element.LinkType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class LinkMapper {

	public Link linkTypeToLink(LinkType linkType) {
		if (linkType == null) {
			return null;
		}

		Link link = new Link();

		link.setHref(linkType.getHref());
		link.setText(linkType.getText());
		link.setType(linkType.getType());

		return link;
	}
}
