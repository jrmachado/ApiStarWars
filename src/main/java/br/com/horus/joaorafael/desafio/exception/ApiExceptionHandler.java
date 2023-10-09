package br.com.horus.joaorafael.desafio.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(BadRequestException ex , WebRequest request ){
		ErroResponse errorDetails =
              new ErroResponse(new Date(), HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<?> entityExistsException(EntityExistsException ex , WebRequest request ){
		ErroResponse errorDetails =
              new ErroResponse(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
		ErroResponse errorDetails =
                new ErroResponse(new Date(), HttpStatus.NOT_FOUND.toString(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(RestTemplateException.class)
    public ResponseEntity<?> restTemplateException(
    		RestTemplateException ex, WebRequest request) {
		ErroResponse errorDetails =
                new ErroResponse(new Date(), HttpStatus.SERVICE_UNAVAILABLE.toString(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE);
    }

	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		ErroResponse errorDetails =
                new ErroResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString() ,ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
