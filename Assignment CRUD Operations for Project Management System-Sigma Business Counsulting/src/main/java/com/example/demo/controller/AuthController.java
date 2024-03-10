package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.payloads.JwtAuthRequest;
import com.example.demo.payloads.JwtAuthResponse;
import com.example.demo.security.JwtTokenHelper;

public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request)
	{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails =this.userDetailsService.loadUserByUsername(request.getUsername());
		String token=this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	
	
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthentionToken(username, password);
		
		try {
		this.authenticationManager.authenticate(authenticationToken);
	}	
		catch(BadCredentialException e) {
			System.out.println("Invalid Deatails");
			throw new ApiException("Invalid username or password");
		
		}
		
}
}
