package com.eatup.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRabbitMqConfig {

	@Value("${rabbitmq.exchange.user}")
	private String exchangeName;

	@Value("${rabbitmq.queue.user}")
	private String queueName;

	@Value("${rabbitmq.routing-key.user}")
	private String routingKey;

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);
		admin.initialize();
		return admin;
	}

	@Bean
	public DirectExchange userExchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	public Queue userQueue() {
		return QueueBuilder.durable(queueName).build();
	}

	@Bean
	public Binding userBinding(Queue userQueue, DirectExchange userExchange) {
		return BindingBuilder.bind(userQueue).to(userExchange).with(routingKey);
	}
}