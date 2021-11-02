package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class KanbanBoardSpringApplicationTests {

	@Test
	void contextLoads() {
	}
	
}
