package testfiles;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businesslogic.*;
import database.MySQLHandler;
import database.PersistenceHandler;

public class ProjectTest {
	Project project;
	HumanResource humanResource;
	TechResource techResource;
	
	@Before
	public void initializeProject(){
		PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		project = new Project();
		project.setName("P1");
		project.setDescription("NA");
		project.setStartDate(LocalDate.of(2021, 12, 20));
		project.setEndDate(LocalDate.of(2021, 12, 25));
		project.setBudget(50000);
		humanResource = new HumanResource(new Employee("Developer", "E1", "NA", 23000));
		project.getHumanResources().add(humanResource);
		techResource = new TechResource("WorkStation", 100, 20);
		project.getTechResources().add(techResource);
	}
	
	@Test
	public void getProjectTest() {
		assertEquals("P1", project.getName());
		assertEquals("NA", project.getDescription());
		assertEquals(50000, project.getBudget(), 0.1);
		assertEquals(LocalDate.of(2021, 12, 20), project.getStartDate());
		assertEquals(LocalDate.of(2021, 12, 25), project.getEndDate());
	}

	@Test
	public void getActualCostTest() {
		assertEquals(25000, project.getActualCost(), 0.1);
	}
	
	@Test
	public void saveHumanResourceTest() {
		project.saveHumanResource(humanResource);
		List<HumanResource> humanResources =  project.getHumanResourcesfromDB();
		assertEquals(23000, humanResources.get(0).getCost(), 0.1);
		assertEquals(project, humanResource.getProject());
	}
	
	@Test
	public void saveTechResourceTest() {
		project.saveTechResource(techResource);
		List<TechResource> techResources =  project.getTechResourcesfromDB();
		assertEquals(2000, techResources.get(0).getCost(), 0.1);
		assertEquals(project, techResource.getProject());
	}
	
	@Test
	public void addTaskDetailsTest() {
		Task task = project.addTaskDetails("T1", "NA", LocalDate.of(2021, 12, 20), LocalDate.of(2021, 12, 25));
		assertEquals("T1", task.getName());
	}
	
	@Test
	public void getVarianceTest() {
		assertEquals(25000, project.getVariance(), 0.1);
	}

}
