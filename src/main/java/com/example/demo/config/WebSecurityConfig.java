/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.config;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Value("${security.enable-csrf}")
//    private boolean csrfConfig;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().anyRequest().authenticated()
////		.and().formLogin().disable();
////		http.authorizeRequests().anyRequest().permitAll();
//		http.authorizeRequests()
//        .antMatchers("/**")
//        .permitAll();
//		if (!csrfConfig) {
//			http.csrf().disable();
//		}
//	}
//}
public class WebSecurityConfig{}
