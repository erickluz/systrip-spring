package org.erick.payment.batch.writer;


import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionsWriter {

	@Bean
	public ItemWriter<String> transactionWriter() {
		return itens -> itens.forEach(System.out::println);
	}	
}
