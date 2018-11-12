package com.infy.isa.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="customer")
@Entity
public class CustomerEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private long uniqueIdNumber;

	@OneToOne
    @PrimaryKeyJoinColumn(name="uniqueIdNumber", referencedColumnName="uniqueIdNumber")
    private CustomerIdentityEntity customerIdentity;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
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
	public CustomerEntity() {
		//uniqueIdNumber=customerIdentity.getUniqueIdNumber();
	}
	public CustomerIdentityEntity getCustomerIdentity() {
		return customerIdentity;
	}
	public void setCustomerIdentity(CustomerIdentityEntity customerIdentity) {
		this.customerIdentity = customerIdentity;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
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
