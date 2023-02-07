package org.erick.payment.message;

import org.erick.payment.domain.PaymentTransaction;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MessagePayment {
	
	private final static String QUEUE_NAME = "transactionsPayment";
	@Value("${rabbitmq.user}")
	private String RABBIT_MQ_USER;
	@Value("${rabbitmq.password}")
	private String RABBIT_MQ_PASSWORD;
	@Value("${rabbitmq.address}")
	private String RABBIT_MQ_ADDRESS;
	
	@Autowired
	private ConnectionFactory rabbitConnectionFactory;
	
	@Bean
	public ErrorHandler errorHandler() {
		return new ConditionalRejectingErrorHandler();
	}

	@Bean
	public Queue transactionsQueue() {
		return new Queue(QUEUE_NAME, true);
	}
	
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("paymentExchange");
    }

    @Bean
	public RabbitTemplate getTransactionsQueueTemplate() {
		RabbitTemplate template = new RabbitTemplate(this.rabbitConnectionFactory);
		template.setConnectionFactory(connectionFactory());
		template.setDefaultReceiveQueue(QUEUE_NAME);
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("com.systrip");
	}

	public MessageConverter jsonMessageConverter() {
		ObjectMapper om = new ObjectMapper();
		Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter(om);
		DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
		defaultClassMapper.setDefaultType(PaymentTransaction.class);
		messageConverter.setClassMapper(defaultClassMapper);
		return messageConverter;
	}

	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(RABBIT_MQ_ADDRESS);
		connectionFactory.setUsername(RABBIT_MQ_USER);
		connectionFactory.setPassword(RABBIT_MQ_PASSWORD);
		return connectionFactory;
	}
	
}
