package com.erick.driver.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.erick.driver.util.Utils;

@Component
public class MessageService {
	private final RabbitTemplate rabbitTemplate;

	public MessageService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Object object) {
		rabbitTemplate.convertAndSend("driverExchange", "com.systrip", Utils.toJson(object));
	}

}
