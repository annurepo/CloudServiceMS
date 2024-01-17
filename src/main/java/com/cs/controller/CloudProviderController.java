package com.cs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.model.CloudProvider;
import com.cs.response.CloudProviderResponseHandler;
import com.cs.services.CloudProviderService;

@RestController
@RequestMapping("api/v1")
public class CloudProviderController 
{
	
	private CloudProviderService CloudProviderservice;

	public CloudProviderController(CloudProviderService cloudProviderservice) {
		CloudProviderservice = cloudProviderservice;
	}
	
	
	
	 // Read Specific Cloud Vendor Details from DB
    @GetMapping("/{vendorId}")
    public ResponseEntity<Object> getCloudProviderDetails(@PathVariable("vendorId") String vendorId)
    {
       return CloudProviderResponseHandler.responseBuilder("Requested Vendor Details are given here",
                HttpStatus.OK, this.CloudProviderservice.getCloudProvider(vendorId));
    }
	
    
    @GetMapping("/getall")
    public List<CloudProvider> getAllCloudProviderDetails()
    {
        return this.CloudProviderservice.getAllCloudProviders();
    }

    @PostMapping("/add")
    public String createCloudProviderDetails(@RequestBody CloudProvider CloudProviderObj)
    {
    	this.CloudProviderservice.createCloudProvider(CloudProviderObj);
        return "Cloud Vendor Created Successfully";
    }

    @PutMapping("/update")
    public String updateCloudProviderDetails(@RequestBody CloudProvider CloudProviderObj)
    {
    	this.CloudProviderservice.updateCloudProvider(CloudProviderObj);
        return "Cloud Vendor Updated Successfully";
    }
    
    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
    	this.CloudProviderservice.deleteCloudProvider(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }
}
