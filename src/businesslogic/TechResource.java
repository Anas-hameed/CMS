package businesslogic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TechResource extends Resource{
	private String type;
	private double baseCost;
	private int quantity;
	@ManyToOne(cascade = CascadeType.ALL)
	private Project project;
	
	public TechResource() {
		this.type = null;
		this.baseCost = 0;
		this.quantity = 0;
	}
	
	public TechResource(String type, double baseCost, int quantity) {
		this.type = type;
		this.baseCost = baseCost;
		this.quantity = quantity;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return type;
	}

	public void setName(String type) {
		this.type = type;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

	@Override
	public double getCost() {
		return baseCost * quantity;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;		
	}
	
}
