package businesslogic;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projectID;
	private String name, description;
	private LocalDate startDate, endDate;
	private double budget, variance;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Task> tasks;
	@OneToOne(cascade = CascadeType.ALL)
	private TechResource techResource;
	@OneToOne(cascade = CascadeType.ALL)
	private HumanResource humanResource;
	@ManyToOne(cascade = CascadeType.ALL)
	private ProjectManager projectManager;
	
	public Project() {
		this.name = null;
		this.description = null;
		this.startDate = null;
		this.endDate = null;
		this.budget = 0;
		this.variance = 0;
	}
	
	public Project(String name, String description, LocalDate startDate, LocalDate endDate, double budget) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.variance = 0;
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

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getVariance() {
		variance = budget - getActualCost();
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public TechResource getTechResource() {
		return techResource;
	}

	public void setTechResource(TechResource techResource) {
		this.techResource = techResource;
	}

	public HumanResource getHumanResource() {
		return humanResource;
	}

	public void setHumanResource(HumanResource humanResource) {
		this.humanResource = humanResource;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTaskDetails(String name, String description, LocalDate startDate, LocalDate endDate) {
		tasks.add(new Task(name, description, startDate, endDate));
	}
	
	public double getActualCost() {
		return (techResource.getCost() + humanResource.getCost());
	}
	
}
