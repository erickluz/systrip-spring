package org.erick.payment.batch.step;

import org.erick.payment.domain.PaymentTransaction;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionsStep {
	
	private final static int INTERVAL_COMMIT = 1;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step stepTransaction(ItemReader<PaymentTransaction> reader, FunctionItemProcessor<PaymentTransaction, String>  processor, ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepTransaction")
				. <PaymentTransaction, String> chunk(INTERVAL_COMMIT)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();			
	}
	
}
