package com.infy.isa.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ServiceDetails {
	@NotEmpty(message = "simNumber must not be blank.")
	@Pattern(regexp="\\d{13}", message = "simNumber must be 13 digits.")
	private String simNumber;
	
	@NotEmpty(message = "serviceNumber must not be blank.")
	@Pattern(regexp="\\d{10}", message = "serviceNumber must be 10 digits.")
	private String serviceNumber;

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	

}
