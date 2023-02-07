package org.erick.payment.batch.reader;

import org.erick.payment.domain.PaymentTransaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionsReader {

	@Bean
	public ItemReader<PaymentTransaction> readTransactions(RabbitTemplate rabbitTemplate) {
		System.out.println("Reading queue...");
		return new AmqpItemReader<PaymentTransaction> (rabbitTemplate);
	}

}
