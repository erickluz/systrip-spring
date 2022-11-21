package com.erick.passenger.service;


import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	private final RabbitTemplate rabbitTemplate;
	private final MessageReceiver receiver;

	public MessageService(MessageReceiver receiver, RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.receiver = receiver;
	}

	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(null, "foo.bar.baz", "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}
	
	public void sendMessage(Object message) {
		rabbitTemplate.convertAndSend(message);
	}

}
