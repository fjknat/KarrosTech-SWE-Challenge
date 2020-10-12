/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.exception.advice;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

public class RESTFulStatus {

	private Integer statusResponse;

	private Integer status;

	private String error;

	private String message;

	private String version;

	private String applicationName;

	private String timestamp;

	/**
	 * @param statusResponse
	 * @param status
	 * @param message
	 * @param version
	 * @param applicationName
	 * @param timestamp
	 * @param path
	 */
	public RESTFulStatus(Integer statusResponse, Integer status, String error, String message, String version,
			String applicationName, Instant timestamp, String path) {
		super();
		this.statusResponse = statusResponse;
		this.status = status;
		this.message = message;
		this.error = error;
		this.version = version;
		this.applicationName = applicationName;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss'Z'").withLocale(Locale.UK)
				.withZone(ZoneOffset.UTC);

		this.timestamp = formatter.format(timestamp);
		this.path = path;
	}

	private String path;

	public static final int STATUS_ERROR = 1;
	public static final int STATUS_OK = 0;

	/**
	 * @return the statusResponse
	 */
	public Integer getStatusResponse() {
		return statusResponse;
	}
	/**
	 * @param statusResponse the statusResponse to set
	 */
	public void setStatusResponse(Integer statusResponse) {
		this.statusResponse = statusResponse;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the statusError
	 */
	public static int getStatusError() {
		return STATUS_ERROR;
	}
	/**
	 * @return the statusOk
	 */
	public static int getStatusOk() {
		return STATUS_OK;
	}

	
}
