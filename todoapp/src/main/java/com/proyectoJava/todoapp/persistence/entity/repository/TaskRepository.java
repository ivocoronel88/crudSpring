package com.proyectoJava.todoapp.persistence.entity.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectoJava.todoapp.persistence.entity.Task;
import com.proyectoJava.todoapp.persistence.entity.TaskStatus;;

public interface TaskRepository extends JpaRepository<Task,Long> {
	
		
	public List<Task> findAllByTaskStatus(TaskStatus taskStatus);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id",nativeQuery = true) // permite ejecutar query nativas
	public void markTaskAsFinished(@Param("id") Long id);
		

}
