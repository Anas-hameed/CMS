package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import businesslogic.Employee;
import businesslogic.HumanResource;
import businesslogic.Project;
import businesslogic.ProjectManager;
import businesslogic.Task;
import businesslogic.TechResource;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ProjectController {
	
	ProjectManager projectManager = ProjectManager.getInstance();	

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
    private TextField EmpName;
    
    @FXML
    private TextField Position;
    
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

    @FXML
    private TableColumn<Task, String> Description;

    @FXML
    private TableColumn<Task, LocalDate> EndDate;

    @FXML
    private TableColumn<Task, String> Name;

    @FXML
    private TableColumn<Task, LocalDate> StartDate;

    @FXML
    private TableColumn<Task, Integer> TaskID;

    @FXML
    private TableView<Task> TasksTable;
    
    @FXML
    private TableView<HumanResource> EmpTable;

    @FXML
    private TableColumn<HumanResource, String> EmpTableContact;

    @FXML
    private TableColumn<HumanResource, Integer> EmpTableID;

    @FXML
    private TableColumn<HumanResource, String> EmpTableName;

    @FXML
    private TableColumn<HumanResource, String> EmpTablePosition;

    @FXML
    private TableColumn<HumanResource, Double> EmpTableSalary;
    
    @FXML
    private TableColumn<TechResource, Double> TRBaseCost;

    @FXML
    private TableColumn<TechResource, String> TRType;

    @FXML
    private TableColumn<TechResource, Integer> TRQuantity;

    @FXML
    private TableView<TechResource> TRTable;
    
    @FXML
    private Text MangerGreeting;
    
    @FXML
    private ComboBox<String> performanceEval;    
    
    // Controller value for the front-end Logic
    String bgcolor="-fx-background-color: #00008c;";
    String RemoveBg= "-fx-background-color: none;";
    static public int Index= 0;
    List<String> EmpOption;
    List<String> TechResources;
    
    
    @FXML
	void initialize() throws Exception{
    	Home.setStyle("-fx-background-color: none;");
    	combbox.setStyle(bgcolor);
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
    	// Tech Resources
    	if(TechResourcesTypes!=null)
    	{
			TechResources = new ArrayList<String>();
			TechResources.add("WorkStation");
			TechResources.add("Network");
			TechResources.add("Electricity");
			TechResources.add("Databases");
			TechResources.add("Server");
			TechResourcesTypes.getItems().addAll(TechResources);
		}
    	if(TasksTable != null)
    	{
    		FetchTasks();
    	}
    	if(EmpTable != null)
    	{
    		FetchEmployees();
    		addButtonToHumanResourceTable();
    	}
    	if(TRTable != null)
    	{
    		FetchTechResources();
    		addButtonToTechResourceTable();
    	}
    	
    } 
    
    private void addButtonToTechResourceTable() {
        TableColumn<TechResource, Void> colBtn = new TableColumn("Modify");

        Callback<TableColumn<TechResource, Void>, TableCell<TechResource, Void>> cellFactory = new Callback<TableColumn<TechResource, Void>, TableCell<TechResource, Void>>() {
            @Override
            public TableCell<TechResource, Void> call(final TableColumn<TechResource, Void> param) {
                final TableCell<TechResource, Void> cell = new TableCell<TechResource, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	TechResource data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            System.out.println("TechResource ID::"+  data.getResourceID());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        TRTable.getColumns().add(colBtn);
    }
    
    private void addButtonToHumanResourceTable() {
        TableColumn<HumanResource, Void> colBtn = new TableColumn("Modify");

        Callback<TableColumn<HumanResource, Void>, TableCell<HumanResource, Void>> cellFactory = new Callback<TableColumn<HumanResource, Void>, TableCell<HumanResource, Void>>() {
            @Override
            public TableCell<HumanResource, Void> call(final TableColumn<HumanResource, Void> param) {
                final TableCell<HumanResource, Void> cell = new TableCell<HumanResource, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	HumanResource data = getTableView().getItems().get(getIndex());
                            Employee emp= data.getEmployee();
                        	System.out.println("selectedData: " + data);
                            FXMLLoader loader= new FXMLLoader();
                            loader.setLocation(getClass().getResource("/application/Sample.fxml"));
                            try {
                            	loader.load();
                            }
                           catch(Exception err){
                        	   System.out.println("Error While Loadings");
                            }
                            UpdateController p  = loader.getController();
                            p.SetTextfied(emp.getName(), emp.getPosition(), emp.getSalary(), emp.getContact());
                            Parent parent = loader.getRoot(); 
                            Stage stage= new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                                      
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        EmpTable.getColumns().add(colBtn);
    }
        
    private void FetchData() {
     	List<Project> projects = projectManager.getProjectsfromDB();    	
    	List<String> projectNames = new ArrayList<String>();
    	String projName=null;
    	int temp=0;
    	for (Project project : projects) {
    		String pname = project.getName();
    		if(pname.length() > 15) {
    			String[] words = pname.split(" ");
    			projectNames.add(words[0]);
    		}
    		else
    		projectNames.add(project.getName());
    		if(temp==Index) {
    			projName=pname;
    		}
    		temp++;
		}
    	if(ProjectName!=null){
    		ProjectName.setText(projName);
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
		}
		combbox.getSelectionModel().select(Index);
    }
    
    private void FetchTasks() {
    	ObservableList<Task> tasks = FXCollections.observableList(projectManager.getProjects().get(Index).getProjectTasksfromDB());  	
     	Name.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getName()));
     	Description.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getDescription()));
     	StartDate.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<LocalDate>(CellDataFeatures.getValue().getStartDate()));
     	EndDate.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<LocalDate>(CellDataFeatures.getValue().getEndDate()));
    	TaskID.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<Integer>(CellDataFeatures.getValue().getTaskID()));
    	TasksTable.setItems(tasks);
    }
    
    private void FetchEmployees() {
    	ObservableList<HumanResource> humanResources = FXCollections.observableList(projectManager.getProjects().get(Index).getHumanResourcesfromDB());  	
    	EmpTableName.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getEmployee().getName()));
    	EmpTableContact.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getEmployee().getContact()));
    	EmpTablePosition.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getEmployee().getPosition()));
    	EmpTableSalary.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<Double>(CellDataFeatures.getValue().getEmployee().getSalary()));
    	EmpTableID.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<Integer>(CellDataFeatures.getValue().getEmployee().getEmpID()));
    	EmpTable.setItems(humanResources);
    }
    
    private void FetchTechResources() {
    	ObservableList<TechResource> techResources = FXCollections.observableList(projectManager.getProjects().get(Index).getTechResourcesfromDB());  	    
    	TRType.setCellValueFactory(CellDataFeatures -> new ReadOnlyStringWrapper(CellDataFeatures.getValue().getName()));
    	TRBaseCost.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<Double>(CellDataFeatures.getValue().getBaseCost()));
    	TRQuantity.setCellValueFactory(CellDataFeatures -> new ReadOnlyObjectWrapper<Integer>(CellDataFeatures.getValue().getQuantity()));
    	TRTable.setItems(techResources);
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
    	Index= index;
    	loadScene(event, "ProjectPages.fxml");	
    }

    @FXML
    void loadlogoutAction(ActionEvent event) throws Exception {
    	combbox.setStyle(RemoveBg);
    	Home.setStyle(RemoveBg);
    	Logout.setStyle(bgcolor);
    	loadborderScene(event, "SignInPage.fxml");
    }
    
    @FXML
    void ShowResultPageAction(ActionEvent event) throws Exception {
    	Home.setStyle("-fx-background-color: none;");
    	performanceEval.setStyle(bgcolor);
    	int index= performanceEval.getSelectionModel().getSelectedIndex();
    	SampleController.Index= index;
    	System.out.println("Index is ::" + index);
    	loadScene(event, "PerformancePage.fxml");	
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
    void addTaskAction(ActionEvent event) throws Exception {
    	String nm=TaskName.getText(); 
    	String detail=taskDetails.getText();
    	LocalDate sd=  TaskStartDate.getValue();
		LocalDate Ed=  TaskEndDate.getValue();
		if(nm.isEmpty() || detail.isEmpty()) {
			showDialog("Please fill out all the fields");
			return ;
		}
    	if(sd.isAfter(Ed))
		{
			showDialog("End Date cann't be before Start date");
			return ;
		}
    	
    	projectManager.addProjectTask(Index, nm, detail, sd,Ed);
    	loadScene(event, "ProjectPages.fxml");	
   
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
    void AddResourceAction(ActionEvent event) throws Exception {
    	String pos, nm, cont, wage; 
    	pos= Position.getText();
    	nm= EmpName.getText();
    	cont= ContactInfo.getText();
    	wage=Salary.getText();
    	if(pos.isEmpty() || nm.isEmpty() || cont.isEmpty() || wage.isEmpty()) {
    		showDialog("Please enter budget Correctly, Integer value Only");
    		return;
    	}
    	int pay=0;
    	try {
    		pay=   Integer.valueOf(wage);
    	}
    	catch(Exception err) {    	
    		showDialog("Please enter Salary Correctly, Integer value Only");
    		return;
    	}
    	projectManager.getProjects().get(Index).saveHumanResource(new HumanResource(new Employee(pos, nm, cont, pay)));
    	loadScene(event, "ResourcesForm.fxml");	
    }
    
    

    @FXML
    void ShowEmployeeType(ActionEvent event) {

    }
   

    @FXML
    void LoadResourcesPage(ActionEvent event) {

    }
    
    
    // Tech Resources goes Here
    @FXML
    void addtechResources(ActionEvent event) throws Exception {
    	projectManager.getProjects().get(Index).saveTechResource(new TechResource(TechResourcesTypes.getValue(), Double.valueOf(BaseCost.getText()), Integer.valueOf(Quantity.getText())));
    	loadScene(event, "Techresources.fxml");	
    }

    @FXML
    void GobackResourcePage(ActionEvent event) throws Exception {    	
    	loadScene(event, "ResourcesForm.fxml");
    }

    @FXML
    void loadtechresources(ActionEvent event) {

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
