package com.meuprojeto.taskmanager.DTO;

import java.io.Serializable;
import java.time.Instant;

import com.meuprojeto.taskmanager.entity.Task;
import com.meuprojeto.taskmanager.entity.enums.TaskPriority;
import com.meuprojeto.taskmanager.entity.enums.TaskStatus;

public class TaskResponseDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Instant createDate;
    private Long userId;  
    private String userName;
    private TaskStatus status;
    private TaskPriority priority;
    
    public TaskResponseDTO(Task obj)
    {
    	this.id = obj.getId();
    	this.title = obj.getTitle();
    	this.description = obj.getDescription();
    	this.completed = obj.isCompleted();
    	this.createDate = obj.getCreateDate();
    	if(obj.getUser() != null)
    	{
    		this.userId = obj.getUser().getId();
    		this.userName = obj.getUser().getName();
    	}
    	this.status = obj.getStatus();
        this.priority = obj.getPriority();
    }
    
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public TaskPriority getPriority() {
		return priority;
	}
}
