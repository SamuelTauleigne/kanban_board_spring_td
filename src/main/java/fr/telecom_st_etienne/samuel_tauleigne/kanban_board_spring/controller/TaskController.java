package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.TaskServiceImpl;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.Constants;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.LoadDatabase;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.TaskMoveAction;

@RestController
public class TaskController {
	
	Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@GetMapping("/tasks")
	Collection<Task> findAllTasks() {
		return this.taskServiceImpl.findAllTasks();
	}
	
	@PostMapping("/tasks")
	Task createTask(@RequestBody Task task) {
		return this.taskServiceImpl.createTask(task);
	}
	
	@PatchMapping("/tasks/{id}")
	Task moveTask(@RequestBody TaskMoveAction taskMoveAction, @PathVariable Long id) {
		
		Task task = this.taskServiceImpl.findTask(id);
		
		if (Constants.MOVE_LEFT_ACTION.equals(taskMoveAction.getAction())) {
			return this.taskServiceImpl.moveLeftTask(task);
		}
		else {
			return this.taskServiceImpl.moveRightTask(task);
		}
		
		// return this.taskServiceImpl.createTask(task);
	}

}