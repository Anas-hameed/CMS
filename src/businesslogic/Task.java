package businesslogic;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskID;
	private String name, description;
	private LocalDate startDate, endDate;
	
	public Task(String name, String description, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
}
