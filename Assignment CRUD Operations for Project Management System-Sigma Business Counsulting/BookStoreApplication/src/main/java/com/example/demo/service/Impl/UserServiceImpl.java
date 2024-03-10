package com.example.demo.service.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setCity(userDto.getCity());
		user.setContactno(userDto.getContactno());
		user.setPassword(userDto.getPassword());
		
		
		User updateUser=this.userRepository.save(user);
		UserDto userDto1=this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		
		return this.userToDto(user);
	}

	
	@Override
	public List<UserDto> getAllUser() {
		List<User> users= this.userRepository.findAll();
		List<UserDto> userDtos=users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override 
	public void deleteUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userRepository.delete(user);
	
	}

		private User dtoToUser(UserDto userDto) {
			User user=this.modelMapper.map(userDto, User.class);
			
			//user.setId(userDto.getId());
		//	user.setName(userDto.getName());
		//	user.setId(userDto.getId());
		//	user.setEmail(userDto.getEmail());
		//	user.setCity(userDto.getCity());
		//	user.setContactno(userDto.getContactno());
			
			return user;
		
		}
		
		private UserDto userToDto(User user) {
			UserDto userDto=this.modelMapper.map(user,UserDto. class);
			
		//	userDto.setId(user.getId());
			//userDto.setName(user.getName());
	//		userDto.setId(user.getId());
		//	userDto.setEmail(user.getEmail());
			//userDto.setCity(user.getCity());
		//	userDto.setContactno(user.getContactno());
			
			return userDto;
		
		}
	
	
}
