/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Route;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface RouteRepository extends BaseRepository<Route, Integer>{

	@Query(value = "SELECT wpt FROM Route wpt JOIN wpt.gps g "
			+ " WHERE g.id = :gpsID ")
	public List<Route> getByGpsId(@Param("gpsID") Integer gpsID);
}
