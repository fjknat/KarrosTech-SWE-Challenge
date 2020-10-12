/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Link;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface LinkRepository extends BaseRepository<Link, Integer>{

	@Query(value = "SELECT l FROM Link l JOIN l.route r"
			+ " WHERE r.id = :routeID ")
	public List<Link> getByRouteId(@Param("routeID") Integer routeID);
	
	@Query(value = "SELECT l FROM Link l JOIN l.track r"
			+ " WHERE r.id = :id ")
	public List<Link> getByTrackId(@Param("id") Integer id);
}
