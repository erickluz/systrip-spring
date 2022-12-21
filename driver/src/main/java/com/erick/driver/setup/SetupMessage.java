package com.erick.driver.setup;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupMessage {
	private final static String QUEUE_NAME = "driverTrip";
	private final static String TOPIC_EXCHANGE_NAME = "driverExchange";
	@Value("${rabbitmq.address}")
	private String RABBIT_MQ_ADDRESS;
	@Value("${rabbitmq.user}")
	private String RABBIT_MQ_USER;
	@Value("${rabbitmq.password}")
	private String RABBIT_MQ_PASSWORD;
	
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
	    cf.setUsername(RABBIT_MQ_USER);
	    cf.setPassword(RABBIT_MQ_PASSWORD);
	    cf.setAddresses(RABBIT_MQ_ADDRESS);
	    return cf;
	}

}
