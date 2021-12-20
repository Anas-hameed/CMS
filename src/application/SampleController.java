package application;

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
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController {

    @FXML
    private Button GobackTask;

    @FXML
    private Text MangerGreeting;

    @FXML
    private Button Logout;

    @FXML
    private ComboBox<String> combbox;

    @FXML
    private BarChart<String, Double> BarGraph;

    @FXML
    private CategoryAxis ResourcesName;

    @FXML
    private Button Home;

    @FXML
    private NumberAxis Cost;
    
    @FXML
    private ComboBox<String> performanceEval;
    
    @FXML
    private Text ProjectName;

    
    public static int Index= 0;
	ProjectManager projectManager = ProjectManager.getInstance();	
	String bgcolor="-fx-background-color: #00008c;";
    String RemoveBg= "-fx-background-color: none;";
    
    
 	@FXML
   	void initialize() throws Exception{
 		performanceEval.setStyle(bgcolor);
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
     	}
 		if(BarGraph!=null) {
 			loadBarGraphData();
 		}
     }
 	
	
	private void loadBarGraphData() {
		Series<String, Double> set1= new XYChart.Series<>();
    	set1.setName("Human(Resources)");
    	set1.getData().add(new XYChart.Data("James", 600));
    	set1.getData().add(new XYChart.Data("Anas", 200));
    	set1.getData().add(new XYChart.Data("Mona", 300));
    	set1.getData().add(new XYChart.Data("Shami", 100));
    	set1.getData().add(new XYChart.Data("Humza", 1000));
    	BarGraph.setLegendSide(Side.BOTTOM);
    	BarGraph.getData().add(set1);
	}
	
        
    private void FetchData() {
     	List<Project> projects = projectManager.getProjectsfromDB();    	
    	List<String> projectNames = new ArrayList<String>();
    	int i=0;
    	for (Project project : projects) {
    		String pname = project.getName();
    		if(pname.length() > 15) {
    			String[] words = pname.split(" ");
    			projectNames.add(words[0]);
    		}
    		else
    		projectNames.add(project.getName());
    		if(Index==i && ProjectName!=null) {
    			ProjectName.setText(pname);
    		}
    		i++;
		}
    	ObservableList<String> list = null;
    	if(projectNames.size() > 0)
    	list = FXCollections.observableArrayList(projectNames);
		if(list == null)
			combbox.setPromptText("No Projects");
		else 
		{
			combbox.setItems(list);
			performanceEval.setItems(list);
			performanceEval.getSelectionModel().select(Index);
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
    void ShowMenuitem(ActionEvent event) throws Exception {
    	Home.setStyle("-fx-background-color: none;");
    	combbox.setStyle(bgcolor);
    	int index= combbox.getSelectionModel().getSelectedIndex();
    	ProjectController.Index= index;
    	System.out.println("Index is ::" + index);
    	loadScene(event, "ProjectPages.fxml");	
    }
    
    
    @FXML
    void ShowResultPageAction(ActionEvent event) throws Exception {
    	Home.setStyle("-fx-background-color: none;");
    	performanceEval.setStyle(bgcolor);
    	Index= combbox.getSelectionModel().getSelectedIndex();
    	loadScene(event, "PerformancePage.fxml");	
    }
    
    
    @FXML
    void ShowHRVGraph(ActionEvent event) throws Exception{
    	loadScene(event, "HumanResourceGraph.fxml");
    }
    
    @FXML
    void showTechGraph(ActionEvent event) throws Exception{
    	loadScene(event, "TechResourceGraph.fxml");
    }
   
    @FXML
    void CloseBtn(ActionEvent event) throws Exception{
    	loadScene(event, "PerformancePage.fxml");
    }
    
    @FXML
    void loadlogoutAction(ActionEvent event) throws Exception {
    	combbox.setStyle(RemoveBg);
    	Home.setStyle(RemoveBg);
    	Logout.setStyle(bgcolor);
    	loadborderScene(event, "SignInPage.fxml");
    }
      
    
}
