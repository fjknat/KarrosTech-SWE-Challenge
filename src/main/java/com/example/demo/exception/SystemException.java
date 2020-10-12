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
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4246259296221890593L;

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public SystemException(ExceptionMessageEnum enumException) {
		super(enumException.getMessageValue());
		// TODO Auto-generated constructor stub
	}
}
