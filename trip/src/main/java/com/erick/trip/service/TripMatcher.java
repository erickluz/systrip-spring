package com.erick.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TripMatcher {
	
	@Autowired
	private TripService tripService;
	
	@Scheduled(fixedRate = 5000)
	public void match() {
		tripService.matchTrip();
	}

}