package com.infy.isa.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerAddress {
	@NotEmpty(message = "address must not be blank.")
	@Size(max=25,message="address length should be max 25 char")
	private String address;
	@NotEmpty(message = "city must not be blank.")
	@Pattern(regexp="[a-zA-z\\s]+", message = "city must not contain any special char other than white spaces")
	private String city;
	@NotEmpty(message = "pincode must not be blank.")
	@Pattern(regexp="\\d{6}", message = "pin should be of 6 digits")
	private String pincode;
	@NotEmpty(message = "state must not be blank.")
	private String state;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
