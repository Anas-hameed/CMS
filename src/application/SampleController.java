package application;

import java.util.ArrayList;
import java.util.List;

import businesslogic.Project;
import businesslogic.ProjectManager;
import businesslogic.Task;
import customException.ResourceNotFound;
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
import javafx.scene.control.Label;
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
    private Label budgetLabel;

    @FXML
    private Label varianceLabel;

    @FXML
    private ComboBox<String> combbox;

    @FXML
    private BarChart<String, Double> HRBarGraph;
    
    @FXML
    private BarChart<String, Double> TRBarGraph;

    @FXML
    private Button Home;
    
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
 		if(HRBarGraph!=null) {
 			loadBarHRGraphData();
 		}
 		if(TRBarGraph!=null) {
 			loadBarTRGraphData();
 		}
 		if(budgetLabel != null) {
 			budgetLabel.setText(projectManager.getProjects().get(Index).getBudget() + "");
 		}
 		if(varianceLabel != null) {
 			varianceLabel.setText(projectManager.getProjects().get(Index).getVariance() + "");
 		}
     }
 	
	
	private void loadBarHRGraphData() {
		Series<String, Double> set1= new XYChart.Series<>();
    	set1.setName("Human Resources");
    	ObservableList<XYChart.Data<String, Double>> data = FXCollections.observableArrayList();
    	for (int i=0; i<projectManager.getProjects().get(Index).getHumanResources().size(); i++) {
    		data.add(new XYChart.Data(projectManager.getProjects().get(Index).getHumanResources().get(i).getEmployee().getName(), projectManager.getProjects().get(Index).getHumanResources().get(i).getEmployee().getSalary()));
		}
    	set1.setData(data);
    	HRBarGraph.setLegendSide(Side.BOTTOM);
    	HRBarGraph.getData().add(set1);
	}
	
	private void loadBarTRGraphData() {
		Series<String, Double> set1= new XYChart.Series<>();
    	set1.setName("Tech Resources");
    	ObservableList<XYChart.Data<String, Double>> data = FXCollections.observableArrayList();
    	for (int i=0; i<projectManager.getProjects().get(Index).getTechResources().size(); i++) {
    		data.add(new XYChart.Data(projectManager.getProjects().get(Index).getTechResources().get(i).getName(), projectManager.getProjects().get(Index).getTechResources().get(i).getCost()));
		}
    	set1.setData(data);
    	TRBarGraph.setLegendSide(Side.BOTTOM);
    	TRBarGraph.getData().add(set1);
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
	  try {
    		AnchorPane root;
			root = (AnchorPane)FXMLLoader.load(getClass().getResource(file));
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(Exception err) {
    		throw new ResourceNotFound("Error Opening file! file not found");
    	}
    	
    }
    
    void loadborderScene(ActionEvent event, String file) throws Exception{
	   try {
    		BorderPane root;
			root = (BorderPane)FXMLLoader.load(getClass().getResource(file));
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
    void ShowHRGraph(ActionEvent event) throws Exception{
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
