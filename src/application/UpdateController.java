package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;

public class UpdateController {
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
    

    public void SetTextfied(String name, String Pos, double salary, String Cont) {
    	UpdateName.setText(name);
    	UpdatePos.setText(Pos);
    	UpdateSalary.setText(salary+"");
    	UpdateContact.setText(Cont);
    }
    
    public void SetTRTechResource(String TecR , double bcost, int quantity) {
//    	SingleSelectionModel<String> p= TechResourcesTypes.getSelectionModel();
    	UpdatBaseCost.setText(bcost + "");
    	UpdateQuantity.setText(quantity+"");    	
    }

    // Human Resources Update
    @FXML
    void UpdateAction(ActionEvent event) {

    }
    
    // Tech Resources updates
    @FXML
    void UpdateTRAction(ActionEvent event) {

    }

}
