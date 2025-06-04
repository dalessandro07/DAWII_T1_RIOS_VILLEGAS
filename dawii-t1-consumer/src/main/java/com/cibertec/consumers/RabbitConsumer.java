package com.cibertec.consumers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.cibertec.models.DragonBall;
import com.cibertec.repositories.IDragonBallRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitConsumer {
	// Atributos
	private IDragonBallRepository repository;
	private final static Logger LOGGER = LogManager.getLogger(RabbitConsumer.class);

	// Constructor
	public RabbitConsumer(IDragonBallRepository repository) {
		this.repository = repository;
	}

	// Métodos
	@RabbitListener(queues = "DragonBall")
	public void consume(String message) {
		try {
			DragonBall personaje = new ObjectMapper().readValue(message, DragonBall.class);
			// Debemos establecer el ID a null para que se genere uno nuevo
			// Esto debido a la restricción de @GeneratedValue(strategy =
			// GenerationType.IDENTITY)

			/*
			 * COMENTANDO RABBIT MQ PARA EVITAR QUE SE DUPLIQUEN LOS PERSONAJES
			 */

			// personaje.setId(null);
			// this.repository.save(personaje);
		} catch (Exception e) {
			LOGGER.error("Error al procesar el mensaje (RabbitMQ): " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
