package com.infy.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.isa.entity.CustomerAddressEntity;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Integer>{
	
	

}
