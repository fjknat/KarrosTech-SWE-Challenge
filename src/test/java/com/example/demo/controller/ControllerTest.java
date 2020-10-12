/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.response.UploadGpsFileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ControllerTest {

//	@Autowired
	private MockMvc mockMvc;
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private static String URL_UPLOAD_GPS_FILE = "/gps/upload";
	private static String URL_LATEST_TRACK = "/gps/latest";
	private static String URL_TRACK_DETAIL = "/gps/track";
	
	@Test
	@Order(1)
	@RepeatedTest(11)
	public void uploadGPSFile() throws Exception{
		InputStream inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("sample.gpx");
		//String gpsData = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		MockMultipartFile mockMultipartFile = new MockMultipartFile(
				"file", "sample.gpx",
				MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.multipart(URL_UPLOAD_GPS_FILE)
				.file(mockMultipartFile)
				.param("userName", "admin2@gmail.com")
				.param("trackTitle", "title GSP 2"))
//				.andExpect(status().isOk())
				.andReturn();		
		assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
        if(mvcResult.getResponse().getStatus() == 200) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				UploadGpsFileResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UploadGpsFileResponse.class);
				assertNotNull(response.getTrackInfoId());
			}catch (Exception e) {

			}
		}
	}
	
	@Test
	@Order(2)
	public void latestTrackList() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL_LATEST_TRACK)
				.param("pageSize", "10")
				.param("pageIndex", "0"))
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());		
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().length() > 1000);        
        
	}
	
	@Test
	@Order(3)
	public void trackDetail() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_TRACK_DETAIL)
				.queryParam("trackId", "1"))			
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());		
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().length() > 1000);        
        
	}
}
