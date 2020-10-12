/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "TRACK_INFO")
@JsonInclude(Include.NON_EMPTY)
public class TrackInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7876519324842978570L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "TITLE", nullable = false, length = 200)
	private String title;
		
	@Column(name = "DESCRIPTION", length = 2000)
	private String Description;
	
	@Column(name = "DATE_POST")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant datePost;
	
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
	//@JsonIdentityReference(alwaysAsId = true)
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy = "trackInfo", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	@Transient
	private Gps gps;
	
	@Column(name = "TIMEZONE", length = 5)
	private String timeZone;
	
	@Column(name = "RAW_DATA_FILE", length = Integer.MAX_VALUE)
	private String rawFile;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the gps
	 */
	public Gps getGps() {
		return gps;
	}

	/**
	 * @param gps the gps to set
	 */
	public void setGps(Gps gps) {
		this.gps = gps;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the rawFile
	 */
	public String getRawFile() {
		return rawFile;
	}

	/**
	 * @param rawFile the rawFile to set
	 */
	public void setRawFile(String rawFile) {
		this.rawFile = rawFile;
	}

	/**
	 * @return the datePost
	 */
	public Instant getDatePost() {
		return datePost;
	}

	/**
	 * @param datePost the datePost to set
	 */
	public void setDatePost(Instant datePost) {
		this.datePost = datePost;
	}	
	
	
	
}
