package com.getHired.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getHired.Model.Candidate;
import com.getHired.Model.HR;
import com.getHired.Model.User;
import com.getHired.Repository.CandidateRepository;
import com.getHired.Repository.HrRepository;
import com.getHired.Repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	HrRepository hrRepository;
	
	@Autowired
	CandidateRepository candidateRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	public List<HR> getAllHrs() {
		
		return hrRepository.findAll();
	}
	
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	public void deleteUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("No user found with id: "+ userId));
		if(user != null) {
			userRepository.deleteById(userId);
		}
		
	}

}
