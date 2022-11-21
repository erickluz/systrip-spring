package com.erick.trip.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.trip.domain.driver.DriverAvailability;
import com.erick.trip.service.TripService;
import com.erick.trip.util.Utils;

@Service
public class MessageDriverConsumer {
	
	@Autowired
	private TripService tripService;
	
	@RabbitListener(queues = {"${rabbitmq.queue.driver}"})
	public void onMessage(String message) {
	  DriverAvailability driverAvailability = (DriverAvailability) Utils.fromJson(message, DriverAvailability.class);
	  tripService.driverSignalAvailability(driverAvailability);
	}

}
