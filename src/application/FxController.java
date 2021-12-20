package application;

import java.io.IOException;
import java.util.Vector;
import businesslogic.ProjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    	String nm, cont, usr, pswd;
    	nm= Name.getText(); 
    	cont= Contact.getText(); 
    	usr= Username.getText();
    	pswd=UserPassword.getText(); 
    	if(nm.isEmpty() || cont.isEmpty() || usr.isEmpty() || pswd.isEmpty()) {
    		showDialog("Please fills out all the feilds");
    		return;
    	}
    	projectManager.setName(nm);
    	projectManager.setContact(cont);
    	projectManager.setUsername(usr);
    	projectManager.setPassword(pswd);
    	projectManager.saveProjectManager();
    	showDialog("Project Manager account created sucessfully");
    	return;
    }
    
    @FXML
    void LoadSigInScreen(ActionEvent event) {
    	// authentication for the user required Here
    	String usrname, paswd;
    	usrname= Username.getText() ; 
    	paswd= UserPassword.getText(); 
    	if(usrname.isEmpty() || paswd.isEmpty()) {
    		showDialog("Warning! User name or password cann't be Null");
    		return;
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
