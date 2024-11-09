package com.getHired.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getHired.Model.Candidate;
import com.getHired.Repository.CandidateRepository;


@Service
public class HRService {

	@Autowired
	CandidateRepository candidateRepository;
	
	public List<Candidate> getAllCandidates() {
		
		return candidateRepository.findAll();
	}

}
