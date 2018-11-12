package com.infy.isa.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.isa.entity.CustomerAddressEntity;
import com.infy.isa.entity.CustomerEntity;
import com.infy.isa.entity.CustomerIdentityEntity;
import com.infy.isa.model.CustomerAddress;
import com.infy.isa.repository.CustomerAddressRepository;
import com.infy.isa.repository.CustomerIdentityRepository;
import com.infy.isa.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	private CustomerIdentityRepository customerIdentityRepository;
	
	public CustomerEntity findCustomer(String date, String email,int simId) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//String Converteddate=new SimpleDateFormat("dd-MM-yyyy").format(format.parse(date));
		Date dateOfBirth=format.parse(date);
		CustomerEntity c= customerRepository.findByDateOfBirthAndEmailAddressAndSim_SimId(dateOfBirth, email, simId);
		System.out.println("customerEntity successfuly obtained");
		return c;
	}
	public CustomerEntity findCustomerIdentity(String firstName,String lastName,int simId) {
		return customerRepository.findByFirstNameAndLastNameAndSim_SimId(firstName, lastName, simId);
	}
	
	public void updateOrAddCustomerAddress(CustomerAddressEntity customerAddressEntity) {
		System.out.println(customerAddressEntity);
		customerAddressRepository.save(customerAddressEntity);
		System.out.println("customer successfuly saved");
	}
	
	public CustomerIdentityEntity findCustomerIdentity(String firstName, String lastName, Long id) {
		return customerIdentityRepository.findByFirstNameAndLastNameAndUniqueIdNumber(firstName, lastName, id);
		
	}
	
	
	

}
