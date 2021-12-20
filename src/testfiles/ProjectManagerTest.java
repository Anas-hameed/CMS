package testfiles;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businesslogic.*;
import database.MySQLHandler;
import database.PersistenceHandler;

public class ProjectManagerTest {	
	Project project;
	ProjectManager projectManager;
	
	@Before
	public void initializeProjectManager(){
		PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		projectManager = new ProjectManager("PM1", "NA", "hunaid", "123");
		project = new Project();
		project.setName("P1");
		project.setDescription("NA");
		project.setStartDate(LocalDate.of(2021, 12, 20));
		project.setEndDate(LocalDate.of(2021, 12, 25));
		project.setBudget(50000);
		projectManager.getProjects().add(project);
		
	}

	@Test
	public void getProjectManagerTest() {
		projectManager.setName("PM1");
		projectManager.setUsername("hunaid");
		projectManager.setPassword("123");
		assertEquals("PM1", projectManager.getName());
		assertEquals("hunaid", projectManager.getUsername());
		assertEquals("123", projectManager.getPassword());
	}
	
	@Test
	public void saveProjectManagerTest() {
		projectManager.saveProjectManager();
		List<String> list1 =  projectManager.verify("anas", "123");
		assertNull(list1);
		List<String> list2 =  projectManager.verify("hunaid", "123");
		assertNotNull(list2);
	}
	
	@Test
	public void addProjectTaskTest() {		
		projectManager.addProjectTask(0, "T1", "NA", LocalDate.of(2021, 12, 20), LocalDate.of(2021, 12, 25));
		List<Task> tasks = projectManager.getProjects().get(0).getProjectTasksfromDB();
		assertNotNull(tasks);
	}
	
	@Test
	public void saveProjectTest() {
		projectManager.saveProject(project);
		List<Project> projects = projectManager.getProjectsfromDB();
		assertEquals("P1", projects.get(0).getName());
	}

}
