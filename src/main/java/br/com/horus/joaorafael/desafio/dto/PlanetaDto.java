package br.com.horus.joaorafael.desafio.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlanetaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String clima;
	private String terreno;

}
