/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 11, 2020				1.0				Initialize
 */
@JsonInclude(Include.NON_EMPTY)
public class TrkDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8532796550742336070L;

	private TrkDTO() {}
	
    protected Integer number;
    protected String type;
    protected List<TrksegDTO> trkseg;       
	
	public Integer getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public List<TrksegDTO> getTrkseg() {
		return trkseg;
	}

	public static TrkDTOBuilder builder() {
		return new TrkDTOBuilder();
	}
    
	public static class TrkDTOBuilder{
		protected Integer number;
	    protected String type;
	    protected List<TrksegDTO> trkseg;	    
	    
		public TrkDTOBuilder number(Integer number) {
			this.number = number;
			return this;
		}

		public TrkDTOBuilder type(String type) {
			this.type = type;
			return this;
		}

		public TrkDTOBuilder trkseg(List<TrksegDTO> trkseg) {
			this.trkseg = trkseg;
			return this;
		}


		public TrkDTO build() {
			TrkDTO trkDTO = new TrkDTO();
			trkDTO.number = this.number;
			trkDTO.trkseg = this.trkseg;
			trkDTO.type = this.type;
			return trkDTO;
		}
	    
	}
}
