package com.erick.passenger.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erick.passenger.domain.Passenger;
import com.erick.passenger.domain.TripRequest;
import com.erick.passenger.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerResource {
	@Autowired
	private PassengerService passengerService;
	
	@PostMapping
	public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger passenger) {
		passengerService.save(passenger);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Passenger> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(passengerService.findById(id));
	}
	
	@PostMapping("/trip")
	public ResponseEntity<Void> requestTrip(@RequestBody TripRequest tripRequest) {
		passengerService.requestTrip(tripRequest);
		return ResponseEntity.noContent().build();
	}

}
