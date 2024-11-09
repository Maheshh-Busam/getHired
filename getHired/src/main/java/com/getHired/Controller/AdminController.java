package com.getHired.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.getHired.Model.Candidate;
import com.getHired.Model.HR;
import com.getHired.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	// admin can access to see all hr details...
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all-hr")
	public List<HR> getAllHrs(){
		return adminService.getAllHrs();
	}
	
	// admin can access to see all candidate details...
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all-candidates")
	public List<Candidate> getAllCandidates(){
		return adminService.getAllCandidates();
	}
	
	// Admin can delete any user...
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		adminService.deleteUserById(userId);
	}	
	
}
