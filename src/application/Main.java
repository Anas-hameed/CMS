package application;
	
import database.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import customException.ResourceNotFound;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SignInPage.fxml"));
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Error in opening the file");
		}
	}
	
	public static void main(String[] args) {
		if(args[0].equals("1")) {
			PersistenceHandler.INSTANCE = MySQLHandler.getInstance();
		}
		else {
			PersistenceHandler.INSTANCE = FileHandler.getInstance();
		}
		launch(args);
	}
}
