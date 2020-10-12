/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.config.SystemConstant;
import com.example.demo.jpa.converter.InstantPersistenceConverter;



/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020			1.0				Initialize
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1317099498507656326L;

	@JsonIgnore
	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive;

	@JsonIgnore
	@Column(name = "RECORD_VERSION", nullable = false)
	private Integer recordVersion;

	@JsonIgnore
	@CreatedBy
	@Column(name = "CREATED_BY", updatable = false)
	private Integer createdBy;

	@JsonIgnore
	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@JsonIgnore
	@LastModifiedBy
	@Column(name = "DELETED_BY")
	private Integer deletedBy;

	@JsonIgnore
	@Column(name = "CREATED_AT", updatable = false)
	@Convert(converter = InstantPersistenceConverter.class)
	private Instant createdAt;

	@JsonIgnore
	@Column(name = "UPDATED_AT")
	@Convert(converter = InstantPersistenceConverter.class)
	private Instant updatedAt;

	@JsonIgnore
	@Column(name = "DELETED_AT")
	@Convert(converter = InstantPersistenceConverter.class)
	private Instant deletedAt;

	@PrePersist
	private void onPrePersist() {

		this.createdAt = Instant.now();
		this.updatedAt = null;
		this.updatedBy = null;
		this.deletedAt = null;
		this.deletedBy = null;

		if (this.isActive == null) {
			this.isActive = true;
		}
		if (this.createdBy == null) {
			this.createdBy = SystemConstant.DEFAULT_USER_ID;
		}
		if (this.recordVersion == null) {
			this.recordVersion = 1;
		}
	}

	@PreUpdate
	private void onPreUpdate() {

		if (this.isActive == null) {
			this.isActive = true;
			
			this.updatedAt = Instant.now();
			if (this.updatedBy == null) {
				this.updatedBy = SystemConstant.DEFAULT_USER_ID;
			}
			
			this.deletedAt = null;
			this.deletedBy = null;
		} else if (this.isActive) {
			this.updatedAt = Instant.now();
			if (this.updatedBy == null) {
				this.updatedBy = SystemConstant.DEFAULT_USER_ID;
			}
			this.deletedAt = null;
			this.deletedBy = null;
		} else {
			this.deletedAt = Instant.now();
			if (this.deletedBy == null) {
				this.deletedBy = SystemConstant.DEFAULT_USER_ID;
			}
		}

		this.recordVersion = this.recordVersion + 1;
	}

	@PreRemove
	private void onPreRemove() {
		if (this.deletedBy == null) {
			this.deletedBy = SystemConstant.DEFAULT_USER_ID;
		}
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

	/**
	 * @return the recordVersion
	 */
	public Integer getRecordVersion() {
		return recordVersion;
	}

	/**
	 * @param recordVersion the recordVersion to set
	 */
	public void setRecordVersion(Integer recordVersion) {
		this.recordVersion = recordVersion;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the deletedBy
	 */
	public Integer getDeletedBy() {
		return deletedBy;
	}

	/**
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the createdAt
	 */
	public Instant getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the deletedAt
	 */
	public Instant getDeletedAt() {
		return deletedAt;
	}

	/**
	 * @param deletedAt the deletedAt to set
	 */
	public void setDeletedAt(Instant deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}