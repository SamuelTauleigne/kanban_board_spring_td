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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Task {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes = "${task.id}", example = "1L", required = true, position = 0)
	public Long id;
	
	@Column(nullable=false, unique=true)
	@NotBlank(message = "title is mandatory")
	@ApiModelProperty(notes = "${task.title}", example = "Titre", required = true, position = 1)
	private String title;
	
	@Column(nullable=false)
	@NotNull(message = "nbHoursForecast can't be null")
	@Min(1)
	@ApiModelProperty(notes = "${task.nbHoursForecast}", example = "15", required = true, position = 2)
	private Integer nbHoursForecast;
	
	@Column(nullable=false)
	@NotNull(message = "nbHoursReal can't be null")
	@ApiModelProperty(notes = "${task.nbHoursReal}", example = "12", required = true, position = 3)
	private Integer nbHoursReal;
	
	@Column(nullable=false)
	@NotNull(message = "created can't be null")
	@ApiModelProperty(notes = "${task.created}", required = true, position = 4)
	private LocalDate created;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return title;
	}

	
}
