package com.infy.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.infy.isa.entity.SimDetailsEntity;


public interface SimRepository extends JpaRepository<SimDetailsEntity , Integer>{
	SimDetailsEntity findByServiceNumberAndSimNumber(int serviceNumber, long simNumber);
	
	@Modifying(clearAutomatically = true)
	@Transactional(noRollbackFor=RuntimeException.class)
	 @Query(value="update SimDetailsEntity set simStatus=?1 where simNumber=?2")
	void activateSim(String status, long simNumber);

}
