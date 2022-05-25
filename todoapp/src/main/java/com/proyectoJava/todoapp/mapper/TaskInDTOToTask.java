package com.proyectoJava.todoapp.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.proyectoJava.todoapp.persistence.entity.Task;
import com.proyectoJava.todoapp.persistence.entity.TaskStatus;
import com.proyectoJava.todoapp.service.dto.TaskInDTO;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO,Task>{// convierte TaskInDTO en un Task

	@Override
	public Task map(TaskInDTO in) {
		Task task = new Task();
		task.setTitle(in.getTitle());
		task.setDescription(in.getDescription());
		task.setEta(in.getEta());
		task.setCreatedDate(LocalDateTime.now());
		task.setFinished(false);
		task.setTaskStatus(TaskStatus.ONTIME);
		return task;
	}

}
