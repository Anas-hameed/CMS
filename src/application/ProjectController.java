package application;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectController {

    @FXML
    private Text ProjectName;

    @FXML
    private TextField TaskName;

    @FXML
    private Button Logout;

    @FXML
    private DatePicker TaskStartDate;

    @FXML
    private Button Addresource;

    @FXML
    private ComboBox<String> combbox;

    @FXML
    private DatePicker TaskEndDate;

    @FXML
    private Button Home;

    @FXML
    private TextField taskDetails;

    @FXML
    private Button addTask;
    
    @FXML
    private Button AddResourcesBtn;
    
    @FXML
    private TextField Salary;

    @FXML
    private Button GobackTask;

    @FXML
    private Button AddTechResources;

    @FXML
    private TextField ContactInfo;

    @FXML
    private ComboBox<String> SelectEmpTypeCombobox;

    @FXML
    private TextField EmpName;
    
    // Tech Resources Values
    @FXML
    private Button Techresources;

    @FXML
    private TextField BaseCost;

    @FXML
    private TextField Quantity;

    @FXML
    private Button addTechResources;

    @FXML
    private Button GobackResource;
    
    @FXML
    private ComboBox<String> TechResourcesTypes;
    
    
    // Controller value for the front-end Logic
    String bgcolor="-fx-background-color: #00008c;";
    String RemoveBg= "-fx-background-color: none;";
    static public int Index= 0;
    List<String> EmpOption;
    List<String> TechResources;
    
    
    @FXML
	void initialize() throws Exception{
    	System.out.println("Hello World Anas");
    	ObservableList<String> list= FXCollections.observableArrayList("Project1", "Project2", "Project3", "Project4");
    	if(combbox!=null) {
			combbox.setItems(list);
			combbox.getSelectionModel().select(Index);
		}
    	Home.setStyle("-fx-background-color: none;");
    	combbox.setStyle(bgcolor);
    	if(SelectEmpTypeCombobox!=null) {
    		SelectEmpTypeCombobox.setPromptText("Select the Employee Type");
	    	EmpOption = new ArrayList<String>();
	    	EmpOption.add("Developer");
	    	EmpOption.add("Designer");
	    	EmpOption.add("Network Manager");
	    	EmpOption.add("Resource Manager");
	    	EmpOption.add("Project Manager");
	    	SelectEmpTypeCombobox.getItems().addAll(EmpOption);
    	}
    	
    	// Tech Resources
    	if(TechResourcesTypes!=null)
    	{
    		System.out.println("Hello Mona");
    		TechResourcesTypes.setPromptText("Select the Employee Type");
			TechResources = new ArrayList<String>();
			TechResources.add("Developer");
			TechResources.add("Designer");
			TechResources.add("Network Manager");
			TechResources.add("Resource Manager");
			TechResources.add("Project Manager");
			TechResourcesTypes.getItems().addAll(TechResources);
		}
    	
    	
    }
    
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
    void loadHome(ActionEvent event) throws Exception {
    	Home.setStyle(bgcolor);
    	combbox.setStyle(RemoveBg);
    	loadScene(event, "ManagerPanelPage.fxml");
    	
    }

    @FXML
    void ShowMenuitem(ActionEvent event) {
    	System.out.println("Hello world, Anas");
    }

    @FXML
    void loadlogoutAction(ActionEvent event) throws Exception {
    	combbox.setStyle(RemoveBg);
    	Home.setStyle(RemoveBg);
    	Logout.setStyle(bgcolor);
    	loadborderScene(event, "SignInPage.fxml");
    }
    
    @FXML
    void loadTaskPage(ActionEvent event) throws Exception {
    	loadScene(event, "ProjectPages.fxml");	
    }
    
    @FXML
    void AddResourcesPage(ActionEvent event) throws Exception {
    	loadScene(event, "ResourcesForm.fxml");	
    }

    // Task Page Actions
    @FXML
    void addTaskAction(ActionEvent event) {
    	
    }
    
    // Resource Page Actions
    @FXML
    void ShowResources(ActionEvent event) {

    }
    
    @FXML
    void LoadTechResourcesPage(ActionEvent event) throws Exception {
    	System.out.println("Hello");
    	loadScene(event, "Techresources.fxml");	
    }
    
    @FXML
    void AddResourceAction(ActionEvent event) {

    }
    

    @FXML
    void ShowEmployeeType(ActionEvent event) {

    }

   

    @FXML
    void LoadResourcesPage(ActionEvent event) {

    }
    
    
    // Tech Resources goes Here

    @FXML
    void addtechResources(ActionEvent event) {

    }

    @FXML
    void GobackResourcePage(ActionEvent event) throws Exception {
    	loadScene(event, "ResourcesForm.fxml");
    }

    @FXML
    void loadtechresources(ActionEvent event) {

    }   
}
