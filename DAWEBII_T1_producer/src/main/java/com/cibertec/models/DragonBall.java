package com.cibertec.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DragonBall {

	@Id
	private Integer id;

	@JsonProperty("name")
	private String nombre;

	private String ki;

	@JsonProperty("maxKi")
	private String max_ki;

	@JsonProperty("race")
	private String raza;

	@JsonProperty("gender")
	private String genero;

	@JsonProperty("description")
	private String descripcion;
}
