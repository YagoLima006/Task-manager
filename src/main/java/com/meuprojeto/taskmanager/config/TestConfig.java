package com.meuprojeto.taskmanager.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.meuprojeto.taskmanager.entity.Task;
import com.meuprojeto.taskmanager.entity.User;
import com.meuprojeto.taskmanager.entity.enums.TaskPriority;
import com.meuprojeto.taskmanager.entity.enums.TaskStatus;
import com.meuprojeto.taskmanager.repository.TaskRepository;
import com.meuprojeto.taskmanager.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public void run(String... args) throws Exception
	{
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "123456");
       
        userRepository.saveAll(Arrays.asList(u1, u2));

        Task t1 = new Task(null, "Estudar Java", "Aprofundar em Spring Boot 3", Instant.now(), false, u1, TaskStatus.IN_PROGRESS, TaskPriority.HIGH);
        Task t2 = new Task(null, "Fazer compras", "Comprar pão, leite e ovos", Instant.now(), true, u1, TaskStatus.COMPLETED, TaskPriority.MEDIUM);
        Task t3 = new Task(null, "Ler um livro", "Ler o capítulo 5 de 'Clean Code'", Instant.now(), false, u2, TaskStatus.PENDING, TaskPriority.LOW);
                
        taskRepository.saveAll(Arrays.asList(t1, t2, t3));
		
	}

}
