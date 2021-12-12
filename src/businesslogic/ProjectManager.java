package businesslogic;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;

import database.MySQLHandler;

@Entity
public class ProjectManager extends Employee {
	private String username, password;
	@OneToMany(mappedBy = "projectManager", cascade = CascadeType.MERGE)
	private List<Project> projects;
	private final static ProjectManager INSTANCE = new ProjectManager();
	@Transient
	private MySQLHandler dbHandler = MySQLHandler.getInstance();

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
	
	public MySQLHandler getDbHandler() {
		return dbHandler;
	}

	public void setDbHandler(MySQLHandler dbHandler) {
		this.dbHandler = dbHandler;
	}
	
	public void saveProjectManager() {
		dbHandler.saveorupdateObject(this);
	}
	
	public void saveProject(Project project) {
		projects.add(project);
    	project.setProjectManager(this);
		dbHandler.saveorupdateObject(project);
	}
		
	public void addProjectTask(int index, String name, String description, LocalDate startDate, LocalDate endDate) {		
		dbHandler.saveorupdateObject(projects.get(index).addTaskDetails(name, description, startDate, endDate));
	}
	
	public List<Project> getProjectsfromDB() {
		return dbHandler.getProjects(this);
	}
	
	public Vector<String> verify(String un, String pass) {
		return dbHandler.verifyLogin(un, pass);
	}
	
}
