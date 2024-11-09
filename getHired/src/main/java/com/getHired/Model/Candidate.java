package com.getHired.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate extends User{

	String resumeUrl;
	String skills;
	String applicantProfile;
	
	@OneToMany(mappedBy="candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	List<JobApplication> jobApplications;
	
	
}
