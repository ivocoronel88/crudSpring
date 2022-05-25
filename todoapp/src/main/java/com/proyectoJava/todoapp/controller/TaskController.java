package com.proyectoJava.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoJava.todoapp.persistence.entity.Task;
import com.proyectoJava.todoapp.persistence.entity.TaskStatus;
import com.proyectoJava.todoapp.service.TaskService;
import com.proyectoJava.todoapp.service.dto.TaskInDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController { // se comunica con el servicio
	 	@Autowired
		private TaskService taskService;
		
	 	
	 	@PostMapping 
	 	public Task createTask(@RequestBody TaskInDTO taskInDTO) {
	 		return taskService.createTask(taskInDTO);
	 	}
	 	
	 	
	 	@GetMapping
	 	public List<Task> findAll(){
	 		return taskService.findAll();
	 	}
	 	
	 	@GetMapping("/status/{status}")
	 	public List<Task> findAllByTaskStatus(@PathVariable("status" ) TaskStatus status){
	 		return taskService.findAllByTaskStatus(status);
	 	}
	 	
	 	@PatchMapping("markAsFinished/{id}")
	 	public ResponseEntity<Void> markAsFinished(@PathVariable("id")Long id){
	 		taskService.updateTaskAsFineshed(id);
	 		
	 		return ResponseEntity.noContent().build();// despues que se ejecuta la operacion envia una respuesta si se realizo 404 o 200
	 	}
	 	
	 	@DeleteMapping("/{id}")
	 	public ResponseEntity<Void> deleteById(@PathVariable("id")Long id) {
	 		taskService.deleteById(id);
	 		return ResponseEntity.noContent().build();
	 	}
}
