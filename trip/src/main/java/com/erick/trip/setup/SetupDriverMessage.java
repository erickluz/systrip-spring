package com.erick.trip.setup;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupDriverMessage {
	private final static String QUEUE_NAME = "driverTrip";
	private final static String TOPIC_EXCHANGE_NAME = "driverExchange";

	
	@Bean
	public Queue queueDriver() {
	  return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	public TopicExchange exchangeDriver() {
	  return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}
	
	@Bean
	public Binding bindingDriver() {
		return BindingBuilder.bind(queueDriver()).to(exchangeDriver()).with("com.systrip.driver");
	}

}