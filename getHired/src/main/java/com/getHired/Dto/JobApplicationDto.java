package com.getHired.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

	String jobId;
	String resumeUrl;
	CandidateDto candidate;
	JobPostDto jobPost;
	
}
