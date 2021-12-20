package database;
import java.io.*;
import java.util.List;
import java.util.Vector;

import businesslogic.HumanResource;
import businesslogic.Project;
import businesslogic.ProjectManager;
import businesslogic.Task;
import businesslogic.TechResource;

public class FileHandler extends PersistenceHandler {
	private final static FileHandler INSTANCE = new FileHandler();
	
	private FileHandler() {
		
	}

	public static FileHandler getInstance() {
	    return INSTANCE;
	}

	@Override
	public void saveorupdateObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<String> verifyLogin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjects(ProjectManager projectManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getProjectTasks(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HumanResource> getHumanResources(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TechResource> getTechResources(Project project) {
		// TODO Auto-generated method stub
		return null;
	}
}
