package com.luv2code.springdemo.rest.exceptions;

/*
 * This is a Custom exception that we are creating.
 * The reason being is because Spring doesn't this one, so we have to create it.
 * 
 * This exception will be thrown by the controller when the client sends a request with a Customer that doesn't exist in the DB.
 * 
 * Such as : http://localhost:8080/spring-crm-rest//api/customers/239403294320490920394
 * 
 * Inside the CustomerRestController.class, the controller method getCustomer() will check if database is returning null.
 * If it is, then it will return New CustomerNotFoundException
 * 
 * This exception will then get caught by the @ControllerAdvice class, via AOP.
 * And then fall into the appropriate method there.
 * 
 * Pretty cool!!!
 * 
 */

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
