package com.erick.trip.message;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.erick.trip.util.Utils;

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