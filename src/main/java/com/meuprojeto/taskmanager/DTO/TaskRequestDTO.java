package com.meuprojeto.taskmanager.DTO;

import java.io.Serializable;

import com.meuprojeto.taskmanager.entity.User;
import com.meuprojeto.taskmanager.entity.enums.TaskPriority;
import com.meuprojeto.taskmanager.entity.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "The title cannot be blank")
	private String title;
	
	private String description;
	
	@NotNull(message = "User ID is required")
	private Long userId;
	
	private User user;
	private TaskStatus status;
    private TaskPriority priority;
    
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public User getUser()
	{
		return user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
