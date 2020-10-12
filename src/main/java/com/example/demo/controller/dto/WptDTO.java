/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */
@JsonInclude(Include.NON_EMPTY)
public class WptDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4172813437687520909L;
	protected Double ele;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
	private Instant time;    
    protected Double magvar;
    protected Double geoidheight;
//    protected String name;
//    protected String cmt;
//    protected String desc;
//    protected String src;
//    protected String sym;
    protected String type;
    protected String fix;
    protected Integer sat;
    protected Double hdop;
    protected Double vdop;
    protected Double pdop;
    protected Double ageofdgpsdata;    
    protected Integer dgpsid;    
    protected Double lat;
    protected Double lon;
	public Double getEle() {
		return ele;
	}
	public void setEle(Double ele) {
		this.ele = ele;
	}
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
	public Double getMagvar() {
		return magvar;
	}
	public void setMagvar(Double magvar) {
		this.magvar = magvar;
	}
	public Double getGeoidheight() {
		return geoidheight;
	}
	public void setGeoidheight(Double geoidheight) {
		this.geoidheight = geoidheight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	public Integer getSat() {
		return sat;
	}
	public void setSat(Integer sat) {
		this.sat = sat;
	}
	public Double getHdop() {
		return hdop;
	}
	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}
	public Double getVdop() {
		return vdop;
	}
	public void setVdop(Double vdop) {
		this.vdop = vdop;
	}
	public Double getPdop() {
		return pdop;
	}
	public void setPdop(Double pdop) {
		this.pdop = pdop;
	}
	public Double getAgeofdgpsdata() {
		return ageofdgpsdata;
	}
	public void setAgeofdgpsdata(Double ageofdgpsdata) {
		this.ageofdgpsdata = ageofdgpsdata;
	}
	public Integer getDgpsid() {
		return dgpsid;
	}
	public void setDgpsid(Integer dgpsid) {
		this.dgpsid = dgpsid;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
    
    
    
    
}
