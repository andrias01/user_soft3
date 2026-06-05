package com.eatup.userservice.messaging.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.eatup.userservice.service.user.UserCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserCommandConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCommandConsumer.class);

	private final UserCommandService userCommandService;
	private final ObjectMapper objectMapper;

	public UserCommandConsumer(UserCommandService userCommandService) {
		this.userCommandService = userCommandService;
		this.objectMapper = JsonMapper.builder().findAndAddModules().build();
	}

	@RabbitListener(queues = "${rabbitmq.queue.user}")
	public void consume(String payload) {
		try {
			UserCommandMessage message = objectMapper.readValue(payload, UserCommandMessage.class);
			switch (message.getAction()) {
				case CREATE -> userCommandService.createUser(message.getCreateRequest());
				case UPDATE -> userCommandService.updateUser(message.getUserId(), message.getUpdateRequest());
				case UPDATE_STATUS -> userCommandService.updateStatus(message.getUserId(), message.getStatus());
				default -> throw new IllegalArgumentException("Unsupported user command");
			}
		} catch (Exception ex) {
			LOGGER.error("Failed to process user command payload {}", payload, ex);
		}
	}
}