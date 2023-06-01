package com.harshit.blog.api.services;

import java.util.List;

import com.harshit.blog.api.payloads.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId); 
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
