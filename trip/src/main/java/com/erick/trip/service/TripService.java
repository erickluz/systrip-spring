package com.erick.trip.service;

import org.springframework.stereotype.Service;

import com.erick.trip.domain.driver.DriverAvailability;
import com.erick.trip.domain.passenger.TripRequestPassenger;

@Service
public class TripService {

	public void startTrip() {
		
	}
	
	public void matchTrip() {
		System.out.println("Matching Driver and Passenger...");
	}
	
	public void driverSignalAvailability(DriverAvailability driverAvailability) {
		System.out.println("Motorista em espera de passageiro. " + driverAvailability.toString());
	}
	
	public void passengerRequestTrip(TripRequestPassenger tripRequestPassenger) {
		System.out.println("Passageiro em espera de motorista. " + tripRequestPassenger.toString());
	}
	
}
