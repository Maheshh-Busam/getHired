package com.getHired.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getHired.Service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
	
	@Autowired
	CandidateService candidateService;
	

}
