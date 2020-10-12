/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Bound;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface BoundRepository extends BaseRepository<Bound, Integer>{

	@Query(value = "SELECT o FROM Bound o JOIN o.metadata a "
			+ " WHERE a.id = :id ")
	public Bound getByMetadataId(@Param("id") Integer id);
}
