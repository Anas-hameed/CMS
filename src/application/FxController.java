package application;

import customException.*;
import java.util.Vector;
import businesslogic.ProjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxController {
	
	ProjectManager projectManager = ProjectManager.getInstance();	

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
    private TextField Name;

    @FXML
    private TextField Contact;
   
    
    private void loadBorderPaneScene(ActionEvent event, String file) throws ResourceNotFound {
    	try { 
        	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(file));
    		Scene scene = new Scene(root,600,500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}catch(Exception err) {			
			throw new ResourceNotFound("Error Opening file! file not found");
		}
    }
    
    private void loadAncherPaneScene(ActionEvent event, String file)  throws ResourceNotFound  {
    	try { 
        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(file));
    		Scene scene = new Scene(root,600,500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		primaryStage.setScene(scene);
    		primaryStage.show();
	    }catch(Exception err) {			
			throw new ResourceNotFound("Error Opening file! file not found");
		}
    }
    

    @FXML
    void LoadSignUpPage(ActionEvent event) throws ResourceNotFound {
    	loadBorderPaneScene(event, "SignUpPage.fxml");
    }

    @FXML
    void LoadSigInPage(ActionEvent event) throws ResourceNotFound {
    	loadBorderPaneScene(event, "SignInPage.fxml");
    }
    
    // Sign up page action goes here
    @FXML
    void LoadSignUp(ActionEvent event) throws InvalidInputException {
    	String nm, cont, usr, pswd;
    	nm= Name.getText(); 
    	cont= Contact.getText(); 
    	usr= Username.getText();
    	pswd=UserPassword.getText(); 
    	if(nm.isEmpty() || cont.isEmpty() || usr.isEmpty() || pswd.isEmpty()) {
    		showDialog("Please fills out all the feilds");
    		throw new InvalidInputException("Invalid Input!  Null fields not Allowed");
    	}
    	ProjectManager projectManager = new ProjectManager(nm, cont, usr, pswd);
    	projectManager.saveProjectManager();
    	showDialog("Project Manager account created sucessfully");
    	return;
    }
    
    @FXML
    void LoadSigInScreen(ActionEvent event) throws UnauthorizedAcessResources, InvalidInputException, illegalArgumentException, ResourceNotFound {
    	// authentication for the user required Here
    	String usrname, paswd;
    	usrname= Username.getText() ; 
    	paswd= UserPassword.getText(); 
    	if(usrname.isEmpty() || paswd.isEmpty()) {
    		showDialog("Warning! User name or password cann't be Null");
    		throw new InvalidInputException("Invalid Input! UserName or Password is Null");
    	}
    	
    	Vector<String> managerinfo = projectManager.verify(Username.getText(), UserPassword.getText());
    	if(managerinfo != null) {
    		projectManager.setEmpID(Integer.valueOf(managerinfo.elementAt(0)));
    		projectManager.setName(managerinfo.elementAt(1));
    		projectManager.setContact(managerinfo.elementAt(2));
    		projectManager.setUsername(Username.getText());
    		projectManager.setPassword(UserPassword.getText());
    		projectManager.setProjects(projectManager.getProjectsfromDB());
    		loadAncherPaneScene(event, "ManagerPanelPage.fxml");
    	}
    	else {
    		showDialog("Incorrect Username/Password");
    		throw new UnauthorizedAcessResources("403! User not found");
		}
    }
      
    private void showDialog(String Msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText(null);
    	alert.setContentText(Msg);
    	alert.showAndWait();
    	return;
    }      
}
