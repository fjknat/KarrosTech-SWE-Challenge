/*******************************************************************************
 * Copyright (C) 2020, Thai Phung
 * 
 * This is the Karros's demo project which develop by Thai Phung.
 ******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.08 at 12:14:25 PM ICT 
//


package com.example.demo.xml.element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 9, 2020			1.0				Initialize
 */

@XmlRegistry
public class ObjectFactory {

    private final static QName _Gpx_QNAME = new QName("http://www.topografix.com/GPX/1/1", "gpx");

    /** 
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.demo.entity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GpxType }
     * 
     */
    public GpxType createGpxType() {
        return new GpxType();
    }

    /**
     * Create an instance of {@link TrkType }
     * 
     */
    public TrkType createTrkType() {
        return new TrkType();
    }

    /**
     * Create an instance of {@link BoundsType }
     * 
     */
    public BoundsType createBoundsType() {
        return new BoundsType();
    }

    /**
     * Create an instance of {@link CopyrightType }
     * 
     */
    public CopyrightType createCopyrightType() {
        return new CopyrightType();
    }

    /**
     * Create an instance of {@link RteType }
     * 
     */
    public RteType createRteType() {
        return new RteType();
    }

    /**
     * Create an instance of {@link TrksegType }
     * 
     */
    public TrksegType createTrksegType() {
        return new TrksegType();
    }

    /**
     * Create an instance of {@link EmailType }
     * 
     */
    public EmailType createEmailType() {
        return new EmailType();
    }

    /**
     * Create an instance of {@link PtType }
     * 
     */
    public PtType createPtType() {
        return new PtType();
    }

    /**
     * Create an instance of {@link PtsegType }
     * 
     */
    public PtsegType createPtsegType() {
        return new PtsegType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link WptType }
     * 
     */
    public WptType createWptType() {
        return new WptType();
    }

    /**
     * Create an instance of {@link ExtensionsType }
     * 
     */
    public ExtensionsType createExtensionsType() {
        return new ExtensionsType();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link MetadataType }
     * 
     */
    public MetadataType createMetadataType() {
        return new MetadataType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GpxType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.topografix.com/GPX/1/1", name = "gpx")
    public JAXBElement<GpxType> createGpx(GpxType value) {
        return new JAXBElement<GpxType>(_Gpx_QNAME, GpxType.class, null, value);
    }

}
