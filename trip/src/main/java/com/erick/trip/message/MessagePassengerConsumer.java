package com.erick.trip.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.trip.domain.passenger.TripRequestPassenger;
import com.erick.trip.service.TripService;
import com.erick.trip.util.Utils;

@Service
public class MessagePassengerConsumer {
	
	@Autowired
	private TripService tripService;
	
	@RabbitListener(queues = {"${rabbitmq.queue.passenger}"})
	public void onMessage(String message) {
	  TripRequestPassenger tripRequestPassenger = (TripRequestPassenger) Utils.fromJson(message, TripRequestPassenger.class);
	  tripService.passengerRequestTrip(tripRequestPassenger);
	}

}
