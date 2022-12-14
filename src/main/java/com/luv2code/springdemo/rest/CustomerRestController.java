package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return customerService.getCustomers();
		
	}
		//add mapping for Get /Customer /{customerid}
	@GetMapping("/customers/{customerId}")
public  Customer getCustomer (@PathVariable int customerId){
		Customer theCustomer= customerService.getCustomer(customerId);
		if(theCustomer==null){
			throw  new CustomerNotFoundException("Customer id not found"+ customerId);
		}
		return theCustomer;
	}
	//add mapping for post /Customer /{customerid}

	@PostMapping("/customers")
	public Customer addCustomer (@RequestBody Customer theCustomer){
		//also just in case the pass an id in json set id
		//to 0 this is force to save new item instead
		//of upadte
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return  theCustomer;
	}
	//add mapping for put /Customer /{customerid}
@PutMapping("/customers")
		public Customer updateCustomer (@RequestBody Customer theCustomer){
			customerService.saveCustomer(theCustomer);
			return theCustomer;
		}

		//add mapping for delete by id
@DeleteMapping("/customers/{customerId}")
public String deleteCustomer (@PathVariable int customerId){
	Customer tempCustomer= customerService.getCustomer(customerId);
		if(tempCustomer==null){
		throw  new CustomerNotFoundException("Customer id not found"+ customerId);
	}
		customerService.deleteCustomer(customerId);
		return "deleted " + customerId;
}

}




















