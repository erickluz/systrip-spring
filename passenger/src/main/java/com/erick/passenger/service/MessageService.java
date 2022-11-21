package com.erick.passenger.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.erick.passenger.util.Utils;

@Service
public class MessageService {
	private final RabbitTemplate rabbitTemplate;

	public MessageService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Object object) {
		rabbitTemplate.convertAndSend("passengerExchange", "com.systrip", Utils.toJson(object));
	}

}
