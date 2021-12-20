package application;

import java.util.ArrayList;
import java.util.List;

import businesslogic.HumanResource;
import businesslogic.ProjectManager;
import businesslogic.TechResource;
import customException.InvalidInputException;
import customException.illegalArgumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UpdateController {
	
	ProjectManager projectManager = ProjectManager.getInstance();	
	
	@FXML
    private TextField UpdateSalary;

    @FXML
    private TextField UpdatePos;

    @FXML
    private TextField UpdateContact;

    @FXML
    private TextField UpdateName;    

    @FXML
    private ComboBox<String> TechResourcesTypes;

    @FXML
    private TextField UpdatBaseCost;

    @FXML
    private TextField UpdateQuantity;
    
    static public int Index = 0;
    
    HumanResource HR;
    TechResource TR;

    
    @FXML
   	void initialize() throws Exception{
	    if(TechResourcesTypes!=null)
		{
	    	List<String> TechResources= new ArrayList<String>();
			TechResources.add("WorkStation");
			TechResources.add("Network");
			TechResources.add("Electricity");
			TechResources.add("Databases");
			TechResources.add("Server");
			TechResourcesTypes.getItems().addAll(TechResources);
		}
    }
    
    public void setHR(HumanResource humanResource) {
		HR = humanResource;
	}
    
    public void setTR(TechResource techResource) {
		TR = techResource;
	}
    
    public void setProjectIndex(int index) {
		Index = index;		
	}
    
    
    public void SetTextfied(String name, String Pos, double salary, String Cont) {
    	UpdateName.setText(name);
    	UpdatePos.setText(Pos);
    	UpdateSalary.setText(salary+"");
    	UpdateContact.setText(Cont);
    }
    
    public void SetTRTechResource(String TecR , double bcost, int quantity) {
    	TechResourcesTypes.setValue(TecR);
    	UpdatBaseCost.setText(bcost + "");
    	UpdateQuantity.setText(quantity+"");    	
    }

    // Human Resources Update
    @FXML
    void UpdateAction(ActionEvent event)throws Exception {
    	String pos, nm, cont, wage; 
    	pos=UpdatePos.getText();
    	nm= UpdateName.getText();
    	cont=UpdateContact.getText();
    	wage=UpdateSalary.getText();
    	if(pos.isEmpty() || nm.isEmpty() || cont.isEmpty() || wage.isEmpty()) {
    		showDialog("Please fill out all the fields");
    		throw new InvalidInputException("Invalid Input!  Null fields not Allowed");
    	}
    	double pay=0;
    	try {
    		pay=   Double.valueOf(wage);
    	}
    	catch(Exception err) {    	
    		showDialog("Please enter Salary Correctly, Integer value Only");
    		throw new illegalArgumentException("Invalid Argument ! Integer value Expected");
    	}
    	
    	HR.getEmployee().setName(UpdateName.getText());
    	HR.getEmployee().setPosition(UpdatePos.getText());
    	HR.getEmployee().setSalary(Double.valueOf(pay));
    	HR.getEmployee().setContact(UpdateContact.getText());
    	projectManager.getProjects().get(Index).updateHumanResource(HR);
    	showDialog("Update was Sucessfull");
    }
    
    
    // Tech Resources updates
    @FXML
    void UpdateTRAction(ActionEvent event) throws Exception{
    	
    	String techR= TechResourcesTypes.getValue();
    	double bCost;
    	int unit;
    	if(techR.isEmpty()) {
    		showDialog("Please fill out all the fields");
    		throw new InvalidInputException("Invalid Input!  Null fields not Allowed");	
    	}
    	try {
    		bCost= Double.valueOf(UpdatBaseCost.getText());
    		unit= Integer.valueOf(UpdateQuantity.getText());
    	}
    	catch(Exception err) {
    		showDialog("Invalid Input format, Expected Integer or Double value");
    		throw new illegalArgumentException("Invalid Argument ! Integer or Double Expected");
    	}
    	TR.setName(techR);
    	TR.setBaseCost(bCost);
    	TR.setQuantity(unit);
    	projectManager.getProjects().get(Index).updateTechResource(TR);
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
