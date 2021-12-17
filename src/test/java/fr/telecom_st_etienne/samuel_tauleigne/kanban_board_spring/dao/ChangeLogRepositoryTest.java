package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.ChangeLog;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class ChangeLogRepositoryTest {

	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private ChangeLogRepository changeLogRepository;
		
	@Test
	public void testFindByTaskId() {
		
		Task task = this.taskRepository.findAll().iterator().next();
		
		ChangeLog changeLog = new ChangeLog();
		changeLog.setOccured(LocalDate.now());
		changeLog.setSourceStatus(task.getStatus());
		changeLog.setTargetStatus(task.getStatus());
		changeLog.setTask(task);
		
		this.changeLogRepository.save(changeLog);
				
		List<ChangeLog> changeLogs = this.changeLogRepository.findByTaskId(task.getId());
		
		Assert.assertEquals(1, changeLogs.size());
	}
	
}