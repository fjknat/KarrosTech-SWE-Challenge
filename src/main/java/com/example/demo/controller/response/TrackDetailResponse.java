/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.response;

import java.io.Serializable;
import java.time.Instant;

import com.example.demo.annotation.CustomJsonRootName;
import com.example.demo.entity.Gps;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */
@CustomJsonRootName(plural = "trackDetails", singular = "trackDetail")
@JsonInclude(Include.NON_NULL)
public class TrackDetailResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147324376673560047L;
	
	private TrackDetailResponse() {		
		
	}
	
	private String userName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private Instant createdDate;

	private String trackTitle;
	private String trackName;
	private String trackDesc;
	private String trackTags;
	private Gps gpx;

	public String getUserName() {
		return userName;
	}
	public Instant getCreatedDate() {
		return createdDate;
	}
	public String getTrackTitle() {
		return trackTitle;
	}
	public String getTrackName() {
		return trackName;
	}
	public String getTrackDesc() {
		return trackDesc;
	}
	public String getTrackTags() {
		return trackTags;
	}
	public Gps getGpx() {
		return gpx;
	}
	
	public static TrackDetailResponseBuilder builder() {
		return new TrackDetailResponseBuilder();
	}

	public static class TrackDetailResponseBuilder {
		private String userName;
		private Instant createdDate;
		private String trackTitle;
		private String trackName;
		private String trackDesc;
		private String trackTags;
		private Gps gpx;

		public TrackDetailResponseBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public TrackDetailResponseBuilder createdDate(Instant createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public TrackDetailResponseBuilder trackTitle(String trackTitle) {
			this.trackTitle = trackTitle;
			return this;
		}

		public TrackDetailResponseBuilder trackName(String trackName) {
			this.trackName = trackName;
			return this;
		}

		public TrackDetailResponseBuilder trackDesc(String trackDesc) {
			this.trackDesc = trackDesc;
			return this;
		}

		public TrackDetailResponseBuilder trackTags(String trackTags) {
			this.trackTags = trackTags;
			return this;
		}

		public TrackDetailResponseBuilder trk(Gps gpx) {
			this.gpx = gpx;
			return this;
		}

		public TrackDetailResponse build() {
			TrackDetailResponse trackDetailResponse = new TrackDetailResponse();
			trackDetailResponse.userName = this.userName;
			trackDetailResponse.createdDate = this.createdDate;
			trackDetailResponse.trackTitle = this.trackTitle;
			trackDetailResponse.trackName = this.trackName;
			trackDetailResponse.trackDesc = this.trackDesc;
			trackDetailResponse.trackTags = this.trackTags;
			trackDetailResponse.gpx = this.gpx;

			return trackDetailResponse;
		}
	}

}
