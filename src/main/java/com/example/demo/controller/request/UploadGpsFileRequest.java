/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

public class UploadGpsFileRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2175156329019010797L;
	@NotNull(message = "Missing the GPS file.")
	private MultipartFile file;
	
	@Size(min = 5, max = 200, message = "Track title can't be empty and less than 200 characters.")
	@NotNull(message = "Track title isn't null")
	private String trackTitle;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the trackTitle
	 */
	public String getTrackTitle() {
		return trackTitle;
	}

	/**
	 * @param trackTitle the trackTitle to set
	 */
	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}
	
	
}
