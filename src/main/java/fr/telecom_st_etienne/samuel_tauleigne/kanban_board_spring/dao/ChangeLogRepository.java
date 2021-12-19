package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.ChangeLog;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
	
	List<ChangeLog> findByTaskId(Long taskId);

}
