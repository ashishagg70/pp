package com.infy.isa.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerPersonalDetails {
	@NotEmpty(message = "email must not be blank.")
	@Pattern(regexp=".+@[a-zA-Z]+?\\.[a-zA-Z]{2,3}", message = "email format not correct")
	private String emailAddress;
	
	@NotEmpty(message = "lastName must not be blank")
	@Size(max=15 , message = "Firstname/Lastname should be maximum of 15 characters")
	private String lastName;
	
	@NotEmpty(message = "firstName must not be blank")
	@Size(max=15 , message = "Firstname/Lastname should be maximum of 15 characters")
	private String firstName;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	

}
