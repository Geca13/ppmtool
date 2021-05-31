package com.example.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseHendler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public final ResponseEntity<?> handleException(IdentifierException ex, WebRequest request){
		IdentifierExceptionResponse response = new IdentifierExceptionResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

}
