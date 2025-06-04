package com.cibertec.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cibertec.models.DragonBall;

@Service
public class DAWII_T1_DragonBallService {
	// Declaración de atributos
	private final RestTemplate restTemplate = new RestTemplate();

	// Métodos
	public DragonBall fetchCharacter(int id) {
		String url = "https://dragonball-api.com/api/characters/" + id;
		DragonBall character = restTemplate.getForObject(url, DragonBall.class);

		if (character != null) {
			System.out.println("Character fetched: " + character.getNombre());
		} else {
			System.out.println("Character not found.");
		}

		return character;
	}
}
