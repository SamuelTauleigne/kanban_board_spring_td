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

import lombok.Data;

@Data
@Entity
public class Developer {
	
	public @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
	
	private @Column(nullable=false) String firstname;
	private @Column(nullable=false) String lastname;
	private @Column(nullable=false) String password;
	private @Column(nullable=false, unique=true) String email;
	private @Column(nullable=false) LocalDate startContract;
	
	@ManyToMany(mappedBy="developers", fetch=FetchType.EAGER)
	private Set<Task> tasks = new HashSet<>();

	/**
	 * @param firstname
	 * @param lastname
	 * @param password
	 * @param email
	 * @param startContract
	 */
	public Developer() {};
	public Developer(String firstname, String lastname, String password, String email, LocalDate startContract) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.startContract = startContract;
	}

	public void addTask(Task task) {this.tasks.add(task);}

}
