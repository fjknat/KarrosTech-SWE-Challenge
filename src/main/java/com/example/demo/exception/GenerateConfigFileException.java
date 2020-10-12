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
public class GenerateConfigFileException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2612616346345629668L;

	public GenerateConfigFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenerateConfigFileException(String message) {
		super(message);
	}

	public GenerateConfigFileException(String message, String data) {
		super(message + " Your data: " + data);
	}
	
	public GenerateConfigFileException(ExceptionMessageEnum enumException) {
		super(enumException.getMessageValue());
		// TODO Auto-generated constructor stub
	}
}
