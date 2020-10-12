/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

/**
 * 
 */
package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

/**
 * BaseEntity with a soft delete flag 
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntitySoftDelete implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4234292377073378536L;
	
	@JsonIgnore
	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive;
	
	@PrePersist
	private void onPrePersist() {
		
		if (this.isActive == null) {
			this.isActive = true;
		}		
	}	

	@PreRemove
	private void onPreRemove() {		
		this.isActive = false;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
