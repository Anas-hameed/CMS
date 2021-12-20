package database;

import java.util.List;
import java.util.Vector;

import businesslogic.HumanResource;
import businesslogic.Project;
import businesslogic.ProjectManager;
import businesslogic.Task;
import businesslogic.TechResource;

public abstract class PersistenceHandler {
	public static PersistenceHandler INSTANCE;
	public PersistenceHandler() {
		
	}
	
	public static PersistenceHandler getInstance() {
		return null;
	}
	public abstract void saveorupdateObject(Object object);
	public abstract Vector<String> verifyLogin(String username, String password);
	public abstract List<Project> getProjects(ProjectManager projectManager);
	public abstract List<Task> getProjectTasks(Project project);
	public abstract List<HumanResource> getHumanResources(Project project);
	public abstract List<TechResource> getTechResources(Project project);
}
