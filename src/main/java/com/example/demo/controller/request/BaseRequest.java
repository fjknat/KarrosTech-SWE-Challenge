/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created By Created Date Version Reason Thai Phung Oct 10, 2020 1.0 Initialize
 */

public class BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3954191362272482708L;

	@Email(message = "User Name is email format.")
	@Size(min = 5, max = 100, message = "User Name can't be empty and less than 100 characters.")
	@NotNull(message = "User Name isn't null which is email format.")
	private String userName;
	

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	
	
}
