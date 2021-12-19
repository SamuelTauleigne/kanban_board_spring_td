package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import java.util.Collection;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.ChangeLog;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskType;

public interface TaskService {

	public Collection<Task> findAllTasks();
	
	public Task changeTaskStatus(Task task, TaskStatus targetStatus);
	
	public Task findTask(Long id);
	
	public TaskStatus findTaskStatus(Long id);
	
	public TaskType findTaskType(Long id);

	public Collection<ChangeLog> findChangeLogsForTask(Task task);
	
	public Task moveRightTask(Task task);
	
	public Task moveLeftTask(Task task);

	public Task createTask(Task task);

	public void deleteTask(Task task);

	public Collection<TaskType> findAllTaskTypes();
	
	public Collection<TaskStatus> findAllTaskStatus();
	
}
