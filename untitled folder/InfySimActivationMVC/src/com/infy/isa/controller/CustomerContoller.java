package com.infy.isa.controller;

import java.text.ParseException;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infy.isa.entity.CustomerAddressEntity;
import com.infy.isa.entity.CustomerEntity;
import com.infy.isa.entity.SimDetailsEntity;
import com.infy.isa.exception.InvalidDetails;
import com.infy.isa.model.CustomerAddress;
import com.infy.isa.model.CustomerBasicDetails;
import com.infy.isa.model.CustomerPersonalDetails;
import com.infy.isa.service.CustomerService;

@RestController
@RequestMapping("customer")
@SessionAttributes("customer")
public class CustomerContoller {
	
	@Autowired
	CustomerService customerService;
	
	CustomerEntity customerEntity;
	@RequestMapping(value = "/validation", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Boolean> verifyCustomerDetails(@Valid @RequestBody CustomerBasicDetails customerDetails,BindingResult result,HttpSession session)
			throws JsonProcessingException, InvalidDetails {
		System.out.println("inside customer controller");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidDetails(errorMessage.toString());
		}
		else {
			try {
			System.out.println(customerDetails.getDateOfBirth());
			System.out.println(customerDetails.getEmailAddress());
			SimDetailsEntity simDetails=(SimDetailsEntity)session.getAttribute("simDetails");
			System.out.println("simId: "+simDetails.getSimId());
			CustomerEntity customerEntity=customerService.findCustomer(customerDetails.getDateOfBirth(), customerDetails.getEmailAddress(),simDetails.getSimId());
			if(customerEntity==null) {
				throw new InvalidDetails("No request placed for you");
			}
			else {
				System.out.println("inside success");
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}}
			
			catch (ParseException e) {
				System.out.println(e.getMessage());
				throw new InvalidDetails("unable to parse date");
			}
		}
		
		
		
	}
	
	@RequestMapping(value = "/identification", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Boolean> verifyCustomerIdentification(@Valid @RequestBody CustomerPersonalDetails customerDetails,BindingResult result,HttpSession session)
			throws JsonProcessingException, InvalidDetails {
		System.out.println("inside customer identification controller");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidDetails(errorMessage.toString());
		}
		else {
			SimDetailsEntity simDetails=(SimDetailsEntity)session.getAttribute("simDetails");
			customerEntity=customerService.findCustomerIdentity(customerDetails.getFirstName(), customerDetails.getLastName(),simDetails.getSimId());
		if(customerEntity==null) {
			throw new InvalidDetails("Invalid Details");
		}
		else if(!customerEntity.getEmailAddress().equals(customerDetails.getEmailAddress())) {
			throw new InvalidDetails("Invalid email Details");
		}
		else {
			System.out.println("inside success of customer identification");
			session.setAttribute("customer", customerEntity);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}}
		
		
	}
	
	@RequestMapping(value = "/addressValidation", method = RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<CustomerAddressEntity> verifyCustomerAddress(@Valid @RequestBody CustomerAddress customerAddress,BindingResult result,HttpSession session)
			throws JsonProcessingException, InvalidDetails {
		System.out.println("inside customer address controller");
		if(result.hasErrors()) {
			StringBuilder errorMessage=new StringBuilder();
			for(ObjectError x: result.getAllErrors()) {
				errorMessage.append(x.getDefaultMessage()+"\n");
			}
			throw new InvalidDetails(errorMessage.toString());
		}
		else {
			System.out.println("getting customer entity");
			//CustomerEntity customerEntity=(CustomerEntity)session.getAttribute("customer");
			if(customerEntity==null) {
				System.out.println("succssfully got entity");
			}
			CustomerAddressEntity customerAddressEntity=new CustomerAddressEntity();
			customerAddressEntity.setAddressId(customerEntity.getCustomerAddress().getAddressId());
			customerAddressEntity.setAddress(customerAddress.getAddress());
			customerAddressEntity.setCity(customerAddress.getCity());
			customerAddressEntity.setPincode(Integer.parseInt(customerAddress.getPincode()));
			customerAddressEntity.setState(customerAddress.getState());
			customerService.updateOrAddCustomerAddress(customerAddressEntity);
			return new ResponseEntity<CustomerAddressEntity>(customerAddressEntity,HttpStatus.OK);
		}}
		
}

