package com.getHired.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getHired.Model.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Long>{
	
	Optional<JobPost> findByJobId(String jobId);

}
