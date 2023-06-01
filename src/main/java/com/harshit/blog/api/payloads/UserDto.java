package com.harshit.blog.api.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty( message = "Please provide a name is should not be Empty")
	@Size(min = 4, max = 24, message = "")
	private String name;
	@Email(message = "Email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 4, max = 10, message = "Password should be greater then 3 character and smaller than 10 character")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$^+=!*()@%&]).{4,10}$", message = "Password should only contain at least one small letter, one Capital Letter, and and one from provided '#$^+=!*()@%&'")
	private String password;
	@NotEmpty
	@Size(min = 10, message = "About section should be greater then 10 characters")
	private String about;
}
