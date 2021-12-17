package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.time.LocalDate;
import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.TaskServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class TaskControllerTest extends ControllerTest {
    
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	 
	
    @Test
    public void getTasksTest() throws Exception {
        mockMvc.perform(get("/tasks")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk())
        		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].title", CoreMatchers.is("Add a photos displayer")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nbHoursForecast", CoreMatchers.is(5)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nbHoursReal", CoreMatchers.is(8)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].status.id", CoreMatchers.is(1)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].type.id", CoreMatchers.is(1)));
        // Missing test on created and empty set of developers
    }
    
    @Test
	public void testCreateTask() throws Exception {
		
    	String taskAsJSONString = "{\"title\":\"Titre\",\"nbHoursForecast\":\"0\",\"nbHoursReal\":\"0\",\"created\":\"" + LocalDate.now() + "\"}";
		
		mockMvc.perform(post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(taskAsJSONString))				
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andExpect(MockMvcResultMatchers.content()
			    		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
		Collection<Task> tasks = this.taskServiceImpl.findAllTasks();
		Assert.assertEquals(3, tasks.size());
		Assert.assertEquals("Titre", this.taskServiceImpl.findTask(3L).getTitle());
		Assert.assertEquals(0, this.taskServiceImpl.findTask(3L).getNbHoursForecast().intValue());
		Assert.assertEquals(0, this.taskServiceImpl.findTask(3L).getNbHoursReal().intValue());
		
	}
    
    @Test
    public void moveTaskTest() throws Exception {
    	
    	String taskMoveLeftAsJSONString = "{\"action\":\"MOVE_LEFT\"}";
    	String taskMoveRightAsJSONString = "{\"action\":\"MOVE_RIGHT\"}";

    	mockMvc.perform(patch("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskMoveRightAsJSONString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    	
    	Assert.assertEquals("DOING", this.taskServiceImpl.findTask(1L).getStatus().getLabel());
    	
    	mockMvc.perform(patch("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskMoveLeftAsJSONString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    	
    	Assert.assertEquals("TODO", this.taskServiceImpl.findTask(1L).getStatus().getLabel());
    	
    }
    
}
