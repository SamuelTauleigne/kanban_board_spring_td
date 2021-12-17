package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class TaskControllerTest extends ControllerTest {

	/*
	@Autowired
    private DeveloperService developerService;
    */
	
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
    
}
