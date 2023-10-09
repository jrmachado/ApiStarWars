package br.com.horus.joaorafael.desafio.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.horus.joaorafael.desafio.dto.PlanetaDto;
import br.com.horus.joaorafael.desafio.exception.BadRequestException;
import br.com.horus.joaorafael.desafio.exception.EntityExistsException;
import br.com.horus.joaorafael.desafio.exception.ResourceNotFoundException;
import br.com.horus.joaorafael.desafio.exception.RestTemplateException;
import br.com.horus.joaorafael.desafio.response.PlanetaResponse;

@SpringBootTest
public class PlanetaServiceTest {

	@MockBean
	private PlanetaService planetaService;


	@Test
	public void criarPlaneta() {

		try {

			PlanetaResponse planeta = PlanetaResponse.builder().id(10).nome("Blu").clima("Teste").terreno("Teste")
					.qtdAparicoes(4).build();

			PlanetaDto dto = new PlanetaDto();
			dto.setNome("Blu");
			dto.setClima("Teste");
			dto.setTerreno("Teste");

			when(planetaService.criarPlaneta(dto)).thenReturn(planeta);

			PlanetaResponse planetaRetorno = planetaService.criarPlaneta(dto);
			Assert.assertEquals(planetaRetorno.getNome(), planeta.getNome());
		} catch (EntityExistsException e) {
			Assert.fail(e.getMessage());
		} catch (BadRequestException e) {
			Assert.fail(e.getMessage());
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

		try {
			when(planetaService.listarPlanetas()).thenReturn(planetas);
			
			List<PlanetaResponse> planetasRetorno = planetaService.listarPlanetas();
			Assert.assertEquals(planetasRetorno.get(0).getNome(), planeta1.getNome());
			
		} catch (RestTemplateException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void buscarPorId() {
		PlanetaResponse planeta = PlanetaResponse.builder().id(10).nome("Teste10").clima("Teste").terreno("Teste").qtdAparicoes(1).build();
		
		try {
			when(planetaService.buscarPorId(planeta.getId())).thenReturn(planeta);
			
			PlanetaResponse planetasRetorno = planetaService.buscarPorId(planeta.getId());
			Assert.assertEquals(planeta, planetasRetorno);
			
		} catch (ResourceNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (RestTemplateException e) {
			Assert.fail(e.getMessage());
		}
	}

}
