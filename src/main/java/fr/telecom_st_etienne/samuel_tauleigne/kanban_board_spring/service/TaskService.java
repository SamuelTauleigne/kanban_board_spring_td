package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import java.util.Collection;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;

public interface TaskService {

	public Collection<Task> findAllTasks();
	
	public Task findTask(Long id);
	
	public Task moveRightTask(Task task);
	
	public Task moveLeftTask(Task task);
	
}