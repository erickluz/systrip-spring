package com.erick.driver.setup;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupMessage {
	private final static String QUEUE_NAME = "driverTrip";
	private final static String TOPIC_EXCHANGE_NAME = "driverExchange";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("com.systrip");
	}
	
	@Bean
	public CachingConnectionFactory defaultConnectionFactory() {
	    CachingConnectionFactory cf = new CachingConnectionFactory();
	    cf.setUsername("adm");
	    cf.setPassword("adm");
	    return cf;
	}

}
