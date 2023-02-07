package org.erick.payment.batch.processor;

import org.erick.payment.domain.PaymentTransaction;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionsProcessor {

	@Bean
	public FunctionItemProcessor<PaymentTransaction, String> transactionProcessor() {
		return new FunctionItemProcessor<PaymentTransaction, String>(itens -> {
			return itens.toString();
		});
	}
}
