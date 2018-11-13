package com.infy.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.isa.entity.SimDetailsEntity;
import com.infy.isa.entity.SimOffersEntity;
import com.infy.isa.repository.OfferRepository;
import com.infy.isa.repository.SimRepository;

@Service
public class SimService {
	@Autowired
	private SimRepository simRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	public SimDetailsEntity findSimDetails(String simNumber,String serviceNumber) {
		int serviceNumberVal=Integer.parseInt(serviceNumber);
		long simNumberVal=Long.parseLong(simNumber);
		return simRepository.findByServiceNumberAndSimNumber(serviceNumberVal, simNumberVal);
	}
	
	public SimOffersEntity findOffer(Long simNumber) {
		return offerRepository.findBySim_SimNumber(simNumber);
	}
	public void activateSim(long simNumber) {
		simRepository.activateSim("active",simNumber);
	
	}
}
