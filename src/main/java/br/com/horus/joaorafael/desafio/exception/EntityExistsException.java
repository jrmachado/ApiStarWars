package br.com.horus.joaorafael.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityExistsException extends Exception  {

	private static final long serialVersionUID = 1L;

	 public EntityExistsException(String msgErro) {
	        super(msgErro);
	    }
}
