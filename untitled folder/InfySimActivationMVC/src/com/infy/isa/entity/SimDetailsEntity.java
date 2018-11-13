package com.infy.isa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="simdetails")
public class SimDetailsEntity {
	@Id
	private int simId;
	private int serviceNumber;
	private long simNumber;
	private String simStatus;
	public SimDetailsEntity(){
		
	}
	public SimDetailsEntity(int simId,int serviceNumber,long simNumber,String simStatus){
		this.simId=simId;
		this.serviceNumber=serviceNumber;
		this.simNumber=simNumber;
		this.simStatus=simStatus;
		
	}
	public int getSimId() {
		return simId;
	}

	public void setSimId(int simId) {
		this.simId = simId;
	}

	public int getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(int serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public long getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(long simNumber) {
		this.simNumber = simNumber;
	}

	public String getSimStatus() {
		return simStatus;
	}

	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}

}
