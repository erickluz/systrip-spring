package com.erick.trip;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TripApplication {
	
	@Value("${rabbitmq.address}")
	private String RABBIT_MQ_ADDRESS;
	@Value("${rabbitmq.user}")
	private String RABBIT_MQ_USER;
	@Value("${rabbitmq.password}")
	private String RABBIT_MQ_PASSWORD;

	public static void main(String[] args) {
		SpringApplication.run(TripApplication.class, args);
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
