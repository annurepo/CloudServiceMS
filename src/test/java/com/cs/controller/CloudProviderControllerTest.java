package com.cs.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cs.model.CloudProvider;
import com.cs.services.CloudProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(CloudProviderController.class)
public class CloudProviderControllerTest 
{
	
	 @Autowired
	    private MockMvc mockMvc;
	    @MockBean
	    private CloudProviderService cloudproviderservice;
	    CloudProvider cloudProviderOne;
	    CloudProvider cloudProviderTwo;
	    List<CloudProvider> CloudProviderList= new ArrayList<>();

	    
	    @BeforeEach
	    void setUp() {
	    	cloudProviderOne = new CloudProvider("1", "Amazon",
	                "USA", "xxxxx");
	    	cloudProviderTwo = new CloudProvider("2", "GCP",
	                "UK", "yyyyy");
	        CloudProviderList.add(cloudProviderOne);
	        CloudProviderList.add(cloudProviderTwo);
	    }

	    
	    
	    @AfterEach
	    void tearDown() {
	    }

	    @Test
	    void getCloudProviderDetails() throws Exception {
	        when(cloudproviderservice.getCloudProvider("1")).thenReturn(cloudProviderOne);
	        this.mockMvc.perform(get("/api/v1/" + "1")).andDo(print()).andExpect(status().isOk());
	    }

	    @Test
	    void getAllCloudProviderDetails() throws  Exception {
	        when(cloudproviderservice.getAllCloudProviders()).thenReturn(CloudProviderList);
	        this.mockMvc.perform(get("/api/v1/getall"))
	                .andDo(print()).andExpect(status().isOk());
	    }
	    
	    @Test
	    void createCloudProviderDetails() throws Exception {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	        String requestJson=ow.writeValueAsString(cloudProviderOne);

	        when(cloudproviderservice.createCloudProvider(cloudProviderOne)).thenReturn("Success");
	        this.mockMvc.perform(post("/api/v1/add")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(requestJson))
	                .andDo(print()).andExpect(status().isOk());
	    }

	    @Test
	    void updateCloudProviderDetails() throws Exception {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	        String requestJson=ow.writeValueAsString(cloudProviderOne);

	        when(cloudproviderservice.updateCloudProvider(cloudProviderOne))
	                .thenReturn("Cloud Vendor Updated Successfully");
	        this.mockMvc.perform(put("/api/v1/update")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(requestJson))
	                .andDo(print()).andExpect(status().isOk());
	    }

	    @Test
	    void deleteCloudProviderDetails() throws Exception {
	        when(cloudproviderservice.deleteCloudProvider("1"))
	                .thenReturn("Cloud Vendor Deleted Successfully");
	        this.mockMvc.perform(delete("/api/v1/" + "1"))
	                .andDo(print()).andExpect(status().isOk());

	    }
}
