/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service;

import com.example.demo.controller.request.TrackDetailRequest;
import com.example.demo.controller.response.TrackDetailResponse;
import com.example.demo.entity.Gps;
import com.example.demo.entity.TrackInfo;
import com.example.demo.entity.User;
import com.example.demo.xml.element.GpxType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface TrackInfoService extends CrudService<TrackInfo, Integer>{
	TrackInfo createTrackInfo(String title, User user, Gps gps, GpxType gpxType, String rawGpsFile);
	
	TrackDetailResponse getTrackDetail(TrackDetailRequest trackDetailRequest);
}
