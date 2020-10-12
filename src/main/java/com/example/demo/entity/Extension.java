/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "EXTENSION")
public class Extension extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2211675931716088728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "OBJECT_NAME", length = 500)
	private String objectName;

	@Column(name = "OBJECT_VALUE")
	private String objectValue;

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
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the objectValue
	 */
	public String getObjectValue() {
		return objectValue;
	}

	/**
	 * @param objectValue the objectValue to set
	 */
	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "METADATA_ID", nullable = false)
//	private Metadata metadata;
	
	
}
