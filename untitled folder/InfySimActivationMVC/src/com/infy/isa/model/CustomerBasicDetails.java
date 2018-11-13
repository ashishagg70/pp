package com.infy.isa.model;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerBasicDetails {
	
	@NotEmpty(message = "email must not be blank.")
	@Pattern(regexp=".+@[a-zA-Z]+?\\.[a-zA-Z]{2,3}", message = "email format not correct")
	private String emailAddress;
	
	@NotEmpty(message = "date of birth must not be blank.")
	@Pattern(regexp="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "date format not correct")
	private String dateOfBirth;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	
}
