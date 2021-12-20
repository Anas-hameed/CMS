package testfiles;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import businesslogic.*;
import database.MySQLHandler;
import database.PersistenceHandler;

public class TaskTest {
	Project project;
	Task task;
	
	@Before
	public void initializeTask(){
		PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		project = new Project();
		project.setName("P1");
		project.setDescription("NA");
		project.setStartDate(LocalDate.of(2021, 12, 20));
		project.setEndDate(LocalDate.of(2021, 12, 25));
		project.setBudget(50000);
		task = new Task();
		task.setName("T1");
		task.setDescription("NA");
		task.setStartDate(LocalDate.of(2021, 12, 20));
		task.setEndDate(LocalDate.of(2021, 12, 22));
		task.setProject(project);
		project.getTasks().add(task);
	}
	
	@Test
	public void getProjectManagerTest() {
		assertEquals("T1", task.getName());
		assertEquals("NA", task.getDescription());
		assertEquals(LocalDate.of(2021, 12, 20), task.getStartDate());
		assertEquals(LocalDate.of(2021, 12, 22), task.getEndDate());
		assertEquals(project, task.getProject());
	}
	

}
