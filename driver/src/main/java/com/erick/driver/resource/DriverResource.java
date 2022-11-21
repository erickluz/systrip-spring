package com.erick.driver.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erick.driver.domain.Driver;
import com.erick.driver.domain.DriverAvailability;
import com.erick.driver.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverResource {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping
	public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {
		driverService.save(driver);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Driver> findDriverById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(driverService.findById(id));
	}
	
	@PostMapping("/trip")
	public ResponseEntity<Void> signalAvailability(@RequestBody DriverAvailability driverAvailability) {
		driverService.sendAvailability(driverAvailability);
		return ResponseEntity.noContent().build();
	}

}