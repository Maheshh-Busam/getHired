package com.getHired.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String jobId;
	String resumeUrl;
	String applicationStatus; // PENDING, ACCEPTED, REJECTED...
	Date appliedDate;
	
	@ManyToOne
	@JoinColumn(name="candidate_id")
	@JsonBackReference
	Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name="jobPost_id")
	@JsonBackReference
	JobPost jobPost;
	
}
