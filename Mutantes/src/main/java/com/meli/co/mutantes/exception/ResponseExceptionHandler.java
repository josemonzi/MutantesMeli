package com.meli.co.mutantes.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request){
		LOGGER.error("Error en el sistema, atrapado la excepción por manejarTodasExcepciones: " + ex.getMessage().toString());		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(ModeloNotFoundException.class)
	public final ResponseEntity<Object> ModeloNotFoundException(ModeloNotFoundException ex, WebRequest request) {
		LOGGER.error("Error en el sistema, atrapado la excepción por ModeloNotFoundException: " + ex.getMessage().toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> BadRequestException(BadRequestException ex, WebRequest request) {
		LOGGER.error("Error en el sistema, atrapado la excepción por BadRequestException: " + ex.getMessage().toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(ForbiddenException.class)
	public final ResponseEntity<Object> ForbiddenException(ForbiddenException ex, WebRequest request) {
		LOGGER.error("Es una cadena DNA de un humano.");				
		return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
	}	
}
