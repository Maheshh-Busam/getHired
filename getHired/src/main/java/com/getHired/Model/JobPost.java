package com.getHired.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String jobId;
	String title;
	String description;
	String location;
	String companyName;
	String status; // OPEN, CLOSED...
	
	@ManyToOne
	@JoinColumn(name="hr_id")
	@JsonBackReference
	HR hr;
	
	@OneToMany(mappedBy ="jobPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	List<JobApplication> jobApplications;
	
}
