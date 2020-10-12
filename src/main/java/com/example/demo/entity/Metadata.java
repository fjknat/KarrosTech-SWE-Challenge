/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

/**
 * 
 */
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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020				1.0				Initialize
 */

@Entity
@Table(name = "METADATA")
@JsonInclude(Include.NON_EMPTY)
public class Metadata extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6300855255319078656L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "NAME", length = 200)
	private String name;
	
	@Column(name = "DESC", length = 2000)
	private String desc;

    @Column(name = "DATE_POST")
    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private Instant datePost;
    
    @Column(name = "KEYWORDS", length = 2000)
    private String keywords;
    
    @Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy = "metadata", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Transient
    @JsonProperty("author")
	private Person person;
    
    @Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy = "metadata", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Transient
	private Copyright copyright;
    
    @Fetch(FetchMode.JOIN)    
	@OneToMany(mappedBy = "metadata", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Transient
	private List<Link> links;
        
    @Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy = "metadata", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Transient
	private Bound bound;
    
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "GPS_ID")
    @JsonIgnore
    private Gps gps;
    
    /*
     * Json value of dynamic object instead of Extensions table
     */
    @Column(name = "EXTENSIONS", length = 10000)
    private String extensions;

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

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * @return the copyrights
	 */
	public Copyright getCopyright() {
		return copyright;
	}

	/**
	 * @param copyrights the copyrights to set
	 */
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
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
	 * @return the bounds
	 */
	public Bound getBound() {
		return bound;
	}

	/**
	 * @param bounds the bounds to set
	 */
	public void setBound(Bound bound) {
		this.bound = bound;
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
    
	
    
}
