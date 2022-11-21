package com.erick.passenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.passenger.domain.Passenger;
import com.erick.passenger.domain.TripRequest;
import com.erick.passenger.repository.PassengerRepository;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private MessageService messageService;
	
	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	public Passenger findById(Integer id) {
		return passengerRepository.findById(id).orElse(null);
	}
	
	public void requestTrip(TripRequest tripRequest) {
		messageService.sendMessage(tripRequest);
	}
	
}
