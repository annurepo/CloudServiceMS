package com.cs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cs.exception.CloudProviderNotFoundException;
import com.cs.model.CloudProvider;
import com.cs.repositories.CloudProviderRepository;

@Service
public class CloudProviderServiceImpl implements CloudProviderService {

	private CloudProviderRepository CloudProviderrepo;

	public CloudProviderServiceImpl(CloudProviderRepository CloudProviderrepo) {
		this.CloudProviderrepo = CloudProviderrepo;

	}

	@Override
	public String createCloudProvider(CloudProvider CloudProviderObj) {

		this.CloudProviderrepo.save(CloudProviderObj);
		return "success";

	}

	@Override
	public String updateCloudProvider(CloudProvider CloudProviderObj) {

		this.CloudProviderrepo.save(CloudProviderObj);
		return "success";
	}

	@Override
	public String deleteCloudProvider(String CloudProviderId) {

		this.CloudProviderrepo.deleteById(CloudProviderId);
		return "success";

	}

	@Override
	public CloudProvider getCloudProvider(String CloudProviderId) {

		if(this.CloudProviderrepo.findById(CloudProviderId).isEmpty())
            throw new CloudProviderNotFoundException("Requested Cloud Vendor does not exist");
		return this.CloudProviderrepo.findById(CloudProviderId).get();
	}

	@Override
	public List<CloudProvider> getAllCloudProviders() {
		return this.CloudProviderrepo.findAll();
	}

	@Override
	public List<CloudProvider> getByVendorName(String vendorName) {

		return this.CloudProviderrepo.findByVendorName(vendorName);
	}

}
