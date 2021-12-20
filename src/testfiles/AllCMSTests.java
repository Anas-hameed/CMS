package testfiles;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HumanResourceTest.class, ProjectManagerTest.class, ProjectTest.class, TaskTest.class,
		TechResourceTest.class })
public class AllCMSTests {

}
