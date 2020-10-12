/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */

public class TrackDetailRequest {
	
	@Email(message = "User Name is email format.")
	private String userName;
	
	@NotNull(message = "Track Id is number positive format.")
	@Min(value = 0, message = "Track Id is number positive format.")
	private Integer trackId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	
	

}
