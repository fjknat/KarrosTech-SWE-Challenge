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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "GPS_INFO")
@JsonInclude(Include.NON_EMPTY)
public class Gps extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197518784803883142L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	/*
     * Json value of dynamic object instead of Extensions table
     */
    @Column(name = "EXTENSIONS", length = 10000)
    private String extensions;
    @Column(name = "VERSION", nullable = false, length = 100)
    private String version;
    @Column(name = "CREATOR", nullable = false, length = 500)
    private String creator;
    
	@OneToOne(mappedBy = "gps", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)	
	private Metadata metadata;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRACK_INFO_ID")
	@JsonIgnore
    private TrackInfo trackInfo;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "gps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Transient
	@JsonProperty("wpt")
	private List<Waypoint> waypoints;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "gps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Transient
	@JsonProperty("rte")
    private List<Route> routes;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "gps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonProperty("trk")
    private List<Track> tracks;

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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
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
	 * @return the waypoints
	 */
	public List<Waypoint> getWaypoints() {
		return waypoints;
	}

	/**
	 * @param waypoints the waypoints to set
	 */
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	/**
	 * @return the routes
	 */
	public List<Route> getRoutes() {
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	/**
	 * @return the tracks
	 */
	public List<Track> getTracks() {
		return tracks;
	}

	/**
	 * @param tracks the tracks to set
	 */
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	/**
	 * @return the trackInfo
	 */
	public TrackInfo getTrackInfo() {
		return trackInfo;
	}

	/**
	 * @param trackInfo the trackInfo to set
	 */
	public void setTrackInfo(TrackInfo trackInfo) {
		this.trackInfo = trackInfo;
	}
	
	
    
}
