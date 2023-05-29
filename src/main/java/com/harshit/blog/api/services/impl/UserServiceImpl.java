package com.harshit.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshit.blog.api.entities.User;
import com.harshit.blog.api.payloads.UserDto;
import com.harshit.blog.api.repositories.UserRepository;
import com.harshit.blog.api.services.UserService;
import com.harshit.blog.api.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));
		userRepository.delete(user);
		return;
	}

	@Override
	public UserDto createUser(UserDto user) {
		User newUser = this.dtoToUser(user);
		User createUser = userRepository.save(newUser);
		return this.userToDto(createUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		User findUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));
		findUser.setAbout(user.getAbout());
		findUser.setEmail(user.getEmail());
		findUser.setName(user.getName());
		findUser.setPassword(user.getPassword());
		User updatedUser =  userRepository.save(findUser);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
