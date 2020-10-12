/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Person;
import com.example.demo.xml.element.PersonType;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

@Component
public class PersonMapper {

	@Autowired
	private EmailMapper emailMapper;

	@Autowired
	private LinkMapper linkMapper;

	public Person personTypeToPerson(PersonType personType) {
		if (personType == null) {
			return null;
		}

		Person person = new Person();

		person.setEmail(emailMapper.emailTypeToEmail(personType.getEmail()));
		person.setName(personType.getName());
		person.setLink(linkMapper.linkTypeToLink(personType.getLink()));

		return person;
	}

}
