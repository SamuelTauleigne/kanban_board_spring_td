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
class DeveloperControllerTest extends ControllerTest {
	
    @Test
    public void getDevelopersTest() throws Exception {
        mockMvc.perform(get("/developers")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk())
        		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", CoreMatchers.is("First")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", CoreMatchers.is("Dev")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].password", CoreMatchers.is("abcd123")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[0].email", CoreMatchers.is("first.dev@mail.com")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname", CoreMatchers.is("Second")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastname", CoreMatchers.is("Dev")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[1].password", CoreMatchers.is("abcdef12345")))
        		.andExpect(MockMvcResultMatchers.jsonPath("$[1].email", CoreMatchers.is("second.dev@mail.com")));
    }

}
