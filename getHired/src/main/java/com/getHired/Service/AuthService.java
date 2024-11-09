package com.getHired.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.getHired.Dto.JwtRequest;
import com.getHired.Dto.JwtResponse;
import com.getHired.Jwt.JwtAuthenticationHelper;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtAuthenticationHelper jwtHelper;

	@Autowired
	UserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public JwtResponse login(JwtRequest jwtRequest) {

		this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

		String token = jwtHelper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).build();

		return response;
	}

	private void doAuthenticate(String username, String password) {
		

		 logger.info("Authenticating user: " + username); 
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		try {
			authManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username and password");
		}

	}
}
