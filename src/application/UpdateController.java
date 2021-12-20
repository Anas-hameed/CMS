package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void UpdateAction(ActionEvent event) {

    }
    
    public void SetTextfied(String name, String Pos, double salary, String Cont) {
    	UpdateName.setText(name);
    	UpdatePos.setText(Pos);
    	UpdateSalary.setText(salary+"");
    	UpdateContact.setText(Cont);
    }

}
