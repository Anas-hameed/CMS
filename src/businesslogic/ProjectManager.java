package businesslogic;

import java.util.List;

import javax.persistence.*;

@Entity
public class ProjectManager extends Employee {
	private String username, password;
	@OneToMany(mappedBy = "projectManager" ,cascade = CascadeType.ALL)
	private List<Project> projects;
	private final static ProjectManager INSTANCE = new ProjectManager();

	private ProjectManager() {
		super("ProjectManager", null, null, 0);
	}
	
	public ProjectManager(String name, String contact, String username, String password) {
		super("ProjectManager", name, contact, 0);
		this.username = username;
		this.password = password;
	}
	
	public static ProjectManager getInstance() {
	    return INSTANCE;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	
}
