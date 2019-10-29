package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.rest.exceptions.CustomerNotFoundException;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);
		
		// keep in mind that if customer is not not, database returns null.
		// jackson returns empty body for null objects.
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return theCustomer;
	}
	
	/*
	 * @RequestBody Customer theCustomer
	 * 
	 * This annotation will grab the body from the request and turn into a Customer object for us.
	 * 
	 */
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
	
		/*  	theCustomer.setId(0);
		 * we need this because when when get to the DAO, it will check to see if ID = null.
		 * If ID == null it will do an insert.
		 * If ID != null, it will do an update on that object.
		 * 
		 */
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
	
		/*
		 * Notice we're actually including the ID here
		 * So inside the DAO it will call saveOrUpdate, and actually update the customer
		 */
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id - " + customerId;
		
	}
	

}
