/*******************************************************************************
 * Copyright (C) 2020, Thai Phung
 * 
 * This is the Karros's demo project which develop by Thai Phung.
 ******************************************************************************/

package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.request.LatestListRequest;
import com.example.demo.controller.request.TrackDetailRequest;
import com.example.demo.controller.request.UploadGpsFileRequest;
import com.example.demo.controller.response.LatestListResponse;
import com.example.demo.controller.response.TrackDetailResponse;
import com.example.demo.controller.response.UploadGpsFileResponse;


/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@RequestMapping(value = "/gps")
public interface GpsRestController {

	@PostMapping(value = "/upload")
	public UploadGpsFileResponse uploadGpsFile(HttpServletResponse response, 			
			@Valid @ModelAttribute UploadGpsFileRequest request);

	@PostMapping(value = "/latest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, List<LatestListResponse>>> getLatestTracks(@Valid @ModelAttribute LatestListRequest latestListRequest);

	@GetMapping(value = "/track", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, TrackDetailResponse>> getTrackDetail(@Valid TrackDetailRequest trackDetailRequest);	

}
