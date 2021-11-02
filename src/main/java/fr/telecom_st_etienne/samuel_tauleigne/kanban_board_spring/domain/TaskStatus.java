package fr.telecom_st_etienne.samuel_tauleigne.kanban_board_spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskStatus {

	public @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
	public @Column(nullable=false, unique=true) String label;
	
	/**
	 * @param label
	 */
	public TaskStatus() {};
	public TaskStatus(String label) {
		super();
		this.label = label;
	}
	
}
