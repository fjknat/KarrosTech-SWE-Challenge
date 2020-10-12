/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.util;

import java.time.Instant;
import java.time.ZoneOffset;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.demo.DemoApplication;
import com.example.demo.xml.element.ExtensionsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

public class ConvertUtil {

	private static final Logger logger = LogManager.getLogger(DemoApplication.class);
	private static final String EMPTY_STRING = "";
		
	public static String convertObjectToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = EMPTY_STRING;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error(e);			
		}
		return json;
	}
	
	public static String convertObjectToJson(ExtensionsType extensionsType) {
		ObjectMapper mapper = new ObjectMapper();
		String json = EMPTY_STRING;
		try {
			if(extensionsType != null) {
				json = mapper.writeValueAsString(extensionsType.getAny());
			}
		} catch (JsonProcessingException e) {
			logger.error(e);			
		}
		return json;
	}
	
	public static Instant convertXMLGregorianCalendarToInstant(XMLGregorianCalendar xmlGregorianCalendar ) {
		if(xmlGregorianCalendar == null) {
			return null;
		}
		Instant convertedDateTime = xmlGregorianCalendar.toGregorianCalendar()
	            .toZonedDateTime()
	            .withZoneSameLocal(ZoneOffset.UTC)
	            .toInstant();
		return convertedDateTime;
		
	}
	
	public static String getTimeZoneCode(XMLGregorianCalendar xmlGregorianCalendar ) {
		if(xmlGregorianCalendar == null) {
			return null;
		}
		return xmlGregorianCalendar.toGregorianCalendar()
	            .toZonedDateTime().getZone().toString();
		
	}
	
}
