package com.example.demo.payloads;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDto {
	
	private int id;
	@NotEmpty(message="Name should not be empty")
	
	private String name;
	@Email(message ="Email address is not valid")
	private String email;
	@NotEmpty
	private String city;
	
	private int contactno;
	private String password;
	
	
}
