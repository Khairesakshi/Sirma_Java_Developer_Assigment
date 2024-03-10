package com.example.demo.payloads;

import com.example.demo.entity.Type;
import com.example.demo.entity.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	
	private int id;
	private String title;
	private String imagename;

	
	private Type type;
	
	private User user;

	
	
}
