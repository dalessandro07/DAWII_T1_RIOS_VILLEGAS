package com.cibertec.consumers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cibertec.models.DragonBall;
import com.cibertec.repositories.IDragonBallRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {
	// Atributos
	private IDragonBallRepository repository;
	private final static Logger LOGGER = LogManager.getLogger(KafkaConsumer.class);

	// Constructor
	public KafkaConsumer(IDragonBallRepository repository) {
		this.repository = repository;
	}

	// Métodos
	@KafkaListener(topics = "DragonBall", groupId = "dragonball-group")
	public void consume(String message) {
		try {
			DragonBall personaje = new ObjectMapper().readValue(message, DragonBall.class);
			// Debemos establecer el ID a null para que se genere uno nuevo
			// Esto debido a la restricción de @GeneratedValue(strategy =
			// GenerationType.IDENTITY)
			personaje.setId(null);
			this.repository.save(personaje);
		} catch (Exception e) {
			LOGGER.error("Error al procesar el mensaje: " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
