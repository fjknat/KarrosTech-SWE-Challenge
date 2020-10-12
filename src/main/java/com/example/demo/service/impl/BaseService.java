/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service.impl;

import org.springframework.data.domain.Sort;

import com.example.demo.config.SystemConstant;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public class BaseService {

	//TODO Need implement to use hard code list if not exist application.properties
	public Integer getMaxNumberOfPaging() {		
		return SystemConstant.PAGING_DEFAULT_SIZE_OF_PAGE;
	}

	public Sort.Direction getOrderSortOfPaging() {		
		if ("ASC".equals(SystemConstant.PAGING_DEFAULT_ORDER_SORT))
			return Sort.Direction.ASC;
		else
			return Sort.Direction.DESC;
	}
}
