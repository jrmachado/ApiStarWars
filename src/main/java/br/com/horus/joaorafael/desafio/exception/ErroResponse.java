package br.com.horus.joaorafael.desafio.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroResponse {

	private Date dataHora;
	private String status;
	private String messagem;
	private String detalhe;
}
