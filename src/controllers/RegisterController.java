package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.shadowsecure.account.AccountEmail;
import com.shadowsecure.account.AccountName;
import com.shadowsecure.account.AddAccount;
import com.shadowsecure.database.DBConnect;
import com.shadowsecure.email.SendVerificationEmail;
import com.shadowsecure.hashing.MD5Hash;
import com.shadowsecure.listeners.EmailVerificationListener;
import com.shadowsecure.listeners.PasswordVerificationListener;
import com.shadowsecure.utils.WindowUtils;
import com.shadowsecure.view.Validations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable {

    @FXML
    private TextField fnameTextField;

    @FXML
    private TextField lnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPassField;

    @FXML
    private Button submitButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label passLengthLabel;

    @FXML
    private Label passUppercaseLabel;

    @FXML
    private Label passDigitLabel;
    
    @FXML 
    private AnchorPane anchorPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		WindowUtils.closeWindow(anchorPane, closeButton);
		WindowUtils.minimizeWindow(anchorPane, minimizeButton);
		
		initListeners();
		initActionEvents();
		
	}
	
	private void initActionEvents() {		
		submitButton.setOnAction(event -> validate());	
	}
	
	//Listeners for styling purposes
	private void initListeners() {
		
		passwordField.textProperty().addListener(new PasswordVerificationListener(passwordField));	
		confirmPassField.textProperty().addListener(new PasswordVerificationListener(confirmPassField));		
		emailTextField.textProperty().addListener(new EmailVerificationListener(emailTextField));

	}
	
	private void validate() {
		
		TextField[] textFields = new TextField[] {fnameTextField, lnameTextField, emailTextField, userTextField};	
		ArrayList<String> errorList = new ArrayList<>();
		
		if(Validations.isTextFieldEmpty(textFields)) {	
			errorList.add("Please fill out the form entirely to sign up successfully.");
		} 
		
		if(!Validations.isEmailValid(emailTextField.getText())) {
			errorList.add("Please check to make sure your email is valid.");
		} 
		
		if(!Validations.isPasswordValid(passwordField.getText())) {			
			errorList.add("Please check to make sure your password meets all the requirements.");
		} 
		
		if(!confirmPassField.getText().equals(passwordField.getText()) || confirmPassField.getText().isEmpty()) {
			errorList.add("Please check to make sure your confirmation password matches.");
		} 

		if(!errorList.isEmpty()) {
			DialogController.showDialog("Error(s)", errorList, new Image(DialogController.ERROR_ICON));
		} else {
			
			String firstName, lastName, accountName, email, password, title, message;
			
			firstName = fnameTextField.getText();
			lastName = lnameTextField.getText();
			accountName = userTextField.getText();
			email = emailTextField.getText();
			password = confirmPassField.getText();
			
			AccountEmail emailVal = new AccountEmail();	
			emailVal.setEmail(email);

			if(!emailVal.doesEmailExist()) {
				
				AccountName userAccountVal = new AccountName();
				userAccountVal.setUserName(accountName);
			
				if(!userAccountVal.doesAccountNameExist()) {
					
					createAccount(password, accountName, firstName, lastName, email);
						
				} else {					
					title = "Register Unsuccessful";
					message = accountName + " is already linked to an account. Please created a new account with a different username or sign in.";
					DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
				}
			} else {
				title = "Register Unsuccessful";
				message = email + " is already linked to an account. Please created a new account with a different email or sign in.";
				DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
			}		
		}
	}

	private void createAccount(String password, String accountName, String firstName, String lastName, String email) {
		
		String title, message;
		
		MD5Hash hash = new MD5Hash();
		
		hash.setStringToHash(password);
		String hashedPassword = hash.hash();
		
		hash.setStringToHash(accountName);
		String hashedUsername = hash.hash();
	
		AddAccount addAccount = new AddAccount();
		addAccount.setFirstName(firstName);
		addAccount.setLastName(lastName);
		addAccount.setUserName(accountName);
		addAccount.setLoginName(hashedUsername);
		addAccount.setEmail(email);
		addAccount.setPassword(hashedPassword);			
	
		if(addAccount.addAccount()) {				
			
			SendVerificationEmail verify = new SendVerificationEmail();
			verify.setSmtpHost("localhost");
			verify.setFromEmail("root");
			verify.setPassword("pass");
			verify.setToEmail("root@localhost");
			
			if(!verify.send(hashedUsername)) {
				title = "Register Successful";
				message = "You have successfully registered " + accountName + "," + " but the verification email failed to send to: " + email + ".\n" + "\n" +
						  "Please try to resend the verification link under the issues link on the login page. " + "\n" + "\n" + "We're sorry.";
				DialogController.showDialog(title, message, new Image(DialogController.SUCCESS_ICON));
			} else {
				title = "Register Successful";
				message = "You have successfully registered " + accountName + "!" + " An email has been sent to: " + email;
				DialogController.showDialog(title, message, new Image(DialogController.SUCCESS_ICON));
				
				WindowUtils.closeWindow(anchorPane);
			}
		}
	}
}























































































