package com.proyectoJava.todoapp.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskInDTO { //define los datos que la api va a pedirle al usuario a la hora de llamar la api
	

	
	private String title;
	private String description;
	private LocalDateTime eta;
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
	public LocalDateTime getEta() {
		return eta;
	}
	public void setEta(LocalDateTime eta) {
		this.eta = eta;
	}


}
