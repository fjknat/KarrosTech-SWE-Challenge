/*******************************************************************************
 * Copyright (C) 2020, Thai Phung
 * 
 * This is the Karros's demo project which develop by Thai Phung.
 ******************************************************************************/
package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.example.demo.annotation.ExecutionTimeLogging;

@SpringBootApplication
@EnableCaching
public class DemoApplication {
	private static final Logger logger = LogManager.getLogger(DemoApplication.class);
	
	@ExecutionTimeLogging
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
