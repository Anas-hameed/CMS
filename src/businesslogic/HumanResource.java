package businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import database.MySQLHandler;

@Entity
public class HumanResource extends Resource{
	@OneToMany(mappedBy = "HR", cascade = CascadeType.MERGE)
	private List<Employee> employees = new ArrayList<Employee>();
	@Transient
	private MySQLHandler dbHandler = MySQLHandler.getInstance();

	public HumanResource() {
		super();
	}
	
	@Override
	public double getCost() {
		double total = 0;
		for(int i=0;i<employees.size();i++)
			total += employees.get(i).getSalary();
		return total;
	}
	
	@Override
	public void setCost(double cost) {
		this.cost = cost;		
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void saveEmployee(Employee employee) {
		employees.add(employee);
		employee.setHR(this);
		dbHandler.saveorupdateObject(employee);
	}
	
	public List<Employee> getEmployeesfromDB() {
		return dbHandler.getEmployees(this);
	}
}
