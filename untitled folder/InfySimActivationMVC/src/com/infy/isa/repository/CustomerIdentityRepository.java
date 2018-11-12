package com.infy.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.isa.entity.CustomerIdentityEntity;

public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentityEntity, Long> {
	CustomerIdentityEntity findByFirstNameAndLastNameAndUniqueIdNumber(String firstName,String lastName,Long id);

}
