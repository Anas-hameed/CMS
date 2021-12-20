package testfiles;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import businesslogic.*;
import database.MySQLHandler;
import database.PersistenceHandler;

public class TechResourceTest {
	Project project;
	TechResource techResource;
	
	@Before
	public void initializeHR(){
		PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		project = new Project("P1", "NA", LocalDate.of(2021, 12, 20), LocalDate.of(2021, 12, 25), 50000);
		techResource = new TechResource();
		techResource.setName("WorkStation");
		techResource.setBaseCost(2000);
		techResource.setQuantity(5);
		techResource.setCost(2000*5);
		techResource.setProject(project);
		project.getTechResources().add(new TechResource("Server", 100, 1));
	}

	@Test
	public void getCostTest() {
		assertEquals(10000, techResource.getCost(), 0.1);
	}
	
	@Test
	public void getTechResourceTest() {
		assertEquals("WorkStation", techResource.getName());
		assertEquals(2000, techResource.getBaseCost(), 0.1);
		assertEquals(5, techResource.getQuantity());
		assertEquals(project, techResource.getProject());
		
	}

}
