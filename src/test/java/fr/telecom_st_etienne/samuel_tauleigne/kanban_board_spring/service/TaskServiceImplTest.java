package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.TaskServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class TaskServiceImplTest {

	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@Test
	void testFindAllTasks() {
		assertEquals(2, taskServiceImpl.findAllTasks().size());
	}

	@Test
	void testFindTask() {
		assertEquals("Add a photos displayer", taskServiceImpl.findTask(1L).getTitle());
		assertEquals("Documenting the detection module", taskServiceImpl.findTask(2L).getTitle());
	}

	@Test
	void testMoveRightTask() {
		Task task = taskServiceImpl.findTask(1L);
		assertEquals("Doing", taskServiceImpl.moveRightTask(task).getStatus().getLabel());
	}

	@Test
	void testMoveLeftTask() {
		Task task;
		task = taskServiceImpl.findTask(1L);
		assertEquals("To Do", taskServiceImpl.moveLeftTask(task).getStatus().getLabel());
		task = taskServiceImpl.findTask(2L);
		assertEquals("To Do", taskServiceImpl.moveLeftTask(task).getStatus().getLabel());
	}

}
