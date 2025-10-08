package com.meuprojeto.taskmanager.DTO;

import java.io.Serializable;

import com.meuprojeto.taskmanager.entity.User;

public class UserResponseDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	
	public UserResponseDTO(User obj)
	{
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	
	
}
