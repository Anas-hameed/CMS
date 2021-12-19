package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import businesslogic.Project;
import businesslogic.ProjectManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPanelController {

	ProjectManager projectManager = ProjectManager.getInstance();	
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
    
    @FXML
    private Text MangerGreeting;
    
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
    	if(MangerGreeting!=null)
        {
    		String nm= projectManager.getName();
    		String temp = nm.substring(0,1);
    		temp= temp.toUpperCase();
    		MangerGreeting.setText("Hello "+temp+ nm.substring(1,nm.length())+",");
        }
    	if(combbox!=null)
    	{
    		FetchData();
    		Platform.runLater(new Runnable() {
			public void run() {
				final Node scrollBar = combbox.lookup(".scroll-bar:vertical");
				scrollBar.setVisible(false);
				}
			});
    	}
    }  
        
    private void FetchData() {
     	List<Project> projects = projectManager.getProjectsfromDB();    	
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
		if(list == null)
			combbox.setPromptText("No Projects");
		else 
			combbox.setItems(list);
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
    void addProjectAction(ActionEvent event) throws Exception {
    	//    	Verification of the Entered Values
    	String projname, projdetail;
    	projname=ProjectName.getText();
    	projdetail =ProjectDetails.getText();
    	LocalDate sd=  ProjectStartDate.getValue();
		LocalDate Ed=  ProjectEndDate.getValue();
    	if(projname.isEmpty() || Budget.getText().isEmpty() || sd==null) {
    		showDialog("Please fill out all the fields");
    		return;
    	}
    	int projBg=0;
    	try {    		
    		 projBg=Integer.valueOf(Budget.getText());
    	}
    	catch(Exception err) {
    		showDialog("Please enter budget Correctly, Integer value Only");
    		return;
    	}	
		if(sd.isAfter(Ed))
		{
			showDialog("End Date cann't be before Start date");
			return ;
		}
		Project project = new Project(projname,projdetail , sd,Ed, projBg);
		projectManager.saveProject(project);
		loadScene(event, "ManagerPanelPage.fxml");
    	
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
