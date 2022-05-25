package com.proyectoJava.todoapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proyectoJava.todoapp.exceptions.ToDoExceptions;
import com.proyectoJava.todoapp.mapper.TaskInDTOToTask;
import com.proyectoJava.todoapp.persistence.entity.Task;
import com.proyectoJava.todoapp.persistence.entity.TaskStatus;
import com.proyectoJava.todoapp.persistence.entity.repository.TaskRepository;
import com.proyectoJava.todoapp.service.dto.TaskInDTO;

@Service // bean para la injeccion de dependencia
public class TaskService { //se implementa la logixca de negocio, logica para cumplir los requerimientos
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskInDTOToTask mapper;
	
	public Task createTask(TaskInDTO taskInDTO) {
		Task task= mapper.map(taskInDTO);
		return this.taskRepository.save(task);
		
	}
	
	public List<Task> findAll(){
		
		return taskRepository.findAll();
	}
	
	public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
		return taskRepository.findAllByTaskStatus(taskStatus);
	}
	
	@Transactional	
	public void updateTaskAsFineshed(Long id) {
		Optional<Task> oTask = taskRepository.findById(id);
		if(oTask.isPresent()) {
			throw new ToDoExceptions("Task not Found",HttpStatus.NOT_FOUND);
		}
		taskRepository.markTaskAsFinished(id);
	}
	
	
	public void deleteById(Long id) {
		Optional<Task> oTask = taskRepository.findById(id);
		if(oTask.isPresent()) {
			throw new ToDoExceptions("Task not Found",HttpStatus.NOT_FOUND);
		}
		taskRepository.deleteById(id);
	}
}
