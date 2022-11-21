package com.erick.trip.setup;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupPassengerMessage {
	private final static String QUEUE_NAME = "passengerTrip";
	private final static String TOPIC_EXCHANGE_NAME = "passengerExchange";
	
	@Bean
	public Queue queuePassenger() {
	  return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	public TopicExchange exchangePassenger() {
	  return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}
	
	@Bean
	public Binding bindingPassenger() {
		return BindingBuilder.bind(queuePassenger()).to(exchangePassenger()).with("com.systrip.passenger");
	}

	
}