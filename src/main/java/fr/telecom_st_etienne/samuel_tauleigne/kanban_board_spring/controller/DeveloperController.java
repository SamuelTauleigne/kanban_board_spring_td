package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.DeveloperService;

@RestController
public class DeveloperController {
	
	@Autowired
	private DeveloperService developerService;
	
	@GetMapping("/developers")
	List<Developer> findAllDevelopers() {
		return this.developerService.findAllDevelopers();
	}

	@GetMapping("/developers/{id}")
	Developer findDeveloperById(@PathVariable Long id) {
		return this.developerService.findDeveloperById(id);
	}
}
