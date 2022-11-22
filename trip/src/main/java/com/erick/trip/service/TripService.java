package com.erick.trip.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.trip.cache.service.DriverCacheService;
import com.erick.trip.cache.service.PassengerCacheService;
import com.erick.trip.domain.driver.DriverAvailability;
import com.erick.trip.domain.driver.DriverCache;
import com.erick.trip.domain.passenger.PassengerCache;
import com.erick.trip.domain.passenger.TripRequestPassenger;
import com.erick.trip.domain.travel.StatusTravel;
import com.erick.trip.domain.travel.Travel;
import com.erick.trip.domain.travel.TravelStatusHistory;
import com.erick.trip.repository.TravelRepository;
import com.erick.trip.repository.TravelStatusHistoryRepository;

@Service
public class TripService {

	@Autowired
	private DriverCacheService driverCacheService;
	@Autowired
	private PassengerCacheService passengerCacheService;
	@Autowired
	private TravelRepository travelRepository;
	@Autowired
	private TravelStatusHistoryRepository travelStatusHistoryRepository;
	
	public void startTrip(PassengerCache passengerCache, DriverCache driverCache) {
		System.out.println("Trip started. Passenger ID: " + passengerCache.getId() + " Driver ID: " + driverCache.getId());
		Travel travel = new Travel();
		travel.setIdDriver(driverCache.getId());
		travel.setIdPassenger(passengerCache.getId());
		travelRepository.save(travel);
		TravelStatusHistory travelStatusHistory = new TravelStatusHistory();
		travelStatusHistory.setStatus(StatusTravel.STARTED);
		travelStatusHistory.setDatetimeStatus(LocalDateTime.now());
		travelStatusHistory.setTravel(travel);
		travelStatusHistoryRepository.save(travelStatusHistory);
	}
	
	public void matchTrip() {
		System.out.println("Matching trips...");
		List<PassengerCache> passengersWaiting = passengerCacheService.getPassengersWaiting();
		System.out.println(passengersWaiting.toString());
		System.out.println("Passengers waiting for trip: " + passengersWaiting.size());
		passengersWaiting.stream().forEach(passenger -> {
			System.out.println("Finding driver for " + passenger.getAddressOrigin() + " and " + passenger.getDistrict());
			DriverCache driverCache = driverCacheService.getDriverAvailaibleForPassenger(passenger.getAddressOrigin(), passenger.getDistrict());
			if (driverCache != null) {
				startTrip(passenger, driverCache);
				passengerCacheService.delete(passenger.getId());
			}
		});
	}
	
	public void driverSignalAvailability(DriverAvailability driverAvailability) {
		DriverCache driverCache = driverCacheService.fromDriverAvailability(driverAvailability);
		driverCacheService.signalDriverAvailability(driverCache);		
	}
	
	public void passengerRequestTrip(TripRequestPassenger tripRequestPassenger) {
		PassengerCache passengerCache = passengerCacheService.fromTripRequest(tripRequestPassenger);
		DriverCache driverCache = driverCacheService.getDriverAvailaibleForPassenger(passengerCache.getAddressdestiny(), passengerCache.getDistrict());
		if (driverCache != null) {
			startTrip(passengerCache, driverCache);
		} else {
			passengerCacheService.putPassengerOnHold(passengerCache);
		}
	}
	
}
