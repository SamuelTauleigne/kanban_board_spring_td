package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ChangeLog {

	public @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
	
	public LocalDate occured;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Task task;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TaskStatus sourceStatus;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private TaskStatus targetStatus;

	/**
	 * @param occured
	 * @param task
	 * @param sourceStatus
	 * @param targetStatus
	 */
	public ChangeLog() {}
    public ChangeLog(LocalDate occured, Task task, TaskStatus sourceStatus, TaskStatus targetStatus) {
        this.occured = occured;
        this.task = task;
        this.sourceStatus = sourceStatus;
        this.targetStatus = targetStatus;
    }

	
}
