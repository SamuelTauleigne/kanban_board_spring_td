package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class KanbanBoardSpringApplicationTests {
	
	@Autowired
	private TaskRepository taskRepository;

	@Test
	void contextLoads() {
		assertThat(taskRepository).isNotNull();
	}
	
}
