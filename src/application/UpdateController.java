package application;

import java.util.ArrayList;
import java.util.List;

import businesslogic.HumanResource;
import businesslogic.ProjectManager;
import businesslogic.TechResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    void UpdateAction(ActionEvent event) {
    	HR.getEmployee().setName(UpdateName.getText());
    	HR.getEmployee().setPosition(UpdatePos.getText());
    	HR.getEmployee().setSalary(Double.valueOf(UpdateSalary.getText()));
    	HR.getEmployee().setContact(UpdateContact.getText());
    	projectManager.getProjects().get(Index).updateHumanResource(HR);
    }
    
    // Tech Resources updates
    @FXML
    void UpdateTRAction(ActionEvent event) {
    	TR.setName(TechResourcesTypes.getValue());
    	TR.setBaseCost(Double.valueOf(UpdatBaseCost.getText()));
    	TR.setQuantity(Integer.valueOf(UpdateQuantity.getText()));
    	projectManager.getProjects().get(Index).updateTechResource(TR);
    }

	

}
