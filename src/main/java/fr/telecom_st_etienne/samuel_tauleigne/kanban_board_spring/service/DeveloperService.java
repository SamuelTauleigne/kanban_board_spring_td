package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import java.util.List;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;

public interface DeveloperService {

	public List<Developer> findAllDevelopers();
	
	public Developer findDeveloperById(Long id);
	
}
