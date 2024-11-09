package com.getHired.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getHired.Model.HR;

public interface HrRepository extends JpaRepository<HR, Long>{
	
	Optional<HR> findByEmail(String email);

}
