package com.cs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs.model.CloudProvider;

@Repository
public interface CloudProviderRepository extends JpaRepository<CloudProvider, String> {

	List<CloudProvider> findByVendorName(String vendorName);
}
