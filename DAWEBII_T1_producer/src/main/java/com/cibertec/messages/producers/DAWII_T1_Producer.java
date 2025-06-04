package com.cibertec.messages.producers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cibertec.models.DragonBall;
import com.google.gson.Gson;

@Service
public class DAWII_T1_Producer {
	// Declaración de atributos
	private final RabbitTemplate rabbitTemplate;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final static Logger LOGGER = LogManager.getLogger(DAWII_T1_Producer.class);

	@Value("${app.rabbitmq.queue.dawii-t1-queue}")
	private String queue;

	@Value("${app.kafka.topic.dawii-t1-topic}")
	private String topic;

	// Constructor
	public DAWII_T1_Producer(RabbitTemplate rabbitTemplate, KafkaTemplate<String, String> kafkaTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(DragonBall personaje) {
		String personajeJSON = new Gson().toJson(personaje);

		// Enviar mensaje a RabbitMQ
		rabbitTemplate.convertAndSend(queue, personajeJSON);
		LOGGER.info("Mensaje enviado a RabbitMQ: {}", personajeJSON);

		// Enviar mensaje a Kafka
		kafkaTemplate.send(topic, personajeJSON);
		LOGGER.info("Mensaje enviado a Kafka: {}", personajeJSON);
	}

}
