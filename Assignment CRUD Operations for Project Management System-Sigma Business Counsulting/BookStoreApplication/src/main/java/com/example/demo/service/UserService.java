package com.example.demo.service;


import java.util.List;


import com.example.demo.payloads.UserDto;

public interface UserService {
	
	//public User saveUser(User user);
	UserDto createUser(UserDto userdto);
	UserDto updateUser(UserDto userdto,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto>getAllUser();
	void deleteUser(Integer userId);
}
