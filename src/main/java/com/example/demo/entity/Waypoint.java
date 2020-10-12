/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import java.time.Instant;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "WAYPOINT")
@JsonInclude(Include.NON_EMPTY)
public class Waypoint extends BaseEntitySoftDelete {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8236173691522700808L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "ELE")
	private Double ele;
	@Column(name = "DATE_POST")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant time;
    @Column(name = "MAG_VAR")
    private Double magvar;
    @Column(name = "GEO_ID_HEIGHT")
    private Double geoidheight;
    
    @Column(name = "NAME", length = 200)
    private String name;
    @Column(name = "CMT", length = 2000)
    private String cmt;
    @Column(name = "DESC", length = 2000)
    private String desc;
    @Column(name = "SRC", length = 2000)
    private String src;
 
    @Column(name = "SYM", length = 2000)
    private String sym;
    @Column(name = "TYPE", length = 100)
    private String type;
    @Column(name = "FIX", length = 2000)
    private String fix;

    @Column(name = "SAT")
    private Integer sat;
    @Column(name = "HDOP")
    private Double hdop;
    @Column(name = "VDOP")
    private Double vdop;
    @Column(name = "PDOP")
    private Double pdop;
    @Column(name = "AGE_OF_DGPS_DATA")
    private Double ageofdgpsdata;
    @Column(name = "DGPS_ID")
    private Integer dgpsid;
    
    @Column(name = "EXTENSIONS", length = 10000)
    private String extensions;
    
    @Column(name = "LAT", nullable = false)
    private Double lat;
    @Column(name = "LON", nullable = false)
    private Double lon;
    
    @Fetch(FetchMode.JOIN)	
	@OneToMany(mappedBy = "waypoint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Link> links;
    
    /* TODO 
	 * Metadata, Gps, Route, ... need use only one parentId column and 
	 * define by format of ID or use with parentType column as [GP, RT,...] 
	 */
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GPS_ID")
    @JsonIgnore
    private Gps gps;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID")
    @JsonIgnore
    private Route route;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRACK_SEGMENT_ID")
    @JsonIgnore
    private TrackSegment trackSegment;
    

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
	 * @return the ele
	 */
	public Double getEle() {
		return ele;
	}

	/**
	 * @param ele the ele to set
	 */
	public void setEle(Double ele) {
		this.ele = ele;
	}

	/**
	 * @return the time
	 */
	public Instant getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Instant time) {
		this.time = time;
	}

	/**
	 * @return the magvar
	 */
	public Double getMagvar() {
		return magvar;
	}

	/**
	 * @param magvar the magvar to set
	 */
	public void setMagvar(Double magvar) {
		this.magvar = magvar;
	}

	/**
	 * @return the geoidheight
	 */
	public Double getGeoidheight() {
		return geoidheight;
	}

	/**
	 * @param geoidheight the geoidheight to set
	 */
	public void setGeoidheight(Double geoidheight) {
		this.geoidheight = geoidheight;
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
	 * @return the sym
	 */
	public String getSym() {
		return sym;
	}

	/**
	 * @param sym the sym to set
	 */
	public void setSym(String sym) {
		this.sym = sym;
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
	 * @return the fix
	 */
	public String getFix() {
		return fix;
	}

	/**
	 * @param fix the fix to set
	 */
	public void setFix(String fix) {
		this.fix = fix;
	}

	/**
	 * @return the sat
	 */
	public Integer getSat() {
		return sat;
	}

	/**
	 * @param sat the sat to set
	 */
	public void setSat(Integer sat) {
		this.sat = sat;
	}

	/**
	 * @return the hdop
	 */
	public Double getHdop() {
		return hdop;
	}

	/**
	 * @param hdop the hdop to set
	 */
	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}

	/**
	 * @return the vdop
	 */
	public Double getVdop() {
		return vdop;
	}

	/**
	 * @param vdop the vdop to set
	 */
	public void setVdop(Double vdop) {
		this.vdop = vdop;
	}

	/**
	 * @return the pdop
	 */
	public Double getPdop() {
		return pdop;
	}

	/**
	 * @param pdop the pdop to set
	 */
	public void setPdop(Double pdop) {
		this.pdop = pdop;
	}

	/**
	 * @return the ageofdgpsdata
	 */
	public Double getAgeofdgpsdata() {
		return ageofdgpsdata;
	}

	/**
	 * @param ageofdgpsdata the ageofdgpsdata to set
	 */
	public void setAgeofdgpsdata(Double ageofdgpsdata) {
		this.ageofdgpsdata = ageofdgpsdata;
	}

	/**
	 * @return the dgpsid
	 */
	public Integer getDgpsid() {
		return dgpsid;
	}

	/**
	 * @param dgpsid the dgpsid to set
	 */
	public void setDgpsid(Integer dgpsid) {
		this.dgpsid = dgpsid;
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
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public Double getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(Double lon) {
		this.lon = lon;
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
	 * @return the trackSegment
	 */
	public TrackSegment getTrackSegment() {
		return trackSegment;
	}

	/**
	 * @param trackSegment the trackSegment to set
	 */
	public void setTrackSegment(TrackSegment trackSegment) {
		this.trackSegment = trackSegment;
	}
    
	
    
}
