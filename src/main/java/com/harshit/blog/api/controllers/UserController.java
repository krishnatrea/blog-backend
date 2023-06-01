package com.harshit.blog.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.harshit.blog.api.payloads.UserDto;
import com.harshit.blog.api.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/{userId}")
	public  ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(userService.updateUser(userDto,userId));
	}

	// DELETE

	@DeleteMapping("/{userId}")
	public  ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(Map.of("message", "User is Deleted"));
	}

	// GET
	@GetMapping("/")
	public  ResponseEntity<List<UserDto>> getAllUsers() {
	return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public  ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId ) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}
}
