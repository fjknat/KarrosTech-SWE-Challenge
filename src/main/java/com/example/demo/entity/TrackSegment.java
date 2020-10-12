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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "TRACK_SEGMENT")
@JsonInclude(Include.NON_EMPTY)
public class TrackSegment extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581532440564332733L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Fetch(FetchMode.JOIN)    
	@OneToMany(mappedBy = "trackSegment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonProperty("trkpt")
	private List<Waypoint> trackPoints;
	
	/*
     * Json value of dynamic object instead of Extensions table
     */
    @Column(name = "EXTENSIONS", length = 10000)
    private String extensions;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRACK_ID", nullable = true)
    @JsonIgnore
	private Track track;

	/**
	 * @return the trackPoints
	 */
	public List<Waypoint> getTrackPoints() {
		return trackPoints;
	}

	/**
	 * @param trackPoints the trackPoints to set
	 */
	public void setTrackPoints(List<Waypoint> trackPoints) {
		this.trackPoints = trackPoints;
	}

	/**
	 * @return the extensions
	 */
	public String getExtensions() {
		return extensions;
	}

	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(String extensions) {
		this.extensions = extensions;
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
    
	
	
}
