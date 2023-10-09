package br.com.horus.joaorafael.desafio.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horus.joaorafael.desafio.client.StarWarsApiRestTemplate;
import br.com.horus.joaorafael.desafio.dto.PlanetaDto;
import br.com.horus.joaorafael.desafio.entity.Planeta;
import br.com.horus.joaorafael.desafio.exception.BadRequestException;
import br.com.horus.joaorafael.desafio.exception.EntityExistsException;
import br.com.horus.joaorafael.desafio.exception.ResourceNotFoundException;
import br.com.horus.joaorafael.desafio.exception.RestTemplateException;
import br.com.horus.joaorafael.desafio.repository.PlanetaRepository;
import br.com.horus.joaorafael.desafio.response.PlanetaResponse;
import br.com.horus.joaorafael.desafio.response.PlanetaSWApiResponse;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	@Autowired
	private StarWarsApiRestTemplate starWarsApiRestTemplate;
	
	private Calendar horaInicial = Calendar.getInstance(); 
	
	private List<PlanetaSWApiResponse> planetasSWApi = new ArrayList<PlanetaSWApiResponse>(); 

	public PlanetaResponse criarPlaneta(PlanetaDto dto) throws EntityExistsException, BadRequestException {

		validarPlaneta(dto);
		if(planetaRepository.findByNome(dto.getNome()).isEmpty()) {
			Planeta planeta = new Planeta();
			planeta.setNome(dto.getNome());
			planeta.setClima(dto.getClima());
			planeta.setTerreno(dto.getTerreno());
			
			planeta =  planetaRepository.save(planeta);
			
			return PlanetaResponse.builder()
					.id(planeta.getId())
					.nome(planeta.getNome())
					.clima(planeta.getClima())
					.terreno(planeta.getTerreno())
					.qtdAparicoes(encontraAparicao(this.planetasSWApi,planeta))
					.build();
		}else {
			throw new EntityExistsException("Já existe um Planeta com o nome: " + dto.getNome());
		}
	}
	
	public List<PlanetaResponse> listarPlanetas() throws RestTemplateException{
		List<PlanetaResponse> response = new ArrayList<PlanetaResponse>();
		List<Planeta> planetas = planetaRepository.findAll();
		this.planetasSWApi =  implementaCache(this.planetasSWApi, horaInicial);
		planetas.forEach(p -> response.add(PlanetaResponse.builder()
											.id(p.getId())
											.nome(p.getNome())
											.clima(p.getClima())
											.terreno(p.getTerreno())
											.qtdAparicoes(encontraAparicao(this.planetasSWApi,p))
											.build()
											));
		
		return response;
	}
	
	public PlanetaResponse buscarPorNome(String nome) throws ResourceNotFoundException, RestTemplateException {
		Optional<Planeta> optional = planetaRepository.findByNome(nome);
		
		if(optional.isPresent()) {
			this.planetasSWApi =  implementaCache(this.planetasSWApi, horaInicial);
			Planeta planeta =  optional.get();
			return PlanetaResponse.builder()
					.id(planeta.getId())
					.nome(planeta.getNome())
					.clima(planeta.getClima())
					.terreno(planeta.getTerreno())
					.qtdAparicoes(encontraAparicao(this.planetasSWApi,planeta))
					.build();
		}else {
			throw new ResourceNotFoundException("Planeta com o nome " + nome + " não encontrato.");
		}
	}

	public PlanetaResponse buscarPorId(Integer id) throws ResourceNotFoundException, RestTemplateException {
		Optional<Planeta> optional = planetaRepository.findById(id);
		if(optional.isPresent()) {
			this.planetasSWApi =  implementaCache(this.planetasSWApi, horaInicial);
			Planeta planeta =  optional.get();
			return PlanetaResponse.builder()
					.id(planeta.getId())
					.nome(planeta.getNome())
					.clima(planeta.getClima())
					.terreno(planeta.getTerreno())
					.qtdAparicoes(encontraAparicao(this.planetasSWApi,planeta))
					.build();
		}else {
			throw new ResourceNotFoundException("Planeta com o id " + id + " não encontrado.");
		}
	}
	
	public void delete(Integer id) throws ResourceNotFoundException {
		Optional<Planeta> optional = planetaRepository.findById(id);
		if(optional.isPresent()) {
			Planeta planeta =  optional.get();
			planetaRepository.delete(planeta);
		}else {
			throw new ResourceNotFoundException("Planeta com o id " + id + " não encontrado.");
		}
	}

	private void validarPlaneta(PlanetaDto dto) throws BadRequestException {

		if (dto == null) {
			throw new BadRequestException("Erro ao inserir null");
		}
		if (dto.getNome().isEmpty() || dto.getNome().equals(null)) {
			throw new BadRequestException("Campo nome vazio");
		}
		if (dto.getClima().isEmpty() || dto.getClima().equals(null)) {
			throw new BadRequestException("Campo clima vazio");
		}
		if (dto.getTerreno().isEmpty() || dto.getTerreno().equals(null)) {
			throw new BadRequestException("Campo terreno vazio");
		}
	}

	private int encontraAparicao(List<PlanetaSWApiResponse> planetasSWApi, Planeta planeta) {
		for(PlanetaSWApiResponse y: planetasSWApi ) {
			if(planeta.getNome().equals(y.getName())) {
				return y.getFilms().size();
			}
		}	
		return 0;
	}
	
	private List<PlanetaSWApiResponse> implementaCache(List<PlanetaSWApiResponse> planetasSWApi, Calendar horaInicial) throws RestTemplateException {
		Calendar atual = Calendar.getInstance(); 
		Calendar horaComparar = (Calendar) horaInicial.clone();
		horaComparar.add(Calendar.HOUR_OF_DAY, 1);
		if(planetasSWApi.isEmpty()) {
			planetasSWApi = starWarsApiRestTemplate.getPlanetasApiStarWars().getBody().getResults(); 
		}
		if(atual.after(horaComparar)) {
			planetasSWApi = starWarsApiRestTemplate.getPlanetasApiStarWars().getBody().getResults(); 
			horaInicial = Calendar.getInstance(); 
		}
		return planetasSWApi;
	}

}
