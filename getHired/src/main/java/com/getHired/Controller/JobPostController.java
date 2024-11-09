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

import com.getHired.Dto.JobPostDto;
import com.getHired.Model.JobPost;
import com.getHired.Service.JobPostService;

@RestController
@RequestMapping("/jobPost")
public class JobPostController {
	
	@Autowired
	JobPostService jobPostService;
	
	// only hr can add job post ...
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public void addJobPost(@RequestBody JobPostDto jobPostDto) {
		jobPostService.addJobPost(jobPostDto);
	}
	
	// all candidates and that hr who posted the job can access...
	@PreAuthorize("hasAnyRole('CANDIDATE', 'HR')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	public List<JobPost> getAllJobPosts(){
		return jobPostService.getAllJobPosts();
	}
	
	// candidates and that hr who posted the job can access..
	@PreAuthorize("hasAnyRole('CANDIDATE', 'HR')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public JobPost getJobPostById(@PathVariable Long id) {
		return jobPostService.getJobPostById(id);
	}
	
	// only hr who posted that job post can access....
	@PreAuthorize("hasRole('HR')")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update")
	public void updateJobPost(@RequestBody JobPostDto jobPostDto) {
		jobPostService.updateJobPost(jobPostDto);
	}
	
	// only hr who posted the job can access....
	@PreAuthorize("hasRole('HR')")
	@DeleteMapping("/delete/{id}")
	public void deleteJobPostById(@PathVariable Long id) {
		jobPostService.deleteJobPostById(id);
	}

}
