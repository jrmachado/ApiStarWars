package br.com.horus.joaorafael.desafio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.horus.joaorafael.desafio.dto.PlanetaDto;
import br.com.horus.joaorafael.desafio.response.PlanetaResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioApplicationTests {

	protected final String BASE_PATH = "http://localhost:";

	protected final String API = "/api/sw/planeta";

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void criarPlaneta() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Tatooinaa");
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta,
				String.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		restTemplate.delete(response.getHeaders().getLocation());

	}

	@Test
	public void criarPlanetaNomeVazio() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("");
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void criarPlanetaClimaVazio() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Todos");
		planeta.setClima("");
		planeta.setTerreno("Teste");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void criarPlanetaTerrenoVazio() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Todos");
		planeta.setClima("Teste");
		planeta.setTerreno("");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void criarPlanetaNomeNulo() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void criarPlanetaClimaNulo() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Todos");
		planeta.setTerreno("Teste");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void criarPlanetaTerrenoNulo() {

		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Todos");
		planeta.setClima("Teste");
		try {
			restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, PlanetaResponse.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void buscaPorId() {
		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Taatto1");
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta,
				String.class);

		ResponseEntity<String> respostaBusca = restTemplate.getForEntity(response.getHeaders().getLocation(),
				String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());

		restTemplate.delete(response.getHeaders().getLocation());
	}

	@Test
	public void buscaPorIdInesistente() {
		try {
			restTemplate.getForEntity(BASE_PATH + port + API + "/buscarPorId/331", String.class);
		} catch (Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
		}
	}

	@Test
	public void buscaPorNome() {
		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("Tato");
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");

		restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta, String.class);
		ResponseEntity<String> respostaBusca = restTemplate
				.getForEntity(BASE_PATH + port + API + "/buscarPorNome?nome=Tato", String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());
	}

	@Test
	public void buscaPorNomeInesistente() {
		try {
			restTemplate.getForEntity(BASE_PATH + port + API + "/buscarPorNome?nome=RuaTeste", String.class);
		} catch (Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
		}
	}

	@Test
	public void listarPlanetas() {
		PlanetaResponse planeta1 = PlanetaResponse.builder().id(1).nome("Teste1").clima("Teste").terreno("Teste").qtdAparicoes(1).build();
		PlanetaResponse planeta2 = PlanetaResponse.builder().id(2).nome("Teste2").clima("Teste").terreno("Teste").qtdAparicoes(3).build();
		PlanetaResponse planeta3 = PlanetaResponse.builder().id(3).nome("Teste3").clima("Teste").terreno("Teste").qtdAparicoes(6).build();

		List<PlanetaResponse> planetas = new ArrayList<PlanetaResponse>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);

		ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH + port + API + "/listar", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void delete() {
		PlanetaDto planeta = new PlanetaDto();
		planeta.setNome("PlanDelet1");
		planeta.setClima("Teste");
		planeta.setTerreno("Teste");
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_PATH + port + API + "/criar", planeta,
				String.class);

		ResponseEntity<String> respostaBusca = restTemplate.exchange(response.getHeaders().getLocation().toString(),
				HttpMethod.DELETE, criarHeader(), String.class, response.getBody());
		Assert.assertEquals(204, respostaBusca.getStatusCodeValue());
	}

	@Test
	public void deleteIdInesistente() {
		try {
			restTemplate.exchange(BASE_PATH + port + API + "/delete/1234", HttpMethod.DELETE, criarHeader(),
					String.class);
		} catch (Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
		}
	}

	private HttpEntity<String> criarHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

}
