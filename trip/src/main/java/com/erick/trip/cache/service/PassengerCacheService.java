package com.erick.trip.cache.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.trip.cache.repository.PassengerCacheRepository;
import com.erick.trip.domain.passenger.PassengerCache;
import com.erick.trip.domain.passenger.TripRequestPassenger;

@Service
public class PassengerCacheService {
	
	@Autowired
	private PassengerCacheRepository passengerCacheRepository;

	public void putPassengerOnHold(PassengerCache passenger) {
		passengerCacheRepository.save(passenger);
	}
	
	public PassengerCache fromTripRequest(TripRequestPassenger tripRequestPassenger) {
		return new PassengerCache(tripRequestPassenger.getIdPassenger(), 
				tripRequestPassenger.getAddressOrigin(), tripRequestPassenger.getAddressDestiny(), tripRequestPassenger.getDistrict());
	}

	public List<PassengerCache> getPassengersWaiting() {
		List<PassengerCache> passengers = new ArrayList<>();
		passengerCacheRepository.findAll().iterator().forEachRemaining(passengers::add);
		return passengers;
	}

	public void delete(Integer id) {
		passengerCacheRepository.deleteById(id);		
	}
	
}
