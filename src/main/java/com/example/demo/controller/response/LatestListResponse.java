/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.example.demo.annotation.CustomJsonRootName;
import com.example.demo.controller.dto.TrkDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

//@JsonRootName(value = "gpx", namespace = "gpxs")
//@JsonTypeName("gpxs")
//@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use= JsonTypeInfo.Id.NAME)
@CustomJsonRootName(plural = "gpxs", singular = "gpx")
@JsonInclude(Include.NON_EMPTY)
public class LatestListResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6813637808168979186L;


	private LatestListResponse() {
	}

	private String userName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private Instant createdDate;

	private String trackTitle;
	private Integer trackId;
	private String trackDesc;
	private String trackTags;
	private List<TrkDTO> trk;

	public String getUserName() {
		return userName;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public String getTrackTitle() {
		return trackTitle;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public String getTrackDesc() {
		return trackDesc;
	}

	public String getTrackTags() {
		return trackTags;
	}

	public List<TrkDTO> getTrk() {
		return trk;
	}

	public String getImageData() {
		return imageData;
	}

	/*
	 * It will store short image on base64 format
	 */
	private String imageData;
	
	
	public static LatestListResponseBuilder builder() {
		return new LatestListResponseBuilder();
	}

	public static class LatestListResponseBuilder {
		private String userName;
		private Instant createdDate;
		private String trackTitle;
		private Integer trackId;
		private String trackDesc;
		private String trackTags;
		private List<TrkDTO> trk;

		public LatestListResponseBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public LatestListResponseBuilder createdDate(Instant createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public LatestListResponseBuilder trackTitle(String trackTitle) {
			this.trackTitle = trackTitle;
			return this;
		}

		public LatestListResponseBuilder trackId(Integer trackId) {
			this.trackId = trackId;
			return this;
		}

		public LatestListResponseBuilder trackDesc(String trackDesc) {
			this.trackDesc = trackDesc;
			return this;
		}

		public LatestListResponseBuilder trackTags(String trackTags) {
			this.trackTags = trackTags;
			return this;
		}

		public LatestListResponseBuilder trk(List<TrkDTO> trk) {
			this.trk = trk;
			return this;
		}

		public LatestListResponse build() {
			LatestListResponse latestListResponse = new LatestListResponse();
			latestListResponse.userName = this.userName;
			latestListResponse.createdDate = this.createdDate;
			latestListResponse.trackTitle = this.trackTitle;
			latestListResponse.trackId = this.trackId;
			latestListResponse.trackDesc = this.trackDesc;
			latestListResponse.trackTags = this.trackTags;
			latestListResponse.trk = this.trk;

			return latestListResponse;
		}
	}

}
