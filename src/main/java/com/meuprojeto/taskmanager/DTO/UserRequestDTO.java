package com.meuprojeto.taskmanager.DTO;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "The name cannot be blank")
	private String name;
	
	@NotBlank(message = "The email cannot be blank")
	private String email;
	
	@NotBlank(message = "The password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
