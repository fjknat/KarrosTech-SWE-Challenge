/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.service.impl;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DemoApplication;
import com.example.demo.config.ExceptionMessageEnum;
import com.example.demo.entity.Bound;
import com.example.demo.entity.Copyright;
import com.example.demo.entity.Email;
import com.example.demo.entity.Metadata;
import com.example.demo.entity.Person;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.SystemException;
import com.example.demo.repository.BoundRepository;
import com.example.demo.repository.CopyrightRepository;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.MetadataService;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */
@Service
public class MetadataServiceImpl extends CrudServiceImpl<Metadata, Integer, MetadataRepository> implements MetadataService{

	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	CopyrightRepository copyrightRepository;
	
	@Autowired
	BoundRepository boundRepository;
	
	@Override
	public Metadata createContentOfMetadata(Metadata metadata) {
		try {
			Person person = metadata.getPerson();
			if (person != null) {
				if (person.getLink() != null) {
					person.getLink().setPerson(person);
				}
				Email email = person.getEmail();
				if (email != null) {
					email.setPerson(person);
				}
				person.setMetadata(metadata);
				metadata.setPerson(personRepository.save(person));
				
				
			}
			
			Copyright copyright = metadata.getCopyright();
			if (copyright != null) {
				copyright.setMetadata(metadata);
				metadata.setCopyright(copyrightRepository.save(copyright));
			}

			Bound bound = metadata.getBound();
			if (bound != null) {
				bound.setMetadata(metadata);
				metadata.setBound(boundRepository.save(bound));
			}
			
			
		} catch (PersistenceException e) {
			ServiceException ex = new ServiceException(ExceptionMessageEnum.ERROR_CODE_60102);
			log.error(e);
			throw ex;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionMessageEnum.ERROR_CODE_60000);
			log.error(e);
			throw ex;
		}
		return metadata;
	}

	@Override
	protected String getDefaultColumnSort() {
		return "id";
	}

}
