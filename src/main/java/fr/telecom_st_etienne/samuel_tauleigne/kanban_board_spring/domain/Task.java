package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Task {
	
	public @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
	
	private @Column(nullable=false, unique=true) String title;
	private @Column(nullable=false) Integer nbHoursForecast;
	private @Column(nullable=false) Integer nbHoursReal;
	private @Column(nullable=false) LocalDate created;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Developer> developers = new HashSet<>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TaskType type;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TaskStatus status;

	/**
	 * @param title
	 * @param nbHoursForecast
	 * @param nbHoursReal
	 * @param created
	 */
	public Task() {};
	public Task(String title, int nbHoursForecast, int nbHoursReal, LocalDate created, TaskStatus taskStatus, TaskType taskType, Set<Developer> developers) {
        super();
        this.title = title;
        this.nbHoursForecast = nbHoursForecast;
        this.nbHoursReal = nbHoursReal;
        this.created = created;
        this.status = taskStatus;
        this.type = taskType;
        this.developers = developers;
    }
	
	public void addDeveloper(Developer developer){
        this.developers.add(developer); // unidirectional
        developer.addTask(this); // bidirectional
    }

	
}
