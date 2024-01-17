package com.cs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cs.model.CloudProvider;
import com.cs.repositories.CloudProviderRepository;

public class CloudProviderServiceImplTest 
{
	
 		@Mock
 		private CloudProviderRepository CloudProviderrepo;
	    private CloudProviderService cloudproviderservice;
	    AutoCloseable autoCloseable;
	    CloudProvider CloudProviderObj;

	    
	    @BeforeEach
	    void setUp() {
	        autoCloseable = MockitoAnnotations.openMocks(this);
	        cloudproviderservice = new CloudProviderServiceImpl(CloudProviderrepo);
	        CloudProviderObj = new CloudProvider("1", "Amazon",
	                "USA", "xxxxx");
	    }

	    @AfterEach
	    void tearDown() throws Exception {
	        autoCloseable.close();
	    }
	    
	    
	    
	    @Test
	    void testCreateCloudProvider() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class);

	        when(CloudProviderrepo.save(CloudProviderObj)).thenReturn(CloudProviderObj);
	        assertThat(cloudproviderservice.createCloudProvider(CloudProviderObj)).isEqualTo("success");

	    }
	    
	    
	    @Test
	    void testUpdateCloudProvider() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class);

	        when(CloudProviderrepo.save(CloudProviderObj)).thenReturn(CloudProviderObj);
	        assertThat(cloudproviderservice.updateCloudProvider(CloudProviderObj)).isEqualTo("success");
	    }

	    @Test
	    void testDeleteCloud() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class, Mockito.CALLS_REAL_METHODS);
	        
	        doAnswer(Answers.CALLS_REAL_METHODS).when(CloudProviderrepo)
	                .deleteById(any());
	        assertThat(cloudproviderservice.deleteCloudProvider("1")).isEqualTo("success");
	    }
	    
	    
	    
	    @Test
	    void testGetCloudProvider() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class);

	        when(CloudProviderrepo.findById("1")).thenReturn(Optional.ofNullable(CloudProviderObj));

	        assertThat(cloudproviderservice.getCloudProvider("1").getVendorName())
	                .isEqualTo(CloudProviderObj.getVendorName());
	    }

	    @Test
	    void testGetByProviderName() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class);

	        when(CloudProviderrepo.findByVendorName("Amazon")).
	                thenReturn(new ArrayList<CloudProvider>(Collections.singleton(CloudProviderObj)));

	        assertThat(cloudproviderservice.getByVendorName("Amazon").get(0).getVendorId()).
	                isEqualTo(CloudProviderObj.getVendorId());
	    }

	    @Test
	    void testGetAllCloudProviders() {
	        mock(CloudProvider.class);
	        mock(CloudProviderRepository.class);

	        when(CloudProviderrepo.findAll()).thenReturn(new ArrayList<CloudProvider>(
	                Collections.singleton(CloudProviderObj)
	        ));

	        assertThat(cloudproviderservice.getAllCloudProviders().get(0).getVendorPhoneNumber()).
	                isEqualTo(CloudProviderObj.getVendorPhoneNumber());

	    }	    
}