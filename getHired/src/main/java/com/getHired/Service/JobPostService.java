package com.getHired.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.getHired.Dto.JobPostDto;
import com.getHired.Model.HR;
import com.getHired.Model.JobPost;
import com.getHired.Repository.HrRepository;
import com.getHired.Repository.JobPostRepository;

@Service
public class JobPostService {
	
	@Autowired
	JobPostRepository jobPostRepository;
	
	@Autowired
	HrRepository hrRepository;

	public void addJobPost(JobPostDto jobPostDto) {
		JobPost jobPost = new JobPost();
		
		jobPost.setJobId(jobPostDto.getJobId());
		jobPost.setTitle(jobPostDto.getTitle());
		jobPost.setDescription(jobPostDto.getDescription());
		jobPost.setLocation(jobPostDto.getLocation());
		jobPost.setCompanyName(jobPostDto.getCompanyName());
		jobPost.setStatus("OPEN");
		
		String hrEmail = jobPostDto.getHr().getEmail();
		Optional<HR> hr = hrRepository.findByEmail(hrEmail);
		
		if(hr.isPresent()) {
			jobPost.setHr(hr.get());
		}
		else {
            throw new IllegalArgumentException("Hr not found.");
        }
		
		jobPostRepository.save(jobPost);
		
	}

	public List<JobPost> getAllJobPosts() {
		return jobPostRepository.findAll();
	}

	public JobPost getJobPostById(Long id) {
		
		return jobPostRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Job Post is not found"));
	}

	public void updateJobPost(JobPostDto jobPostDto) {
        Optional<JobPost> jobPostOpt = jobPostRepository.findByJobId(jobPostDto.getJobId());

        if (jobPostOpt.isPresent()) {
            JobPost jobPost = jobPostOpt.get();
            verifyJobPostOwnership(jobPost);

            if (jobPostDto.getCompanyName() != null && !jobPostDto.getCompanyName().isEmpty()) {
                jobPost.setCompanyName(jobPostDto.getCompanyName());
            }
            if (jobPostDto.getTitle() != null && !jobPostDto.getTitle().isEmpty()) {
                jobPost.setTitle(jobPostDto.getTitle());
            }
            if (jobPostDto.getDescription() != null && !jobPostDto.getDescription().isEmpty()) {
                jobPost.setDescription(jobPostDto.getDescription());
            }
            if (jobPostDto.getLocation() != null && !jobPostDto.getLocation().isEmpty()) {
                jobPost.setLocation(jobPostDto.getLocation());
            }
            jobPostRepository.save(jobPost);
        } else {
            throw new IllegalArgumentException("Job Post not found with jobId: " + jobPostDto.getJobId());
        }
    }

    public void deleteJobPostById(Long id) {
        JobPost jobPost = getJobPostById(id);
        verifyJobPostOwnership(jobPost);
        jobPostRepository.deleteById(id);
    }

    private void verifyJobPostOwnership(JobPost jobPost) {
        String loggedInUserEmail = getLoggedInUserEmail();

        if (!jobPost.getHr().getEmail().equals(loggedInUserEmail)) {
            throw new SecurityException("Unauthorized to modify this job post.");
        }
    }

    private String getLoggedInUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        throw new SecurityException("Could not retrieve logged-in user details");
    }

}
