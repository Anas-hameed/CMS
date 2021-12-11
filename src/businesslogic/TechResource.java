package businesslogic;

import javax.persistence.Entity;

@Entity
public class TechResource extends Resource{
	private int workStations, networks, electricityCost, databasesNo, servers;

	public TechResource(int workStations, int networks, int electricityCost, int databasesNo, int servers) {		
		this.workStations = workStations;
		this.networks = networks;
		this.electricityCost = electricityCost;
		this.databasesNo = databasesNo;
		this.servers = servers;
	}

	@Override
	public double getCost() {
		double total = 0;
		total = workStations*1000 + networks*1000 + electricityCost + databasesNo*1000 + servers*1000;
		setCost(total);
		return cost;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;		
	}

	public int getWorkStations() {
		return workStations;
	}

	public void setWorkStations(int workStations) {
		this.workStations = workStations;
	}

	public int getNetworks() {
		return networks;
	}

	public void setNetworks(int networks) {
		this.networks = networks;
	}

	public int getElectricityCost() {
		return electricityCost;
	}

	public void setElectricityCost(int electricityCost) {
		this.electricityCost = electricityCost;
	}

	public int getDatabases() {
		return databasesNo;
	}

	public void setDatabases(int databases) {
		this.databasesNo = databases;
	}

	public int getServers() {
		return servers;
	}

	public void setServers(int servers) {
		this.servers = servers;
	}
	
}
