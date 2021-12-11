package businesslogic;

import java.util.List;

import javax.persistence.*;

@Entity
public class HumanResource extends Resource{
	@OneToMany(cascade = CascadeType.ALL)
	private List<Employee> employees;

	public HumanResource() {
		//employees = new Vector<Employee>();
	}
	
	@Override
	public double getCost() {
		double total = 0;
		for(int i=0;i<employees.size();i++)
			total += employees.get(i).getSalary();
		setCost(total);
		return cost;
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
}
