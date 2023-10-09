package br.com.horus.joaorafael.desafio.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsApiResponse {

	private List<PlanetaSWApiResponse> results;

	public StarWarsApiResponse(List<PlanetaSWApiResponse> results, String name) {
		this.results = results;
	}
}
