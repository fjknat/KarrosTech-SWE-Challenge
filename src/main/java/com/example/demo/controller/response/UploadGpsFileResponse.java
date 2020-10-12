/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.response;

import com.example.demo.annotation.CustomJsonRootName;
import com.example.demo.config.SystemConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */
@CustomJsonRootName(plural = "uploadGpsFiles", singular = "uploadGpsFile")
@JsonInclude(Include.NON_EMPTY)
public class UploadGpsFileResponse {
	
	private UploadGpsFileResponse() {

	}

	private String status = SystemConstant.RESPONSE_STATUS_OK;
	private Integer trackInfoId;
		

	public String getStatus() {
		return status;
	}

	public Integer getTrackInfoId() {
		return trackInfoId;
	}

	public static UploadGpsFileResponseBuilder builder() {
		return new UploadGpsFileResponseBuilder();
	}
	

	UploadGpsFileResponse(String status, Integer trackInfoId) {
		super();
		this.status = status;
		this.trackInfoId = trackInfoId;
	}


	public static class UploadGpsFileResponseBuilder {
		private String status;
		private Integer trackInfoId;

		UploadGpsFileResponseBuilder(){
			
		}
		public UploadGpsFileResponseBuilder trackInfoId(Integer trackInfoId) {
			this.trackInfoId = trackInfoId;
			return this;
		}

		public UploadGpsFileResponseBuilder status(String status) {
			this.status = status;
			return this;
		}

		public UploadGpsFileResponse build() {
			UploadGpsFileResponse uploadGpsFileResponse = new UploadGpsFileResponse();
			uploadGpsFileResponse.trackInfoId = this.trackInfoId;
			uploadGpsFileResponse.status = this.status;
			return uploadGpsFileResponse;
		}

	}
}
