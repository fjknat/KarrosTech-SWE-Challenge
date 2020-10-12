/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Copyright;
import com.example.demo.xml.element.CopyrightType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */
@Component
public class CopyrightMapper {

	public Copyright copyrightTypeToCopyright(CopyrightType copyrightType) {
		if (copyrightType == null) {
			return null;
		}

		Copyright copyright = new Copyright();

		copyright.setAuthor(copyrightType.getAuthor());
		copyright.setLicense(copyrightType.getLicense());
		copyright.setYear(copyrightType.getYear());

		return copyright;
	}
}
