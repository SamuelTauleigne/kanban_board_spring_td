package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskStatusRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.TaskStatusService;

public class TaskStatusServiceImpl implements TaskStatusService {

	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Override
	public TaskStatus findTaskStatusByLabel(String label) {
        return taskStatusRepository.findByLabel(label).iterator().next();
    }

}
