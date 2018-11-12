package com.infy.isa.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infy.isa.entity.CustomerIdentityEntity;
import com.infy.isa.exception.InvalidDetails;
import com.infy.isa.model.CustomerID;
import com.infy.isa.service.CustomerService;

@RestController
@RequestMapping("idproof")
public class IDController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/validation", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Boolean> verifyCustomerId(@Valid @RequestBody CustomerID customerId,BindingResult result,HttpSession session)
			throws JsonProcessingException, InvalidDetails {
		System.out.println("inside idproof controller");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidDetails(errorMessage.toString());
		}
		else {
		CustomerIdentityEntity customerIdentityEntity=customerService.findCustomerIdentity(customerId.getFirstName(), customerId.getLastName(),Long.parseLong( customerId.getUniqueIdNumber()));
			
		if(customerIdentityEntity==null) {
			throw new InvalidDetails("Invalid Id details");
		}
		else {
			System.out.println("inside success");
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}}
		
		
	}


}
