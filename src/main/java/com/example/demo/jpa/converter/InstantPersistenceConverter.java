/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.jpa.converter;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize 
 */

@Converter(autoApply = true)
public class InstantPersistenceConverter implements AttributeConverter<Instant, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(Instant attribute) {
		return (attribute == null) ? null : Timestamp.from(attribute);
	}

	@Override
	public Instant convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.toInstant();
	}

}

