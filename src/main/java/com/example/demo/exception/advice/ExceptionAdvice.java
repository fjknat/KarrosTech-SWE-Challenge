/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/


package com.example.demo.exception.advice;

import java.time.Instant;
import java.util.stream.Collectors;

import javax.validation.UnexpectedTypeException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.DemoApplication;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.exception.GenerateConfigFileException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.exception.TimeoutException;
import com.example.demo.exception.ValidateException;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */
@RestControllerAdvice
public class ExceptionAdvice {
	
	@Value("${application.version}")
	private String version;
	
	@Value("${application.name}")
	private String applicationName;
	
	private static final Logger logger = LogManager.getLogger(DemoApplication.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(GenerateConfigFileException.class)
	RESTFulStatus generateConfigFileHandler(GenerateConfigFileException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), version, applicationName, Instant.now(), "");
		logger.info(ex);
		return status;
	}
	

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	RESTFulStatus resourceNotFoundHandler(ResourceNotFoundException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), version, applicationName, Instant.now(), "");
		logger.info(ex);
		return status;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ServiceException.class)
	RESTFulStatus serviceHandler(ServiceException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), version, applicationName, Instant.now(), "");
		logger.error(ex);
		return status;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SystemException.class)
	RESTFulStatus systemHandler(SystemException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), version, applicationName,
				Instant.now(), "");
		logger.error(ex);
		return status;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler(TimeoutException.class)
	RESTFulStatus timeoutHandler(TimeoutException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.REQUEST_TIMEOUT.value(),
				HttpStatus.REQUEST_TIMEOUT.getReasonPhrase(), ex.getMessage(), version, applicationName, Instant.now(),
				"");
		logger.warn(ex);
		return status;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidateException.class)
	RESTFulStatus resourceNotFoundHandler(ValidateException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), version, applicationName, Instant.now(), "");
		logger.info(ex);
		return status;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	RESTFulStatus exceptionHandler(BindException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getBindingResult().getAllErrors().stream()
						.map(e -> e.getDefaultMessage()).collect(Collectors.joining(" | ")),
				version, applicationName, Instant.now(), "");
		logger.error(ex);
		return status;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnexpectedTypeException.class)
	RESTFulStatus exceptionHandler(UnexpectedTypeException ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(),
				version, applicationName, Instant.now(), "");
		logger.error(ex);
		return status;
	}
	


	/*
	 * System error
	 */

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	RESTFulStatus exceptionHandler(Exception ex) {
		RESTFulStatus status = new RESTFulStatus(RESTFulStatus.STATUS_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				ExceptionMessageEnum.ERROR_CODE_60000.getMessageValue(), version, applicationName, Instant.now(), "");
		logger.error(ex);
		return status;
	}
	

}
