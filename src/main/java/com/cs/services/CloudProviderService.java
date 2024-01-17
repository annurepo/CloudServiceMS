package com.cs.services;

import java.util.List;

import com.cs.model.CloudProvider;

public interface CloudProviderService 
{
	public String createCloudProvider(CloudProvider CloudProviderObj);
    
 	public String updateCloudProvider(CloudProvider CloudProviderObj);
    
 	public String deleteCloudProvider(String CloudProviderId);
    
 	public CloudProvider getCloudProvider(String CloudProviderId);
    
 	public List<CloudProvider> getAllCloudProviders();
    
 	public List<CloudProvider> getByVendorName(String vendorName);

}
