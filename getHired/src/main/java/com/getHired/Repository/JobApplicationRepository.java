package com.getHired.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getHired.Model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{

}
