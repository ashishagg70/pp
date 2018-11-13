package com.infy.isa.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="customeridentity")
@Entity
public class CustomerIdentityEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long uniqueIdNumber;
	
	@OneToOne(mappedBy="customerIdentity", cascade=CascadeType.ALL)
    private CustomerEntity customer;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String state;
	public long getUniqueIdNumber() {
		return uniqueIdNumber;
	}
	public void setUniqueIdNumber(long uniqueIdNumber) {
		this.uniqueIdNumber = uniqueIdNumber;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
