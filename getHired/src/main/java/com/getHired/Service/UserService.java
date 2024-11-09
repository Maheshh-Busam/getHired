package com.getHired.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.getHired.Dto.CandidateDto;
import com.getHired.Dto.HRDto;
import com.getHired.Dto.UserDto;
import com.getHired.Exceptions.AccessDeniedException;
import com.getHired.Exceptions.ResourceNotFoundException;
import com.getHired.Model.Candidate;
import com.getHired.Model.HR;
import com.getHired.Model.Role;
import com.getHired.Model.User;
import com.getHired.Repository.CandidateRepository;
import com.getHired.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CandidateRepository candidateRepository;
	

	public void registerAsCandidate(CandidateDto candidateDto) {
		Candidate candidate = new Candidate();
		
		candidate.setUsername(candidateDto.getUsername());
		candidate.setAge(candidateDto.getAge());
		candidate.setEmail(candidateDto.getEmail());
		candidate.setGender(candidateDto.getGender());
		candidate.setResumeUrl(candidateDto.getResumeUrl());
		candidate.setSkills(candidateDto.getSkills());
		candidate.setApplicantProfile(candidateDto.getApplicantProfile());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(candidateDto.getPassword());
		
		candidate.setPassword(encodedPassword);
		
		Role role = new Role();
		role.setRoleName("ROLE_CANDIDATE");
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		candidate.setRoles(roles);
		candidateRepository.save(candidate);
		
		userRepository.save(candidate);
		
	}

	public void registerAsHr(HRDto hrDto) {
		
		HR hr = new HR();
		
		hr.setUsername(hrDto.getUsername());
		hr.setAge(hrDto.getAge());
		hr.setEmail(hrDto.getEmail());
		hr.setGender(hrDto.getGender());
		hr.setHrProfile(hrDto.getHrProfile());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(hrDto.getPassword());
		
		hr.setPassword(encodedPassword);
		
		Role role = new Role();
		role.setRoleName("ROLE_HR");
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		hr.setRoles(roles);
		
		userRepository.save(hr);
		
	}

	public User getUserById(Long id) {
		
		checkUserAuthorization(id);
        return userRepository.findById(id).
        		orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public void updateUserProfile(Long id, UserDto userDto) {
		 checkUserAuthorization(id);
		 
		// Retrieve the user by ID for updating
		 User user = userRepository.findById(id).
				 orElseThrow(() -> new ResourceNotFoundException("User not found"));
		 
		if (userDto.getAge() > 0) {
		    user.setAge(userDto.getAge());
		 }
	    if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
	        user.setEmail(userDto.getEmail());
	    }
	    if (userDto.getUsername() != null && !userDto.getUsername().isEmpty()) {
	        user.setUsername(userDto.getUsername());
	    }
	    if (userDto.getGender() != null && !userDto.getGender().isEmpty()) {
	        user.setGender(userDto.getGender());
	    }
	    if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String encodedPassword = encoder.encode(userDto.getPassword());
	        user.setPassword(encodedPassword);
	    }
		 
		userRepository.save(user); 
		
	}

	public void deleteUserProfile(Long id) {
		checkUserAuthorization(id);
		
		userRepository.deleteById(id);
		
	}
	
	// check current  logged user id is match with fetching id...
	private void checkUserAuthorization(Long id) {
        // Get the currently logged-in user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long loggedInUserId = ((User) authentication.getPrincipal()).getId(); // Assuming your User class has an 'id' field

        // Retrieve the user by ID
        User user = userRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("User not found"));

        // Check if the logged-in user's ID matches the requested ID
        if (!user.getId().equals(loggedInUserId)) {
            throw new AccessDeniedException("You do not have permission to access this user");
        }
    }

}
