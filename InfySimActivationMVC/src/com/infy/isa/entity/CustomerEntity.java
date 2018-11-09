package com.infy.isa.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="customer")
@Entity
public class CustomerEntity {
	
	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uniqueIdNumber", unique = true)
	private CustomerIdentityEntity customerIdentity;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar dateOfBirth;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String idType;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", unique = true)
	private CustomerAddressEntity customerAddress;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "simId", unique = true)
	private SimDetailsEntity sim;
	private String state;
	public CustomerIdentityEntity getCustomerIdentity() {
		return customerIdentity;
	}
	public void setCustomerIdentity(CustomerIdentityEntity customerIdentity) {
		this.customerIdentity = customerIdentity;
	}
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public CustomerAddressEntity getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(CustomerAddressEntity customerAddress) {
		this.customerAddress = customerAddress;
	}
	public SimDetailsEntity getSim() {
		return sim;
	}
	public void setSim(SimDetailsEntity sim) {
		this.sim = sim;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
