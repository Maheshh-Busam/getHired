package com.getHired.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostDto {

	String jobId;
	String title;
	String description;
	String location;
	String companyName;
	HRDto hr;
	List<JobApplicationDto> jobApplications;
}
