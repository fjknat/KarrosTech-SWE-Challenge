/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */

@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomJsonRootName {
	String singular(); 
    String plural(); 
}
