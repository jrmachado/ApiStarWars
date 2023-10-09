package br.com.horus.joaorafael.desafio.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaSWApiResponse {

	private String name;

	private List<String> films;

	public PlanetaSWApiResponse(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}
}
