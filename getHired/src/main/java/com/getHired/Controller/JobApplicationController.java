package com.getHired.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.getHired.Dto.JobApplicationDto;
import com.getHired.Model.JobApplication;
import com.getHired.Service.JobApplicationService;


@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;
	
	// only candidates can access ...
	@PreAuthorize("hasRole('CANDIDATE')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/apply")
	public void applyJob(@RequestBody JobApplicationDto jobApplicationDto) {
		jobApplicationService.applyJob(jobApplicationDto);
	}
	
	// only hr who posted the jobs 
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all/{jobId}")
	public List<JobApplication> getAllJobApplicationsByJobId(@PathVariable Long jobId){
		return jobApplicationService.getAllJobApplicationsByJobId(jobId);
	}
	
	// can access hr who posted the job and candidate who applied ...
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public JobApplication getJobApplicationById(@PathVariable Long id) {
		return jobApplicationService.getJobApplicationById(id);
	}
	
	// candidate can access their applied jobs
	@PreAuthorize("hasRole('CANDIDATE')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/my-job-applications/{candidateId}")
	public List<JobApplication> getMyJoApplications(@PathVariable Long candidateId){
		return jobApplicationService.getMyJoApplications(candidateId);
	}
	
	// Only HR can update the application ...
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-status/{id}")
	public void updateJobApplicationStatus(Long id, String status) {
		jobApplicationService.updateJobApplicationStatus(id, status);
	}
	
	// Candidates only can withdraw....
	@PreAuthorize("hasRole('CANDIDATE')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void withdrawJobApplication(@PathVariable Long id) {
		jobApplicationService.withdrawJobApplication(id);
	}
	
	
}
