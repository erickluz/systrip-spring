package com.erick.trip.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.trip.cache.repository.DriverCacheRepository;
import com.erick.trip.domain.driver.DriverAvailability;
import com.erick.trip.domain.driver.DriverCache;

@Service
public class DriverCacheService {
	
	@Autowired
	private DriverCacheRepository driverCacheRepository;

	public DriverCache getDriverAvailaibleForPassenger(String addressdestiny, String district) {
		return driverCacheRepository.findByAddressOrDistrict(addressdestiny, district);
	}
	
	public void signalDriverAvailability(DriverCache driverCache) {
		driverCacheRepository.save(driverCache);
	}
	
	public DriverCache fromDriverAvailability(DriverAvailability driverAvailability) {
		return new DriverCache(driverAvailability.getIdDriver(), driverAvailability.getCurrentAddress(), driverAvailability.getDistrict(), 1L);
	}
	
}
