package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskStatusRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.TaskService;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.TaskStatusService;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;
	private TaskStatusRepository taskStatusRepository;
	private TaskStatusService taskStatusService;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	@Override
	public Collection<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task findTask(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public Task moveRightTask(Task task) {
		// TODO ...
		return task;
	}

	@Override
	public Task moveLeftTask(Task task) {
		// TODO ...
		TaskStatus status = task.getStatus();
        TaskStatus previousStatus = null;
        
        switch (status.getLabel()) {
            case "Doing" : previousStatus = taskStatusService.findTaskStatusByLabel("To Do"); // This is not working yet because of findTaskStatusByLabel
            case "Done" : previousStatus = taskStatusService.findTaskStatusByLabel("Doing");
        };
        
        if (previousStatus != null) {
            task.setStatus(previousStatus);
            return taskRepository.save(task);
        }
        
        return task;
	}

}
