package application;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPanelController {

    @FXML
    private Button Logout;

    @FXML
    private Button Projects;

    @FXML
    private Button Home;

    @FXML
    private TableColumn<String, String> Projecttable;
    
    @FXML
    private ComboBox<String> combbox;
    
    @FXML
    private Text ProjectName;

    @FXML
    private TextField TaskName;

    @FXML
    private DatePicker TaskStartDate;

    @FXML
    private Button Addresource;

    @FXML
    private DatePicker TaskEndDate;

    @FXML
    private ComboBox<String> combbox1;

    @FXML
    private TextField taskDetails;

    @FXML
    private Button addTask;
    
    String bgcolor="-fx-background-color: #00008c;";
    String RemoveBg= "-fx-background-color: none;";
    int index=0;
    boolean b=true;
    
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
    	System.out.println("Hello World Anas");
    	if(b)
    	{
    		ObservableList<String> list= FXCollections.observableArrayList("Project1", "Project2", "Project3", "Project4");
	    	if(combbox==null) {
				System.out.println("Hi i am empty combobox");
				return;
			}
	    	combbox.setItems(list);
	    	Platform.runLater(new Runnable() {
	            public void run() {
	                final Node scrollBar = combbox.lookup(".scroll-bar:vertical");
	                scrollBar.setVisible(false);
	            }
	        });
	    }
    	combbox.getSelectionModel().select(index);
    	b=false;
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
    	index= combbox.getSelectionModel().getSelectedIndex(); 
    	System.out.println("Hello World"+index);
    	Home.setStyle("-fx-background-color: none;");
    	combbox.setStyle(bgcolor);
//    	loadScene(event, "ProjectPages.fxml");
    	
    	
    }
    @FXML
    void addTaskAction(ActionEvent event) {

    }

    @FXML
    void ShowResources(ActionEvent event) {

    }

    @FXML
    void AddResourceAction(ActionEvent event) {

    } 
}

