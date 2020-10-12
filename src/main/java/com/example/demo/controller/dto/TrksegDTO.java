/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */

public class TrksegDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6503401077817801312L;
	protected List<WptDTO> trkpt;

	/**
	 * @return the trkpt
	 */
	public List<WptDTO> getTrkpt() {
		return trkpt;
	}

	/**
	 * @param trkpt the trkpt to set
	 */
	public void setTrkpt(List<WptDTO> trkpt) {
		this.trkpt = trkpt;
	}
	
	
}
