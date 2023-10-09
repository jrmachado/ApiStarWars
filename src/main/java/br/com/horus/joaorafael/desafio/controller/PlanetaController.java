package br.com.horus.joaorafael.desafio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.horus.joaorafael.desafio.dto.PlanetaDto;
import br.com.horus.joaorafael.desafio.exception.BadRequestException;
import br.com.horus.joaorafael.desafio.exception.EntityExistsException;
import br.com.horus.joaorafael.desafio.exception.ResourceNotFoundException;
import br.com.horus.joaorafael.desafio.exception.RestTemplateException;
import br.com.horus.joaorafael.desafio.response.PlanetaResponse;
import br.com.horus.joaorafael.desafio.service.PlanetaService;

@RestController
@RequestMapping(value = "/api/sw/planeta", produces = { "application/json" })
@CrossOrigin("*")
public class PlanetaController {

	@Autowired
	private PlanetaService service;

	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<PlanetaResponse> criarPlaneta(@RequestBody PlanetaDto planetaDto)
			throws BadRequestException, EntityExistsException {

		PlanetaResponse planeta = service.criarPlaneta(planetaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planeta.getId())
				.toUri();
		return ResponseEntity.created(uri).body(planeta);
	}

	@GetMapping("/listar")
	@Transactional
	public ResponseEntity<List<PlanetaResponse>> listarPlanetas() throws RestTemplateException {
		return ResponseEntity.ok().body(service.listarPlanetas());
	}

	@GetMapping("/buscarPorNome")
	@Transactional
	public ResponseEntity<PlanetaResponse> buscarPorNome(@RequestParam(value = "nome", defaultValue = "") String nome)
			throws ResourceNotFoundException, RestTemplateException {
		return ResponseEntity.ok().body(service.buscarPorNome(nome));
	}

	@GetMapping("/buscarPorId/{id}")
	@Transactional
	public ResponseEntity<PlanetaResponse> buscarPorId(@PathVariable("id") Integer id)
			throws ResourceNotFoundException, RestTemplateException {
		return ResponseEntity.ok().body(service.buscarPorId(id));
	}

	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Integer id) throws ResourceNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
