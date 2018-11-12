package com.infy.isa.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerID {
	
	private String idType;
	@NotEmpty(message = "aadhar id must not be blank.")
	@Pattern(regexp="\\d{16}", message = "aadhar id should be of 16 digits")
	private String uniqueIdNumber;
	@NotEmpty(message = "firstName must not be blank.")
	private String firstName;
	@NotEmpty(message = "lastName must not be blank.")
	private String lastName;
	@NotEmpty(message = "state must not be blank.")
	private String state;
	@NotEmpty(message = "date of birth must not be blank.")
	@Pattern(regexp="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "date format not correct")
	private String dateOfBirth;
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getUniqueIdNumber() {
		return uniqueIdNumber;
	}
	public void setUniqueIdNumber(String uniqueIdNumber) {
		this.uniqueIdNumber = uniqueIdNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

}
