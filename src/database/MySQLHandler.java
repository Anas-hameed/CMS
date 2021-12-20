package database;

import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import businesslogic.*;

public class MySQLHandler {
	private SessionFactory sf;
	private final static MySQLHandler INSTANCE = new MySQLHandler();
	
	private MySQLHandler() {
		Configuration con = new Configuration();
		con.configure().addAnnotatedClass(Employee.class);
        con.configure().addAnnotatedClass(ProjectManager.class);
        con.configure().addAnnotatedClass(Task.class);
        con.configure().addAnnotatedClass(TechResource.class);
        con.configure().addAnnotatedClass(HumanResource.class);
        con.configure().addAnnotatedClass(Project.class);
        sf = con.buildSessionFactory();
	}
	
	public static MySQLHandler getInstance() {
	    return INSTANCE;
	}
	
	public void saveorupdateObject(Object object) {		        
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(object);
        trans.commit();
        session.close();
	}
	
	public Vector<String> verifyLogin(String username, String password) {		        
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.query.Query<ProjectManager> query = session.createQuery("FROM ProjectManager p WHERE p.username = :un and p.password = :pass");
        query.setParameter("un", username);
        query.setParameter("pass", password);
        List<ProjectManager> list = query.getResultList();        
        trans.commit();
        session.close();
        Vector<String> managerStrings = new Vector<String>();
        if(list.size() == 1) {        	
        	managerStrings.add(String.valueOf(list.get(0).getEmpID()));
        	managerStrings.add(list.get(0).getName());
        	managerStrings.add(list.get(0).getContact());
        	return managerStrings;
        }        	
        return null;
	}
	
	public List<Project> getProjects(ProjectManager projectManager) {		        
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.query.Query<Project> query = session.createQuery("FROM Project WHERE projectManager_empID = :pid");
        query.setParameter("pid", projectManager.getEmpID());
        List<Project> projects = query.getResultList();
        for (Project project : projects) {
			project.setHumanResources(getHumanResources(project));
			project.setTasks(getProjectTasks(project));
			project.setTechResources(getTechResources(project));
		}
        trans.commit();
        session.close();
        return projects;
	}
	
	public List<Task> getProjectTasks(Project project) {		        
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.query.Query<Task> query = session.createQuery("FROM Task WHERE project_projectID = :pid");
        query.setParameter("pid", project.getProjectID());
        List<Task> tasks = query.getResultList();                    
        trans.commit();
        session.close();
        return tasks;
	}
	
	public List<HumanResource> getHumanResources(Project project) {		        
        Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.query.Query<HumanResource> query = session.createQuery("FROM HumanResource WHERE project_projectID = :pid");
        query.setParameter("pid", project.getProjectID());
        List<HumanResource> humanResources = query.getResultList();                    
        trans.commit();
        session.close();
        return humanResources;
	}

	public List<TechResource> getTechResources(Project project) {
		Session session = sf.openSession();
        Transaction trans = session.beginTransaction();
        org.hibernate.query.Query<TechResource> query = session.createQuery("FROM TechResource WHERE project_projectID = :pid");
        query.setParameter("pid", project.getProjectID());
        List<TechResource> techResources = query.getResultList();                    
        trans.commit();
        session.close();
        return techResources;
	}
	
}
