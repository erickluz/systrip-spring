package com.erick.trip.cache.repository;

import org.springframework.data.repository.CrudRepository;

import com.erick.trip.domain.driver.DriverCache;

public interface DriverCacheRepository extends CrudRepository<DriverCache, Integer> {
	
	DriverCache findByAddressOrDistrict(String address, String district);
	
	
}
