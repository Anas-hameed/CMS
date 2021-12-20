package businesslogic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import database.MySQLHandler;
import database.PersistenceHandler;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projectID;
	private String name, description;
	private LocalDate startDate, endDate;
	private double budget, variance;
	@OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
	private List<Task> tasks;
	@OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
	private List<TechResource> techResources;
	@OneToMany(mappedBy = "project", cascade = CascadeType.MERGE)
	private List<HumanResource> humanResources;
	@ManyToOne(cascade = CascadeType.ALL)
	private ProjectManager projectManager;		
	@Transient
	private PersistenceHandler dbHandler;
	
	public Project() {
		this.name = null;
		this.description = null;
		this.startDate = null;
		this.endDate = null;
		this.budget = 0;
		this.variance = 0;
		tasks = new ArrayList<Task>();
		techResources = new ArrayList<TechResource>();
		humanResources = new ArrayList<HumanResource>();
		dbHandler = PersistenceHandler.INSTANCE;
	}
	
	public Project(String name, String description, LocalDate startDate, LocalDate endDate, double budget) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.variance = 0;
		tasks = new ArrayList<Task>();
		techResources = new ArrayList<TechResource>();
		humanResources = new ArrayList<HumanResource>();
		dbHandler = PersistenceHandler.INSTANCE;
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<TechResource> getTechResources() {
		return techResources;
	}

	public void setTechResources(List<TechResource> techResources) {
		this.techResources = techResources;
	}

	public List<HumanResource> getHumanResources() {
		return humanResources;
	}

	public void setHumanResources(List<HumanResource> humanResources) {
		this.humanResources = humanResources;
	}
	
	public List<Task> getProjectTasksfromDB() {
		return dbHandler.getProjectTasks(this);
	}
	
	public double getTotalTechResourcesCost() {
		double total = 0;
		for (TechResource techResource : techResources) {
			total += techResource.getCost();
		}
		return total;
	}
	
	public double getTotalHumanResourcesCost() {
		double total = 0;
		for (HumanResource humanResource : humanResources) {
			total += humanResource.getCost();
		}
		return total;
	}
	
	public void saveHumanResource(HumanResource humanResource) {
		humanResource.setProject(this);
		humanResource.getEmployee().setHR(humanResource);
		humanResource.setCost(humanResource.getCost());
		humanResources.add(humanResource);
		dbHandler.saveorupdateObject(humanResource);
	}
	
	public void updateHumanResource(HumanResource humanResource) {
		humanResource.setCost(humanResource.getCost());
		for (HumanResource HR : humanResources) {
			if(HR.getEmployee().getEmpID() == humanResource.getEmployee().getEmpID()) {
				HR.setCost(humanResource.getCost());
				HR.setEmployee(humanResource.getEmployee());
				break;
			}				
		}
		dbHandler.saveorupdateObject(humanResource);
	}
	
	public void saveTechResource(TechResource techResource) {
		techResource.setProject(this);
		techResource.setCost(techResource.getCost());
		techResources.add(techResource);
		dbHandler.saveorupdateObject(techResource);
	}
	
	public void updateTechResource(TechResource techResource) {
		techResource.setCost(techResource.getCost());
		for (TechResource TR : techResources) {
			if(TR.getResourceID() == techResource.getResourceID()) {
				TR.setBaseCost(techResource.getBaseCost());
				TR.setName(techResource.getName());
				TR.setQuantity(techResource.getQuantity());
				TR.setCost(techResource.getCost());
				break;
			}
		}
		dbHandler.saveorupdateObject(techResource);
	}
	
	
	public List<HumanResource> getHumanResourcesfromDB() {
		return dbHandler.getHumanResources(this);
	}
	
	public List<TechResource> getTechResourcesfromDB() {
		return dbHandler.getTechResources(this);
	}
	
	public Task addTaskDetails(String name, String description, LocalDate startDate, LocalDate endDate) {
		Task t = new Task(name, description, startDate, endDate);
		tasks.add(t);
		t.setProject(this);
		return t;
	}
	
	public double getActualCost() {
		return (getTotalHumanResourcesCost() + getTotalTechResourcesCost());
	}
	
}
