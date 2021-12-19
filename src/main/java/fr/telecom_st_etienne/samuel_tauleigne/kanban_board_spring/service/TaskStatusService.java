package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;

public interface TaskStatusService {

	public TaskStatus findTaskStatusByLabel(String label);
	
}
