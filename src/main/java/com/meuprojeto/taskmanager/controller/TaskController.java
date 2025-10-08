package com.meuprojeto.taskmanager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meuprojeto.taskmanager.DTO.TaskRequestDTO;
import com.meuprojeto.taskmanager.DTO.TaskResponseDTO;
import com.meuprojeto.taskmanager.services.TaskServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController 
{

	@Autowired
	private TaskServices taskService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id)
	{
		TaskResponseDTO dto = taskService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TaskResponseDTO>> findAllByUserId(@PathVariable Long userId)
	{
		List<TaskResponseDTO> dto = taskService.findAllByUserId(userId);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO request)
	{
		TaskResponseDTO createTaskDTO = taskService.createTask(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createTaskDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(createTaskDTO);
	}
	
	@PutMapping
	public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO request)
	{
		TaskResponseDTO updateTask = taskService.updateTask(id, request);
		return ResponseEntity.ok(updateTask);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteTask(@PathVariable Long id)
	{
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}
