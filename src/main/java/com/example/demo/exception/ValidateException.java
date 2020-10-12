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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5143160629829248472L;

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(String message, String data) {
		super(message + " Your data: " + data);
		// TODO Auto-generated constructor stub
	}

	public ValidateException(ExceptionMessageEnum enumException) {
		super(enumException.getMessageValue());
		// TODO Auto-generated constructor stub
	}
}
