/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Gps;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface GpsRepository extends BaseRepository<Gps, Integer>{

	@Query(value = "SELECT g FROM Gps g, TrackInfo ti"
			+ " WHERE g.trackInfo = ti "
			+ " AND ti.id = :trackInfoId ")
	public Gps findByTrackInfoId(@Param("trackInfoId") Integer trackInfoId);
	
	@Query(value = "SELECT g.* FROM GPS_INFO g, TRACK_INFO ti, Track t, TRACK_SEGMENT ts, WAYPOINT wp"
			+ " WHERE g.TRACK_INFO_ID = ti.id AND g.ID = t.GPS_ID  AND t.ID = ts.TRACK_ID "
			+ " AND ts.ID = wp.TRACK_SEGMENT_ID AND ti.id = :trackInfoId ", nativeQuery = true)
	public Optional<Gps> findByTrackInfoIdSQL(@Param("trackInfoId") Integer trackInfoId);	
	
	@Query(value = "SELECT DISTINCT * FROM GPS_INFO g RIGHT JOIN TRACK_INFO ti ON g.TRACK_INFO_ID = ti.ID "
			+ " LEFT JOIN Track t ON g.ID = t.GPS_ID"
			+ " LEFT JOIN TRACK_SEGMENT ts ON t.ID = ts.TRACK_ID"
			+ " LEFT JOIN WAYPOINT wp ON ts.ID = wp.TRACK_SEGMENT_ID"
			+ " LEFT JOIN METADATA me ON g.ID = me.GPS_ID"
			+ " LEFT JOIN PERSON p ON p.METADATA_ID = me.ID "
			+ " LEFT JOIN COPYRIGHT cp ON me.ID = cp.METADATA_ID  "
			+ " LEFT JOIN BOUND b ON b.METADATA_ID = me.ID "
			+ " LEFT JOIN WAYPOINT wpt ON wpt.GPS_ID = g.ID"
			+ " LEFT JOIN ROUTE rt ON rt.GPS_ID = g.ID"
			+ " LEFT JOIN LINK lrt ON lrt.ROUTE_ID = rt.ID "			
			+ " LEFT JOIN LINK ltk ON ltk.TRACK_ID = t.ID"
			+ " WHERE ti.id = :trackInfoId ", nativeQuery = true)
	public Gps findFullByTrackInfoIdSQL(@Param("trackInfoId") Integer trackInfoId);
	

}
