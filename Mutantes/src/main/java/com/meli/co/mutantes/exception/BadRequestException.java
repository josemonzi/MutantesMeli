package com.meli.co.mutantes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;
	private String error; 

	public BadRequestException(String mensaje) {
		super(mensaje);
	}
	
	public BadRequestException(String error, HttpStatus statusCode ) {
		super(error);
		this.setStatusCode(statusCode);
		this.setError(error);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
