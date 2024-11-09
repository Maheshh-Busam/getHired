package com.getHired.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto extends UserDto{

	String resumeUrl;
	String skills;
	String applicantProfile;
	List<JobApplicationDto> jobApplications;
}
