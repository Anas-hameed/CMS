module CMS {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires org.hibernate.orm.core;
	requires java.persistence;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens businesslogic to org.hibernate.orm.core;
	
}
