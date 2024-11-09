package com.getHired.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getHired.Dto.JobApplicationDto;
import com.getHired.Model.Candidate;
import com.getHired.Model.JobApplication;
import com.getHired.Model.JobPost;
import com.getHired.Repository.CandidateRepository;
import com.getHired.Repository.JobApplicationRepository;
import com.getHired.Repository.JobPostRepository;

@Service
public class JobApplicationService {
	
	@Autowired
    private JobApplicationRepository jobApplicationRepository;
    
    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private CandidateRepository candidateRepository;

	public void applyJob(JobApplicationDto jobApplicationDto) {
		JobApplication jobApplication = new JobApplication();

        // Set Job Application details
        jobApplication.setResumeUrl(jobApplicationDto.getResumeUrl());
        jobApplication.setApplicationStatus("PENDING");
        jobApplication.setAppliedDate(new Date());

        // Find and set the Candidate
        String candidateEmail = jobApplicationDto.getCandidate().getEmail();
        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(candidateEmail);
        if (candidateOpt.isPresent()) {
            jobApplication.setCandidate(candidateOpt.get());
        } else {
            throw new IllegalArgumentException("Candidate not found.");
        }

        // Find and set the JobPost
        String jobId = jobApplicationDto.getJobId();
        Optional<JobPost> jobPostOpt = jobPostRepository.findByJobId(jobId);
        if (jobPostOpt.isPresent()) {
            jobApplication.setJobPost(jobPostOpt.get());
        } else {
            throw new IllegalArgumentException("Job Post not found.");
        }

        // Save Job Application
        jobApplicationRepository.save(jobApplication);
		
	}

	public List<JobApplication> getAllJobApplicationsByJobId(Long jobId) {
		JobPost jobPost = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new IllegalArgumentException("Job Post not found with jobID: "+jobId));
		 List<JobApplication> jobApplications = jobPost.getJobApplications();
	     
		 return jobApplications;
	}

	public JobApplication getJobApplicationById(Long id) {
		JobApplication jobApplication = jobApplicationRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Job application not found."));
		return jobApplication;
	}

	public List<JobApplication> getMyJoApplications(Long candidateId) {
		Candidate candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new IllegalArgumentException("No Candidate found with id : "+ candidateId));
		
		return candidate.getJobApplications();
	}

	public void updateJobApplicationStatus(Long id, String status) {
		JobApplication jobApplication = jobApplicationRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Job application not found."));
		jobApplication.setApplicationStatus(status);
        jobApplicationRepository.save(jobApplication);
	}

	public void withdrawJobApplication(Long id) {
		if (jobApplicationRepository.existsById(id)) {
            jobApplicationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Job application not found.");
        }
		
	}

}
