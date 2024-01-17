package com.cs.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cs.model.CloudProvider;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CloudProviderRepositoryTest {


	
	@Autowired
    private CloudProviderRepository CloudProviderrepo;
    CloudProvider CloudProviderObj;

    @BeforeEach
    void setUp() {
        CloudProviderObj = new CloudProvider("1","Amazon",
                "USA", "xxxxx");
        CloudProviderrepo.save(CloudProviderObj);
    }

    @AfterEach
    void tearDown() {
        CloudProviderObj = null;
        CloudProviderrepo.deleteAll();
    }

    // Test case SUCCESS

    @Test
    void testFindByVendorName_Found()
    {
        List<CloudProvider> CloudProviderList = CloudProviderrepo.findByVendorName("Amazon");
        assertThat(CloudProviderList.get(0).getVendorId()).isEqualTo(CloudProviderObj.getVendorId());
        assertThat(CloudProviderList.get(0).getVendorAddress())
                .isEqualTo(CloudProviderObj.getVendorAddress());
    }

    // Test case FAILURE
    @Test
    void testFindByVendorName_NotFound()
    {
        List<CloudProvider> CloudProviderList = CloudProviderrepo.findByVendorName("GCP");
        assertThat(CloudProviderList.isEmpty()).isTrue();
    }
}
