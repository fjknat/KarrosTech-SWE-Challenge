/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service;

import java.util.List;

import com.example.demo.controller.request.LatestListRequest;
import com.example.demo.controller.request.UploadGpsFileRequest;
import com.example.demo.controller.response.LatestListResponse;
import com.example.demo.controller.response.UploadGpsFileResponse;
import com.example.demo.entity.Gps;
import com.example.demo.xml.element.GpxType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public interface GpsDataService extends CrudService<Gps, Integer>{
	
	UploadGpsFileResponse uploadGpsData(GpxType gpxType, UploadGpsFileRequest request, String rawGpsFile);
	
	List<LatestListResponse> loadLatestTracks(LatestListRequest latestListRequest);
}
