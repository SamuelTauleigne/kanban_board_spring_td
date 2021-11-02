package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.utils;

import java.time.LocalDate;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.ChangeLogRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.DeveloperRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskStatusRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.dao.TaskTypeRepository;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Developer;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.Task;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskStatus;
import fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain.TaskType;

@Configuration
public class LoadDatabase {

    private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    @Profile("test")
    CommandLineRunner initDatabase(DeveloperRepository developerRepository,
                                   TaskRepository taskRepository,
                                   TaskStatusRepository taskStatusRepository,
                                   TaskTypeRepository taskTypeRepository,
                                   ChangeLogRepository changLogRepository) {
        return args -> {
            log.info("Seeding ...");
            
            Developer dev1 = new Developer();
            dev1.setFirstname("First");
            dev1.setLastname("Dev");
            dev1.setPassword("abcd123");
            dev1.setEmail("first.dev@mail.com");
            dev1.setStartContract(LocalDate.now());
            developerRepository.save(dev1);

            Developer dev2 = new Developer();
            dev2.setFirstname("Second");
            dev2.setLastname("Dev");
            dev2.setPassword("abcdef12345");
            dev2.setEmail("second.dev@mail.com");
            dev2.setStartContract(LocalDate.now());
            developerRepository.save(dev2);
            
            TaskType featureType = new TaskType();
            featureType.setLabel("feature");
            taskTypeRepository.save(featureType);
            TaskType docType = new TaskType();
            docType.setLabel("doc");
            taskTypeRepository.save(docType);


            TaskStatus todoStatus = new TaskStatus();
            todoStatus.setLabel("To Do");
            taskStatusRepository.save(todoStatus);
            TaskStatus doingStatus = new TaskStatus();
            doingStatus.setLabel("Doing");
            taskStatusRepository.save(doingStatus);
            TaskStatus doneStatus = new TaskStatus();
            doneStatus.setLabel("Done");
            taskStatusRepository.save(doneStatus);

            Task task1 = new Task();
            task1.setTitle("Add a photos displayer");
            task1.setNbHoursForecast(5);
            task1.setNbHoursReal(8);
            task1.setCreated(LocalDate.now());
            task1.setStatus(todoStatus);
            task1.setType(featureType);
            task1.setDevelopers(new HashSet<>());
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setTitle("Documenting the detection module");
            task2.setNbHoursForecast(5);
            task2.setNbHoursReal(8);
            task2.setCreated(LocalDate.now());
            task2.setStatus(doingStatus);
            task2.setType(docType);
            task2.setDevelopers(new HashSet<>());
            taskRepository.save(task2);

            log.info("Seeding Over !");
        };
    }

}
