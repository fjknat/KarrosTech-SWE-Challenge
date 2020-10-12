/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.HealthRestController;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

@RestController
public class HealthRestControllerImpl implements HealthRestController {

	@Override
	public String healthCheck() {
		return "OK";
	}

}
