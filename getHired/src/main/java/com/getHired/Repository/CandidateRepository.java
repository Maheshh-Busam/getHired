package com.getHired.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getHired.Model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	Optional<Candidate> findByEmail(String email);

}
