package businesslogic;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int empID;
	protected String position, name, contact;
	protected double salary;
	@ManyToOne(cascade = CascadeType.ALL)
	private HumanResource HR;
	
	public Employee() {
		this.position = null;
		this.name = null;
		this.contact = null;
		this.salary = 0;
	}

	public Employee(String position, String name, String contact, double salary) {
		this.position = position;
		this.name = name;
		this.contact = contact;
		this.salary = salary;
	}
	
	public HumanResource getHR() {
		return HR;
	}

	public void setHR(HumanResource hR) {
		HR = hR;
	}

	public String getPosition() {
		return position;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
