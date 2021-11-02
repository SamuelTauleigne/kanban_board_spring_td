package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class DeveloperRepositoryTest {

	@Autowired
	private DeveloperRepository developerRepository;
	
	@Test
	// Testing findAll of DeveloperRepository
    public void findAllDevelopersTest() {
        Collection<Developer> developers = developerRepository.findAll();
        assertEquals(2, developers.size());
    }

}
