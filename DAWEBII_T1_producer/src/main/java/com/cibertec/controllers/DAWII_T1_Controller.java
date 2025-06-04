package com.cibertec.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.messages.producers.DAWII_T1_Producer;
import com.cibertec.models.DragonBall;
import com.cibertec.services.DAWII_T1_DragonBallService;

@Controller
public class DAWII_T1_Controller {
	// Declaración de atributos
	private static final Logger LOGGER = LogManager.getLogger(DAWII_T1_Controller.class);
	private final DAWII_T1_DragonBallService service;
	private final DAWII_T1_Producer producer;

	// Constructor
	public DAWII_T1_Controller(DAWII_T1_DragonBallService service, DAWII_T1_Producer producer) {
		this.producer = producer;
		this.service = service;
	}

	@GetMapping
	public String showIndex(Model model) {
		return "index";
	}

	@PostMapping("/buscar")
	public String buscarDragonBall(@RequestParam("id") String id, Model model) {
		LOGGER.info("Buscando Dragon Ball con ID: {}", id);
		DragonBall dragonBall = service.fetchCharacter(Integer.parseInt(id.trim()));

		if (dragonBall != null) {
			model.addAttribute("dragonBall", dragonBall);
			producer.sendMessage(dragonBall);
		} else {
			model.addAttribute("error", "Dragon Ball no encontrado");
		}

		return "index";
	}

}
