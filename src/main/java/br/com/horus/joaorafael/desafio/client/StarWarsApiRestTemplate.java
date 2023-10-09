package br.com.horus.joaorafael.desafio.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.horus.joaorafael.desafio.exception.RestTemplateException;
import br.com.horus.joaorafael.desafio.response.StarWarsApiResponse;



@Service
public class StarWarsApiRestTemplate {

	final static String url = "https://swapi.dev/api/planets/";
	   
    RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<StarWarsApiResponse> getPlanetasApiStarWars() throws RestTemplateException {
		try { 
			return restTemplate.exchange(url, HttpMethod.GET,geraHeader(),StarWarsApiResponse.class);
   		}catch(Exception e) {
   			throw new RestTemplateException("SWAPI - Star Wars API fora do ar.");
   		}
   	}
	
	public HttpEntity<String> geraHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}
}
