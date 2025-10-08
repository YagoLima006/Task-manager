package com.meuprojeto.taskmanager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meuprojeto.taskmanager.DTO.UserRequestDTO;
import com.meuprojeto.taskmanager.DTO.UserResponseDTO;
import com.meuprojeto.taskmanager.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id)
	{
		UserResponseDTO dto = userService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll()
	{
		List<UserResponseDTO> dto = userService.findAll();
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request)
	{
		UserResponseDTO createUserDTO = userService.createUser(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createUserDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(createUserDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id)
	{
		userService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
