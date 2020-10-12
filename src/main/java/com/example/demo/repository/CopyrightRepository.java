/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Copyright;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface CopyrightRepository extends BaseRepository<Copyright, Integer>{

	@Query(value = "SELECT o FROM Copyright o JOIN o.metadata a "
			+ " WHERE a.id = :id ")
	public Copyright getByMetadataId(@Param("id") Integer id);
}
