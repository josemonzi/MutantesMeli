package com.meli.co.mutantes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;

		
	public ForbiddenException(HttpStatus statusCode ) {
		super();
		this.setStatusCode(statusCode);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
