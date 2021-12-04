package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxController {

    @FXML
    private Button SignUp;

    @FXML
    private TextField Username;

    @FXML
    private Button SignIn;

    @FXML
    private PasswordField UserPassword;
    
    @FXML
    private Button SignUpPage;

    @FXML
    private Button SignInPage;
    
    private void loadBorderPaneScene(ActionEvent event, String file) {
    	try { 
        	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(file));
    		Scene scene = new Scene(root,600,500);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		primaryStage.setScene(scene);
    		primaryStage.show();
    	}
    	catch(Exception err) {
    		System.out.println(err);
    		System.out.println("_________________________________________________");
		}
    }
    

    @FXML
    void LoadSignUpPage(ActionEvent event) throws IOException {
    	loadBorderPaneScene(event, "SignUpPage.fxml");
    }

    @FXML
    void LoadSigInPage(ActionEvent event) {
    	loadBorderPaneScene(event, "SignInPage.fxml");

    }
    
    @FXML
    void LoadSignUp(ActionEvent event) {

    }
    @FXML
    void LoadSigInScreen(ActionEvent event) {

    }

}
