package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.shadowsecure.account.AccountValidation;
import com.shadowsecure.hashing.MD5Hash;
import com.shadowsecure.utils.WindowUtils;
import com.shadowsecure.view.LoadView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {

	@FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button minimizeButton;
    
    @FXML
    private Button registerButton;
    
    @FXML 
    private Hyperlink issuesHyperlink;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		WindowUtils.minimizeWindow(anchorPane, minimizeButton);		
		WindowUtils.closeApplication(anchorPane, closeButton);
		registerButton.setOnAction(event -> LoadView.loadRegisterWindow(this.getClass()));	
		issuesHyperlink.setOnAction(event -> LoadView.loadForgotWindow(this.getClass()));
		submitButton.setOnAction(event -> login());
	}
	
	private void login() {
		
		AccountValidation accountVal = new AccountValidation();
		
		MD5Hash hash = new MD5Hash();
		hash.setStringToHash(passwordField.getText());
		String hashedPassword = hash.hash();		
		accountVal.setPassword(hashedPassword);
		
		hash.setStringToHash(userTextField.getText());
		String hashedUsername = hash.hash();		
		accountVal.setUserName(hashedUsername);
		
		if(accountVal.doesAccountExist()) {
			String title = "LOGGED IN";
			String message = "IT WORKS";
			DialogController.showDialog(title, message, new Image(DialogController.SUCCESS_ICON));
		} else {
			String title = "Login Unsuccessful";
			String message = "Your email address or password is incorrect.";
			DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
		}
	}
}
