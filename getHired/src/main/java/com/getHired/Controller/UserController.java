package com.getHired.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.getHired.Dto.CandidateDto;
import com.getHired.Dto.HRDto;
import com.getHired.Dto.UserDto;
import com.getHired.Model.User;
import com.getHired.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register-candidate") // anyone can use as first time register as Candidate
	public void registerAsCandidate(@RequestBody CandidateDto candidateDto) {
		userService.registerAsCandidate(candidateDto);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/register-hr") // anyone can use as first time register as HR
	public void registerAsHr(@RequestBody HRDto hrDto) {
		userService.registerAsHr(hrDto);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all") // Only Admin can access this.. 
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('CANDIDATE', 'HR')")
	@GetMapping("/{id}") // Candidate or hr can only access
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('CANDIDATE', 'HR')")
	@PutMapping("/update/{id}") // candidate or hr can update their profiles..
	public ResponseEntity<String> updateUserProfile(@PathVariable Long id, @RequestBody UserDto userDto) {
		userService.updateUserProfile(id,userDto);
		return ResponseEntity.ok("User Successfully Updated");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('CANDIDATE', 'HR')")
	@DeleteMapping("/delete/{id}") // candidate or hr can delete their profiles..
	public ResponseEntity<String> deleteUserProfile(@PathVariable Long id) {
		userService.deleteUserProfile(id);
		return ResponseEntity.ok("User Successfully Deleted");
	}
	
}
