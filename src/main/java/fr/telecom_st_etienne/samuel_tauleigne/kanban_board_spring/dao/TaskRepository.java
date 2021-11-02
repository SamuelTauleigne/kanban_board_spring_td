package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
