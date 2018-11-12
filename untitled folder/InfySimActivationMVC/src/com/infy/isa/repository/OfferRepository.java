package com.infy.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.isa.entity.SimOffersEntity;

public interface OfferRepository extends JpaRepository<SimOffersEntity , Integer>{

	SimOffersEntity findBySim_SimNumber(Long simNumber);
}
