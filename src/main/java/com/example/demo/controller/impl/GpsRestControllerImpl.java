/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoApplication;
import com.example.demo.annotation.CustomJsonRootName;
import com.example.demo.annotation.ExecutionTimeLogging;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.controller.GpsRestController;
import com.example.demo.controller.request.LatestListRequest;
import com.example.demo.controller.request.TrackDetailRequest;
import com.example.demo.controller.request.UploadGpsFileRequest;
import com.example.demo.controller.response.LatestListResponse;
import com.example.demo.controller.response.TrackDetailResponse;
import com.example.demo.controller.response.UploadGpsFileResponse;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.exception.ValidateException;
import com.example.demo.service.GpsDataService;
import com.example.demo.service.TrackInfoService;
import com.example.demo.xml.element.GpxType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

@RestController
public class GpsRestControllerImpl implements GpsRestController {
	
	private static final Logger logger = LogManager.getLogger(DemoApplication.class);
	
	@Autowired
	private GpsDataService gpsService;
	
	@Autowired
	private TrackInfoService trackInfoService;
	
	@Override
	@ExecutionTimeLogging
	public UploadGpsFileResponse uploadGpsFile(HttpServletResponse response, UploadGpsFileRequest request) {
		
		if (request.getFile() == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw new ValidateException(ExceptionMessageEnum.ERROR_CODE_60404);
		}
		
		GpxType gpx;
		JAXBElement<GpxType> element;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class);
			byte[] byteData = new byte[request.getFile().getInputStream().available()];
			request.getFile().getInputStream().read(byteData);
			String rawFile = new String(byteData);
			InputStream inputStream = new ByteArrayInputStream(byteData);
			
			//Source source = new StreamSource(request.getFile().getInputStream());
			Source source = new StreamSource(inputStream);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			//gpx = (GpxType) unmarshaller.unmarshal(request.getFile().getInputStream());
			element = unmarshaller.unmarshal(source, GpxType.class);
			gpx = element.getValue();
			return gpsService.uploadGpsData(gpx, request, rawFile);
		} catch (JAXBException e) {
			ValidateException ex = new ValidateException(ExceptionMessageEnum.ERROR_INVALID_GPS_FORMAT);
			logger.info(e);
			throw ex;
		} catch (ValidateException e) {
			logger.error(e);
			throw e;
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			logger.error(e);
			logger.error(ex);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			logger.error(e);
			logger.error(ex);
			throw ex;
		} finally {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		

	}

	@Override
	@ExecutionTimeLogging
	public ResponseEntity<Map<String, List<LatestListResponse>>> getLatestTracks(LatestListRequest latestListRequest) {
		List<LatestListResponse> latestListResponses = gpsService.loadLatestTracks(latestListRequest);
		if (latestListResponses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Map<String, List<LatestListResponse>> result = new HashMap<>();
		result.put(LatestListResponse.class.getAnnotation(CustomJsonRootName.class).plural(), latestListResponses);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	@ExecutionTimeLogging
	public ResponseEntity<Map<String, TrackDetailResponse>> get(TrackDetailRequest trackDetailRequest) {
		TrackDetailResponse trackDetailResponse = trackInfoService.getTrackDetail(trackDetailRequest);
		if (trackDetailResponse == null ) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		Map<String, TrackDetailResponse> result = new HashMap<>();
		result.put(TrackDetailResponse.class.getAnnotation(CustomJsonRootName.class).plural(), trackDetailResponse);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
