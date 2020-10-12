/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service;

import com.example.demo.entity.Metadata;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface MetadataService extends CrudService<Metadata, Integer>{
	Metadata createContentOfMetadata(Metadata metadata);
}
