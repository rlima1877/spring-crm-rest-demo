package com.luv2code.springdemo.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * This is AOP stuff for Exception handling.
 * 
 * Call comes from client -> goes through exception AOP @ControllerAdvice -> 
 * Enter the controller method -> Controller method throws exception -> gets sent back to this class.
 * 
 */

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// Just like we can return to the client a List<Foo>, we can also return a ResponseEntity<CustomerErrorResponse>
	// ResponseEntity<CustomerErrorResponse> -- the object inside of the angle brackets is the RESPONSE BODY object.
	// remember ResponseEntity is a wrapper to the http response. It allows us to manipulate the response before returning.
	// CustomerNotFoundException ex is the exception that will fall into this method. Spring will do that for us.
	// We could add more exceptions by separating the parameters with commas or create a separate method if they differ in response.
	
	/*
	 * Catch all exception
	 * This will handle something like, entering a character in the URL string, where it's expecting a number. Such as below
	 * http://localhost:8080/spring-crm-rest//api/customers/osfodsjfisjfidsj
	 */
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
		
		CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	/*
	 * This will be caught when a client tries to query a customer that doesn't exist int the database.
	 * 
	 * Such as : http://localhost:8080/spring-crm-rest//api/customers/239403294320490920394
	 * 
	 */
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		
		CustomerErrorResponse errorResponse = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
