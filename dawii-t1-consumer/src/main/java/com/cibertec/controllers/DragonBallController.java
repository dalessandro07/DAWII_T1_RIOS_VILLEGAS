package com.cibertec.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.models.DragonBall;
import com.cibertec.repositories.IDragonBallRepository;

@Controller
@RequestMapping("/personajes")
public class DragonBallController {
	// Atributos
	private static final Logger LOGGER = LogManager.getLogger(DragonBallController.class);
	private final IDragonBallRepository repository;

	// Constructor
	public DragonBallController(IDragonBallRepository repository) {
		this.repository = repository;
	}

	// Métodos
	@GetMapping
	public String listar(org.springframework.ui.Model model) {
		List<DragonBall> personajes = new ArrayList<>();

		try {
			repository.findAll().forEach(personajes::add);
			LOGGER.info("Listando {} personajes de Dragon Ball.", personajes.size());
		} catch (Exception e) {
			LOGGER.error("Error al listar personajes: ", e);
			throw new RuntimeException("Error al listar personajes", e);
		}

		model.addAttribute("personajes", personajes);
		return "personajes";
	}

	@PostMapping
	public String agregar(DragonBall personaje, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("personajes", repository.findAll());
			return "personajes";
		}

		try {
			repository.save(personaje);
			LOGGER.info("Personaje agregado: " + personaje.getNombre());
			return "redirect:/personajes";
		} catch (Exception e) {
			LOGGER.error("Error al agregar personaje: ", e);
			throw new RuntimeException("Error al agregar personaje", e);
		}
	}
}
