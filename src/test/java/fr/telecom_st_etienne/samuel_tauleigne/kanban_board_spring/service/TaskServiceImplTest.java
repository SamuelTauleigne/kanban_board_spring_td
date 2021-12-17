package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.ChangeLog;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskType;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.DeveloperServiceImpl;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.TaskServiceImpl;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils.Constants;

@SpringBootTest
@ActiveProfiles(profiles="test")
class TaskServiceImplTest {

	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@Autowired
	private DeveloperServiceImpl developerServiceImpl;
	
	@Test
	public void testFindAllTasks() {
		
		Collection<Task> tasks = this.taskServiceImpl.findAllTasks();
		
		assertEquals(2, tasks.size());
	}
	
	@Test
	public void testFindAllTaskTypes() {
		
		Collection<TaskType> taskTypes = this.taskServiceImpl.findAllTaskTypes();
		
		assertEquals(2, taskTypes.size());
	}
	
	@Test
	public void testFindAllTaskStatus() {
		
		Collection<TaskStatus> taskStatus = this.taskServiceImpl.findAllTaskStatus();
		
		assertEquals(3, taskStatus.size());
	}
	
	@Test
	public void testChangeTaskStatus() {
		
		Task task = this.taskServiceImpl.findAllTasks().iterator().next();
		
		TaskStatus status1 = this.taskServiceImpl.findTaskStatus(1L);
		
		TaskStatus status2 = this.taskServiceImpl.findTaskStatus(2L);
		
		task = this.taskServiceImpl.changeTaskStatus(task, status2);
		
		assertEquals(status2, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskServiceImpl.findChangeLogsForTask(task);
		
		assertEquals(1, changeLogs.size());
		
		ChangeLog changeLog = changeLogs.iterator().next();
		
		assertEquals(status1, changeLog.getSourceStatus());
		
		assertEquals(status2, changeLog.getTargetStatus());		
	}
	
	/*
	 * @Test public void testDisplayMoveRightForTask() {
	 * 
	 * TaskStatus todoStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
	 * 
	 * TaskStatus doingStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
	 * 
	 * TaskStatus doneStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
	 * 
	 * Task task = new Task();
	 * 
	 * task.setStatus(todoStatus);
	 * 
	 * assertTrue(this.taskServiceImpl.displayMoveRightForTask(task));
	 * 
	 * task.setStatus(doingStatus);
	 * 
	 * assertTrue(this.taskServiceImpl.displayMoveRightForTask(task));
	 * 
	 * task.setStatus(doneStatus);
	 * 
	 * assertFalse(this.taskServiceImpl.displayMoveRightForTask(task)); }
	 * 
	 * @Test public void testDisplayMoveLeftForTask() {
	 * 
	 * TaskStatus todoStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
	 * 
	 * TaskStatus doingStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
	 * 
	 * TaskStatus doneStatus =
	 * this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
	 * 
	 * Task task = new Task();
	 * 
	 * task.setStatus(todoStatus);
	 * 
	 * assertFalse(this.taskServiceImpl.displayMoveLeftForTask(task));
	 * 
	 * task.setStatus(doingStatus);
	 * 
	 * assertTrue(this.taskServiceImpl.displayMoveLeftForTask(task));
	 * 
	 * task.setStatus(doneStatus);
	 * 
	 * assertTrue(this.taskServiceImpl.displayMoveLeftForTask(task)); }
	 */
	
	@Test
	public void testMoveRightTask() {
		
		Developer developer = this.developerServiceImpl.findAllDevelopers().iterator().next();
		
		TaskStatus todoStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
				
		TaskStatus doneStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		task.setNbHoursForecast(0);
		task.setNbHoursReal(0);
		task.setTitle("title");
		// task.setType(taskServiceImpl.findTaskType(1L));
		task.setStatus(todoStatus);
		task.addDeveloper(developer);
		
		task = this.taskServiceImpl.createTask(task);
		
		task = this.taskServiceImpl.moveRightTask(task);

		assertEquals(doingStatus, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskServiceImpl.findChangeLogsForTask(task);
		
		assertEquals(1, changeLogs.size());
		
		ChangeLog changeLog = changeLogs.iterator().next();
		
		assertEquals(todoStatus, changeLog.getSourceStatus());
		
		assertEquals(doingStatus, changeLog.getTargetStatus());
		
		task = this.taskServiceImpl.moveRightTask(task);
		
		assertEquals(doneStatus, task.getStatus());
		
		changeLogs = this.taskServiceImpl.findChangeLogsForTask(task);
		
		assertEquals(2, changeLogs.size());
		
		this.taskServiceImpl.deleteTask(task);
	}
	
	@Test
	public void testMoveLeftTask() {
		
		Developer developer = this.developerServiceImpl.findAllDevelopers().iterator().next();
				
		TaskStatus todoStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_TODO_ID);
		
		TaskStatus doingStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DOING_ID);
				
		TaskStatus doneStatus = this.taskServiceImpl.findTaskStatus(Constants.TASK_STATUS_DONE_ID);
		
		Task task = new Task();
		task.setNbHoursForecast(0);
		task.setNbHoursReal(0);
		task.setTitle("title");		
		task.addDeveloper(developer);
		
		task = this.taskServiceImpl.createTask(task);
		task = this.taskServiceImpl.moveRightTask(task); // => DOING
		task = this.taskServiceImpl.moveRightTask(task); // => DONE
		
		task = this.taskServiceImpl.moveLeftTask(task);
		
		assertEquals(doingStatus, task.getStatus());
		
		Collection<ChangeLog> changeLogs = this.taskServiceImpl.findChangeLogsForTask(task);
		
		assertEquals(3, changeLogs.size());
		
		boolean lastChangeLogFound = false;
		
		for (ChangeLog changeLog : changeLogs) {
		
			if (doneStatus.equals(changeLog.getSourceStatus())) {
				
				lastChangeLogFound = true;
				
				assertEquals(doingStatus, changeLog.getTargetStatus());
			}
		}
		
		assertTrue(lastChangeLogFound);
		
		task = this.taskServiceImpl.moveLeftTask(task);
		
		assertEquals(todoStatus, task.getStatus());
		
		changeLogs = this.taskServiceImpl.findChangeLogsForTask(task);
		
		assertEquals(4, changeLogs.size());
		
		this.taskServiceImpl.deleteTask(task);
	}

}
