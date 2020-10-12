/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Email;
import com.example.demo.xml.element.EmailType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */
@Component
public class EmailMapper {

	public Email emailTypeToEmail(EmailType emailType) {
		if (emailType == null) {
			return null;
		}

		Email email = new Email();

		email.setDomain(emailType.getDomain());
		email.setEmailName(emailType.getId());
		
		return email;
	}
}
