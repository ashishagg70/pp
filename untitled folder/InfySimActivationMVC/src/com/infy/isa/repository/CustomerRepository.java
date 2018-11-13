package com.infy.isa.repository;

import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.infy.isa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{
	@Transactional(noRollbackFor=RuntimeException.class)
	CustomerEntity findByDateOfBirthAndEmailAddressAndSim_SimId(Date dateOfBirth,String email,int simId);

	CustomerEntity findByFirstNameAndLastNameAndSim_SimId(String firstName, String lastName,int simId);
}
