package br.com.horus.joaorafael.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class RestTemplateException extends Exception{

	private static final long serialVersionUID = 1L;

	public RestTemplateException(String msgErro) {
		super(msgErro);
	}

}
