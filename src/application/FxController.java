package application;

import java.io.IOException;
import java.util.Vector;

import businesslogic.ProjectManager;
import database.MySQLHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxController {
	
	ProjectManager projectManager = ProjectManager.getInstance();
	MySQLHandler dbHandler = MySQLHandler.getInstance();

    @FXML
    private Button SignUp;

    @FXML
    private TextField Username;

    @FXML
    private Button SignIn;

    @FXML
    private PasswordField UserPassword;
    
    @FXML
    private Button SignUpPage;

    @FXML
    private Button SignInPage;
    
    @FXML
    private ComboBox<String> combbox;

    @FXML
    private TableView<?> table;
    
    @FXML
    private TableColumn<?, ?> Projecttable;
    
    
    private void loadBorderPaneScene(ActionEvent event, String file) {
    	try { 
        	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(file));
    		Scene scene = new Scene(root,600,500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    	catch(Exception err) {
    		System.out.println(err);
    		System.out.println("_________________________________________________");
		}
    }
    
    private void loadAncherPaneScene(ActionEvent event, String file) {
    	try { 
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(file));
    		Scene scene = new Scene(root,600,500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    	catch(Exception err) {
    		System.out.println(err);
    		System.out.println("_________________________________________________");
		}
    }
    

    @FXML
    void LoadSignUpPage(ActionEvent event) throws IOException {
    	loadBorderPaneScene(event, "SignUpPage.fxml");
    }

    @FXML
    void LoadSigInPage(ActionEvent event) {
    	loadBorderPaneScene(event, "SignInPage.fxml");

    }
    
    
    // Sign up page action goes here
    @FXML
    void LoadSignUp(ActionEvent event) {
//    	Username.setText("h2000");
//    	UserPassword.setText("12345");
    	dbHandler.saveorupdateObject(new ProjectManager("hunaid", "na", "h2000", "12345"));
    }
    
    @FXML
    void LoadSigInScreen(ActionEvent event) {
    	// authentication for the user required Here
    	Vector<String> managerinfo = dbHandler.verifyLogin(Username.getText(), UserPassword.getText());
    	if(managerinfo != null) {
    		projectManager.setName(managerinfo.elementAt(0));
    		projectManager.setContact(managerinfo.elementAt(1));
    		projectManager.setUsername(Username.getText());
    		projectManager.setUsername(UserPassword.getText());
    		projectManager.setProjects(dbHandler.getProjects(projectManager));
    		loadAncherPaneScene(event, "ManagerPanelPage.fxml");
    	}
    	
    }
    
    @FXML
    void ShowMenuitem(ActionEvent event) {
    	System.out.println("Hello");

    }

    
}
