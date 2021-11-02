package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Test
    // Testing save of TaskRepository
    public void saveTaskTest() {
    	// Asserting findAll is still OK
        Collection<Task> tasks = taskRepository.findAll();
        assertEquals(2, tasks.size());

        // Saving a new task
        Task new_task = new Task();
        new_task.setTitle("Add a new contact form");
        new_task.setNbHoursForecast(5);
        new_task.setNbHoursReal(8);
        new_task.setCreated(LocalDate.now());
        new_task.setStatus(taskStatusRepository.findById(1L).orElse(null));
        new_task.setType(taskTypeRepository.findById(1L).orElse(null));
        new_task.setDevelopers(new HashSet<>());
        taskRepository.save(new_task);

        // Asserting the task is saved
        tasks = taskRepository.findAll();
        assertEquals(3, tasks.size());
        assertEquals("To Do", new_task.getStatus().getLabel());
    }
	
	@Test
	// Testing findAll of TaskRepository
    public void findAllTasksTest() {
        Collection<Task> tasks = taskRepository.findAll();
        assertEquals(3, tasks.size());
    }

}
