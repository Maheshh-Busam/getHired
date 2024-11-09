package com.getHired.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.getHired.Model.Candidate;
import com.getHired.Model.HR;
import com.getHired.Service.HRService;

@RestController
@RequestMapping("/hr")
public class HRController {
	
	@Autowired
	HRService hrService;
	
	// Get all Candidates only hr can access ....
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all-candidates")	
	public List<Candidate> getAllCandidates(){
		return hrService.getAllCandidates();
	}
	 
}
