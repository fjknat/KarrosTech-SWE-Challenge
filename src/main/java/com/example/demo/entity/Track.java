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
@Table(name = "TRACK")
@JsonInclude(Include.NON_EMPTY)
public class Track extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708190137569440838L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "NAME", length = 200)
	private String name;
	@Column(name = "CMT", length = 2000)
    private String cmt;
	@Column(name = "DESC", length = 2000)
    private String desc;
	@Column(name = "SRC", length = 2000)
    private String src;
	@Column(name = "NUMBER")
    private Integer number;
    @Column(name = "TYPE", length = 100)
    private String type;
    
    @Fetch(FetchMode.JOIN)	
	@OneToMany(mappedBy = "track", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Transient
	private List<Link> links; 

    /*
     * Json value of dynamic object instead of Extensions table
     */
    @Column(name = "EXTENSIONS", length = 10000)
    private String extensions;
    
    @Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "track", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("trkseg")
	private List<TrackSegment> trackSegments;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GPS_ID")
    @JsonIgnore
    private Gps gps;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cmt
	 */
	public String getCmt() {
		return cmt;
	}

	/**
	 * @param cmt the cmt to set
	 */
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
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
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
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
	 * @return the trackSegments
	 */
	public List<TrackSegment> getTrackSegments() {
		return trackSegments;
	}

	/**
	 * @param trackSegments the trackSegments to set
	 */
	public void setTrackSegments(List<TrackSegment> trackSegments) {
		this.trackSegments = trackSegments;
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
    
	
}
