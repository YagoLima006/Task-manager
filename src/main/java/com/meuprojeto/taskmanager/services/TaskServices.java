package com.meuprojeto.taskmanager.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuprojeto.taskmanager.DTO.TaskRequestDTO;
import com.meuprojeto.taskmanager.DTO.TaskResponseDTO;
import com.meuprojeto.taskmanager.entity.Task;
import com.meuprojeto.taskmanager.entity.enums.TaskPriority;
import com.meuprojeto.taskmanager.entity.enums.TaskStatus;
import com.meuprojeto.taskmanager.repository.TaskRepository;
import com.meuprojeto.taskmanager.repository.UserRepository;
import com.meuprojeto.taskmanager.services.exception.ResourceNotFoundException;

@Service
public class TaskServices 
{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional(readOnly = true)
	public TaskResponseDTO findById(Long id)
	{
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " +id));
		return new TaskResponseDTO(task);
	}
	
	@Transactional(readOnly = true)
	public List<TaskResponseDTO> findAllByUserId(Long userId)
	{
		if(!userRepository.existsById(userId))
		{
			throw new ResourceNotFoundException("User with id: " +userId+ " not found");
		}
		
		List<Task> taskList = taskRepository.findByUserId(userId);
		return taskList.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public TaskResponseDTO createTask(TaskRequestDTO dto)
	{
		userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Id not found: " +dto.getUserId()));
		
		Task newTask = new Task();
		newTask.setTitle(dto.getTitle());
		newTask.setDescription(dto.getDescription());
		newTask.setUser(dto.getUser());
		newTask.setStatus(dto.getStatus() != null ? dto.getStatus() : TaskStatus.PENDING);
		newTask.setPriority(dto.getPriority() != null ? dto.getPriority() : TaskPriority.MEDIUM);
		newTask = taskRepository.save(newTask);
		
		return new TaskResponseDTO(newTask);
	}
	
	@Transactional
	public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto)
	{
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " +id));
		
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setStatus(dto.getStatus());
		task.setPriority(dto.getPriority());
		
		task = taskRepository.save(task);
		return new TaskResponseDTO(task);
	}
	
	@Transactional
	public void deleteTask(Long id)
	{
		if(!taskRepository.existsById(id))
		{
			throw new ResourceNotFoundException("Task with id: " +id+ " not found");
		}
		taskRepository.deleteById(id);
	}
}
