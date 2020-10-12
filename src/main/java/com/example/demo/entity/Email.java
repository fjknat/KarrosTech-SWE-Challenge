/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "EMAIL")
@JsonInclude(Include.NON_EMPTY)
public class Email extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4247054063834669464L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "EMAIL_NAME", length = 100, nullable = false)
	@JsonProperty("id")
	private String emailName;
   
	@Column(name = "DOMAIN", length = 100, nullable = false)
	private String domain;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = true)
	@JsonIgnore
	private Person person;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the emailName
	 */
	public String getEmailName() {
		return emailName;
	}

	/**
	 * @param emailName the emailName to set
	 */
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}
