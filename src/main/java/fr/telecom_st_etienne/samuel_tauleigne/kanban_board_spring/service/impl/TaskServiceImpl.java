package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.ChangeLogRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskStatusRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskTypeRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.ChangeLog;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskType;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.TaskService;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.Constants;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	
	@Autowired
	private ChangeLogRepository	changeLogRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Task> findAllTasks() {
		return this.taskRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<TaskType> findAllTaskTypes() {
		return this.taskTypeRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<TaskStatus> findAllTaskStatus() {
		return this.taskStatusRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Task findTask(Long id) {
		return this.taskRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TaskStatus findTaskStatus(Long id) {
		return this.taskStatusRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public TaskType findTaskType(Long id) {
		return this.taskTypeRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<ChangeLog> findChangeLogsForTask(Task task) {
		return this.changeLogRepository.findByTaskId(task.getId());
	}

	@Override
	@Transactional
	public Task changeTaskStatus(Task task, TaskStatus targetStatus) {

		task = this.taskRepository.save(task);
		
		ChangeLog changeLog = new ChangeLog();
		changeLog.setOccured(LocalDate.now());
		changeLog.setSourceStatus(task.getStatus());
		changeLog.setTargetStatus(targetStatus);		
		changeLog.setTask(task);
		
		this.changeLogRepository.save(changeLog);
		
		task.setStatus(targetStatus);
		
		return task;
	}

	@Override
	@Transactional
	public Task moveRightTask(Task task) {	
		TaskStatus targetStatus = this.getTargetStatusForMoveRight(task.getStatus());
		return this.changeTaskStatus(task, targetStatus);
	}

	@Transactional(readOnly = true)
	private TaskStatus getTargetStatusForMoveRight(TaskStatus status) {
		
		TaskStatus todoStatus = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus doneStatus = this.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		TaskStatus result = null;
		
		if (status != null) {
			
			if (status.equals(todoStatus)) {
				
				result = doingStatus;
			}
			else if (status.equals(doingStatus)) {
				
				result = doneStatus;
			}
			else if (status.equals(doneStatus)) {
				
				throw new IllegalStateException();
			}
		}
		else {
			
			throw new IllegalArgumentException();
		}
		
		return result;
	}

	@Override
	@Transactional
	public Task moveLeftTask(Task task) {
		TaskStatus targetStatus = this.getTargetStatusForMoveLeft(task.getStatus());
		return this.changeTaskStatus(task, targetStatus);
	}

	@Transactional(readOnly = true)
	private TaskStatus getTargetStatusForMoveLeft(TaskStatus status) {
		
		TaskStatus todoStatus = this.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
		
		TaskStatus doneStatus = this.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		TaskStatus result = null;
		
		if (status != null) {
			
			if (status.equals(todoStatus)) {
				
				throw new IllegalStateException();
			}
			else if (status.equals(doingStatus)) {
				
				result = todoStatus;
			}
			else if (status.equals(doneStatus)) {
				
				result = doingStatus;
			}
		}
		else {
			
			throw new IllegalArgumentException();
		}
		
		return result;
	}

	@Override
	@Transactional
	public Task createTask(Task task) {
		return this.taskRepository.save(task);
	}

	@Override
	@Transactional
	public void deleteTask(Task task) {
		
		task = this.taskRepository.save(task);
		
		List<ChangeLog> changeLogs = this.changeLogRepository.findByTaskId(task.getId());
		
		for (ChangeLog changeLog : changeLogs) {
			
			this.changeLogRepository.delete(changeLog);
		}
		
		this.taskRepository.delete(task);
		
		return;
	}

}
