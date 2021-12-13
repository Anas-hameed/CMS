package businesslogic;

import javax.persistence.*;

@MappedSuperclass
public abstract class Resource {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int resourceID;
	protected double cost;		

	public Resource() {
		this.cost = 0;
	}
	
	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}	

	public abstract double getCost();
	public abstract void setCost(double cost);
}




