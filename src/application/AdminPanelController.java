package application;

import java.util.ArrayList;
import java.util.List;

import businesslogic.Project;
import businesslogic.ProjectManager;
import database.MySQLHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminPanelController {

	ProjectManager projectManager = ProjectManager.getInstance();
	MySQLHandler dbHandler = MySQLHandler.getInstance();
	
	String bgcolor="-fx-background-color: #00008c;";
    String RemoveBg= "-fx-background-color: none;";
	
    @FXML
    private Button Logout;
    
    @FXML
    private Button Home;
    
    @FXML
    private ComboBox<String> combbox;        

    @FXML
    private TextField ProjectName;
  
    @FXML
    private DatePicker ProjectStartDate;
    
    @FXML
    private DatePicker ProjectEndDate;
    
    @FXML
    private TextField Budget;

    @FXML
    private Button addProject;

    @FXML
    private TextField ProjectDetails;
    
    void loadScene(ActionEvent event, String file) throws Exception{
    	AnchorPane root;
		root = (AnchorPane)FXMLLoader.load(getClass().getResource(file));
		Scene scene = new Scene(root,600,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    }
    
    void loadborderScene(ActionEvent event, String file) throws Exception{
    	BorderPane root;
		root = (BorderPane)FXMLLoader.load(getClass().getResource(file));
		Scene scene = new Scene(root,600,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    @FXML
	void initialize() throws Exception{
    	List<Project> projects = dbHandler.getProjects(projectManager);    	
    	List<String> projectNames = new ArrayList<String>();
    	for (Project project : projects) {
    		String pname = project.getName();
    		if(pname.length() > 15) {
    			String[] words = pname.split(" ");
    			projectNames.add(words[0]);
    		}
    		else
    			projectNames.add(project.getName());
		}
    	ObservableList<String> list = null;
    	if(projectNames.size() > 0)
    		list = FXCollections.observableArrayList(projectNames);
    	if(combbox!=null) {
    		if(list == null)
    			combbox.setPromptText("No Projects");
    		else {
    			combbox.setItems(list);
    			combbox.getSelectionModel().select(0);
    			Platform.runLater(new Runnable() {
    				public void run() {
    					final Node scrollBar = combbox.lookup(".scroll-bar:vertical");
    					scrollBar.setVisible(false);
    				}
    			});
			}
			
		}
    }    
    
    @FXML
    void loadHome(ActionEvent event) throws Exception {
    	Home.setStyle(bgcolor);
    	combbox.setStyle(RemoveBg);
    	loadScene(event, "ManagerPanelPage.fxml");
    }

    @FXML
    void loadlogoutAction(ActionEvent event) throws Exception {
    	combbox.setStyle(RemoveBg);
    	Home.setStyle(RemoveBg);
    	Logout.setStyle(bgcolor);
    	loadborderScene(event, "SignInPage.fxml");
    }
        
    @FXML
    void ShowMenuitem(ActionEvent event) throws Exception {
    	Home.setStyle("-fx-background-color: none;");
    	combbox.setStyle(bgcolor);
    	int index= combbox.getSelectionModel().getSelectedIndex();
    	ProjectController.Index= index;
    	System.out.println("Index is ::" + index);
    	loadScene(event, "ProjectPages.fxml");	
    }
      
    // Add project controller
    @FXML
    void addProjectAction(ActionEvent event) {
    	Project project = new Project(ProjectName.getText(), ProjectDetails.getText(), ProjectStartDate.getValue(), ProjectEndDate.getValue(), Integer.valueOf(Budget.getText()));    	
    	projectManager.getProjects().add(project);
    	project.setProjectManager(projectManager);
    	dbHandler.saveorupdateObject(project);
    }
}


