package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.DeveloperRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.DeveloperService;

@Service
public class DeveloperServiceImpl implements DeveloperService {
	
	private DeveloperRepository developerRepository;
	
	public DeveloperServiceImpl(DeveloperRepository developerRepository) {
		super();
		this.developerRepository = developerRepository;
	}

	@Override
	public List<Developer> findAllDevelopers() {
		return developerRepository.findAll();
	}

}