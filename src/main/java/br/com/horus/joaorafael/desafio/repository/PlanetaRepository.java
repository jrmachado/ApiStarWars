package br.com.horus.joaorafael.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.horus.joaorafael.desafio.entity.Planeta;

@Repository
@Transactional
public interface PlanetaRepository extends JpaRepository<Planeta, Integer>{
	
	Optional<Planeta> findByNome(String nome);
	
	Optional<Planeta> findById(Integer id);

}
