package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.service.impl.DeveloperServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class DeveloperServiceImplTest {

	@Autowired
	private DeveloperServiceImpl developerServiceImpl;
	
	@Test
	void testFindAllDevelopers() {
        assertEquals(2, developerServiceImpl.findAllDevelopers().size());
	}

}
