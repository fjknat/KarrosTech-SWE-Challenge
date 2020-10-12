/*******************************************************************************
 * Copyright (C) 2020, Thai Phung
 * 
 * This is the Karros's demo project which develop by Thai Phung.
 ******************************************************************************/
/**
 * 
 */
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.config.ExceptionMessageEnum;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639608308875317533L;

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message, String data) {
		super(message + " Your data: " + data);
		// TODO Auto-generated constructor stub
	}
	
	public ResourceNotFoundException(ExceptionMessageEnum enumException) {
		super(enumException.getMessageValue());
		// TODO Auto-generated constructor stub
	}
	
}
