package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//the advice
@ControllerAdvice
public class CustomerRestExceptionHandler {
    //add an exception hadnler for customerNotFoundException
    @Autowired
    private CustomerService customerService;

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {

        //create CustomerErrorResponse
CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
        //return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    //add mappiung for post customers



}