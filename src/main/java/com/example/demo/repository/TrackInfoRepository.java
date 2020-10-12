/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.TrackInfo;
import com.example.demo.service.BaseRepository;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface TrackInfoRepository extends BaseRepository<TrackInfo, Integer>{
	
	@Query(value = "SELECT ti FROM TrackInfo ti, User u"
			+ " WHERE ti.user = u "
			+ " AND (u.userName = :userName OR :userName is null) ")
	public Page<TrackInfo> getLatestListTrackInfo(Pageable pageable, @Param("userName") String userName);
	
	@Query(value = "SELECT ti FROM TrackInfo ti, User u"
			+ " WHERE ti.user = u "
			+ " AND ti.id = :trackInfoId")
	Optional<TrackInfo> getWithUserByTrackInfoId(@Param("trackInfoId") Integer trackInfoId);
}
