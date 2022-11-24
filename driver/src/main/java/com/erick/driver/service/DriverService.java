package com.erick.driver.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.driver.domain.Driver;
import com.erick.driver.domain.DriverAvailability;
import com.erick.driver.repository.DriverRepository;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private MessageService messageService;
	
	@Transactional
	public Driver save(Driver driver) {
		return driverRepository.save(driver);
	}
	
	public Driver findById(Integer id) {
		return driverRepository.findById(id).orElse(null);
	}
	
	public void sendAvailability(DriverAvailability driverAvailability) {
		messageService.sendMessage(driverAvailability);
	}

	public List<Driver> findAll() {
		return (List<Driver>) driverRepository.findAll();
	}

}