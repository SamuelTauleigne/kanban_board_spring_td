package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.DeveloperRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class DeveloperTest {

	@Autowired
	private DeveloperRepository developerRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Test
	// Testing the addDeveloper method of Task
	public void addDeveloperTest() {
		
		// Finding a developer and a task
		Developer dev1 = developerRepository.findById(1L).orElse(null);
        Task task1 = taskRepository.findById(1L).orElse(null);

        // Testing them
        assert task1 != null;
        assert dev1 != null;
        
        // Testing getters
        assertEquals(0, task1.getDevelopers().size());
        assertEquals(0, dev1.getTasks().size());

        // Testing addDeveloper
        task1.addDeveloper(dev1);
        assertEquals(1, task1.getDevelopers().size());
        assertEquals(1, dev1.getTasks().size());

	}
	

}
