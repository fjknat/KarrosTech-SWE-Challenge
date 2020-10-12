/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Bound;
import com.example.demo.xml.element.BoundsType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class BoundMapper {
	public Bound boundsTypeToBound(BoundsType boundsType) {
		if (boundsType == null) {
			return null;
		}

		Bound bound = new Bound();

		bound.setMaxlat(boundsType.getMaxlat());
		bound.setMaxlon(boundsType.getMaxlon());
		bound.setMinlat(boundsType.getMinlat());
		bound.setMinlon(boundsType.getMinlon());

		return bound;
	}
}
