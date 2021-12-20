package testfiles;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import businesslogic.*;
import database.MySQLHandler;
import database.PersistenceHandler;

public class HumanResourceTest {
	Project project;
	HumanResource humanResource;
	
	@Before
	public void initializeHR(){
		PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		project = new Project("P1", "NA", LocalDate.of(2021, 12, 20), LocalDate.of(2021, 12, 25), 50000);
		humanResource = new HumanResource();
		Employee employee = new Employee("Developer", "E1", "NA", 23000);
		humanResource.setEmployee(employee);
		employee.setHR(humanResource);
		humanResource.setProject(project);
		project.getHumanResources().add(new HumanResource(new Employee("Designer", "E2", "NA", 40000)));
	}

	@Test
	public void getCostTest() {
		assertEquals(23000, humanResource.getCost(), 0.1);
		humanResource.getEmployee().setSalary(25000);
		assertEquals(25000, humanResource.getCost(), 0.1);
		humanResource.getEmployee().setSalary(2000);
		assertEquals(2000, humanResource.getCost(), 0.1);
	}
	
	@Test
	public void getHumanResourceTest() {
		assertEquals(project, humanResource.getProject());
		assertEquals("E1", humanResource.getEmployee().getName());
		assertEquals("NA", humanResource.getEmployee().getContact());
		assertEquals("Developer", humanResource.getEmployee().getPosition());
		assertEquals(humanResource, humanResource.getEmployee().getHR());
	}

}
