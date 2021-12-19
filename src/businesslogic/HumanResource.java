package businesslogic;

import javax.persistence.*;

//import database.MySQLHandler;

@Entity
public class HumanResource extends Resource{
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
	@ManyToOne(cascade = CascadeType.ALL)
	private Project project;

	public HumanResource() {
		super();
	}
	
	public HumanResource(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public double getCost() {
		return employee.getSalary();
	}
	
	@Override
	public void setCost(double cost) {
		this.cost = cost;		
	}	
	
}
