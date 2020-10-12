/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020			1.0				Initialize
 */

@Entity
@Table(name = "USER")
@JsonInclude(Include.NON_EMPTY)
public class User extends BaseEntity{

	private static final long serialVersionUID = -3122530630382452413L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 100)
	private String userName;

	@Column(name = "FULL_NAME", nullable = false, length = 50)
	private String fullName;

	@Column(name = "IS_ADMIN", nullable = false, columnDefinition = "boolean default false")
	private Boolean isAdmin = false;
	
	@Fetch(FetchMode.JOIN)
	//@JsonSerialize(using = RoleSerializer.class)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrackInfo> trackInfo;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	

	/**
	 * @return the trackInfo
	 */
	public List<TrackInfo> getTrackInfo() {
		return trackInfo;
	}

	/**
	 * @param trackInfo the trackInfo to set
	 */
	public void setTrackInfo(List<TrackInfo> trackInfo) {
		this.trackInfo = trackInfo;
	}
	
	
}
