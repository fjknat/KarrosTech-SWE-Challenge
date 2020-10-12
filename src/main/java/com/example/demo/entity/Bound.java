/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */


@Entity
@Table(name = "BOUND")
@JsonInclude(Include.NON_EMPTY)
public class Bound extends BaseEntitySoftDelete{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6299898463265317721L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "MIN_LAT")
	protected Double minlat;
	
	@Column(name = "MIN_LON")
    protected Double minlon;
	
	@Column(name = "MAX_LAT")
    protected Double maxlat;
	
	@Column(name = "MAX_LON")    
    protected Double maxlon;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "METADATA_ID", nullable = false)
	@JsonIgnore
	private Metadata metadata;

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
	 * @return the minlat
	 */
	public Double getMinlat() {
		return minlat;
	}

	/**
	 * @param minlat the minlat to set
	 */
	public void setMinlat(Double minlat) {
		this.minlat = minlat;
	}

	/**
	 * @return the minlon
	 */
	public Double getMinlon() {
		return minlon;
	}

	/**
	 * @param minlon the minlon to set
	 */
	public void setMinlon(Double minlon) {
		this.minlon = minlon;
	}

	/**
	 * @return the maxlat
	 */
	public Double getMaxlat() {
		return maxlat;
	}

	/**
	 * @param maxlat the maxlat to set
	 */
	public void setMaxlat(Double maxlat) {
		this.maxlat = maxlat;
	}

	/**
	 * @return the maxlon
	 */
	public Double getMaxlon() {
		return maxlon;
	}

	/**
	 * @param maxlon the maxlon to set
	 */
	public void setMaxlon(Double maxlon) {
		this.maxlon = maxlon;
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

	
}
