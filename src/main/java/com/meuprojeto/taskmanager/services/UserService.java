package com.meuprojeto.taskmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meuprojeto.taskmanager.DTO.UserRequestDTO;
import com.meuprojeto.taskmanager.DTO.UserResponseDTO;
import com.meuprojeto.taskmanager.entity.User;
import com.meuprojeto.taskmanager.repository.UserRepository;
import com.meuprojeto.taskmanager.services.exception.BusinessRuleException;
import com.meuprojeto.taskmanager.services.exception.ResourceNotFoundException;

@Service
public class UserService
{

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserResponseDTO createUser(UserRequestDTO dto)
	{
		Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());
		
		if (userOptional.isPresent())
		{
            throw new BusinessRuleException("The email " + dto.getEmail() + " is already in use.");
        }

		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		User newUser = userRepository.save(user);
		
		return new UserResponseDTO(newUser);
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll()
	{
		List<User> userList = userRepository.findAll();
		return userList.stream().map(UserResponseDTO::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UserResponseDTO findById(Long id)
	{
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));
	
		return new UserResponseDTO(user);
	}
	
	@Transactional
	public UserResponseDTO updateUser(Long id, UserRequestDTO dto)
	{
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
	
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		user = userRepository.save(user);
		return new UserResponseDTO(user);
	}
	
	@Transactional
	public void delete(Long id)
	{
		 if (!userRepository.existsById(id)) 
		 {
	            throw new ResourceNotFoundException("Id not found: " +id);
	     }
	        userRepository.deleteById(id);
	    
	}
}
