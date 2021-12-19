package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.TaskServiceImpl;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.Constants;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.LoadDatabase;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.TaskMoveAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(description = "Set of endpoints for GET, POST, PATCH on Tasks")
public class TaskController {
	
	Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@GetMapping("/tasks")
	@ApiOperation("${task.get}")
	Collection<Task> findAllTasks() {
		return this.taskServiceImpl.findAllTasks();
	}
	
	@PostMapping("/tasks")
	@ApiOperation("${task.create}")
	Task createTask(@ApiParam("${task.param}") @Valid @RequestBody Task task) {
		return this.taskServiceImpl.createTask(task);
	}
	
	@PatchMapping("/tasks/{id}")
	@ApiOperation("${task.move}")
	Task moveTask(@ApiParam("${action.param}") @RequestBody TaskMoveAction taskMoveAction,
			@ApiParam("${id.param}") @PathVariable Long id) {
		
		Task task = this.taskServiceImpl.findTask(id);
		
		if (Constants.MOVE_LEFT_ACTION.equals(taskMoveAction.getAction())) {
			return this.taskServiceImpl.moveLeftTask(task);
		}
		else { // MOVE_RIGHT_ACTION
			return this.taskServiceImpl.moveRightTask(task);
		}
		
	}
	
	
	// Here, I set error messages.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}