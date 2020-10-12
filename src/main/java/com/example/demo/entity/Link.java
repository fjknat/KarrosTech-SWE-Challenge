/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

/**
 * 
 */
package com.example.demo.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

/**
 * @author miskd
 *
 */
@Entity
@Table(name = "LINK")
@JsonInclude(Include.NON_EMPTY)
public class Link extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1281385412301064929L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "HREF", nullable = false, length = 2024)
	private String href;
	
	@Column(name = "TEXT", length = 2000)
	private String text;
	
	@Column(name = "TYPE", length = 20)
	private String type;
	
	/* TODO 
	 * Metadata, Person, Waypoint, Route, ... need use only one parentId column and 
	 * define by format of ID or use with parentType column as [MD, PS, WP, RT,...] 
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "METADATA_ID", nullable = true)
	@JsonIgnore
	private Metadata metadata;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = true)
	@JsonIgnore
	private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WAYPOINT_ID", nullable = true)
	@JsonIgnore
	private Waypoint waypoint;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROUTE_ID", nullable = true)
	@JsonIgnore
	private Route route;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRACK_ID", nullable = true)
	@JsonIgnore
	private Track track;

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
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
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

	/**
	 * @return the waypoint
	 */
	public Waypoint getWaypoint() {
		return waypoint;
	}

	/**
	 * @param waypoint the waypoint to set
	 */
	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

	/**
	 * @return the route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}

	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(Track track) {
		this.track = track;
	}
	
	
	
}
