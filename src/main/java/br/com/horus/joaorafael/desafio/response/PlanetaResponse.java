package br.com.horus.joaorafael.desafio.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetaResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String clima;
	private String terreno;
	private int qtdAparicoes;

}
