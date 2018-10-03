package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.shadowsecure.account.AccountEmail;
import com.shadowsecure.account.AccountHash;
import com.shadowsecure.email.SendVerificationEmail;
import com.shadowsecure.listeners.EmailVerificationListener;
import com.shadowsecure.listeners.PasswordVerificationListener;
import com.shadowsecure.utils.WindowUtils;
import com.shadowsecure.view.Validations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class IssuesAccountController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<String> resetComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userTextField;   
    
    @FXML
    private TextField emailTextField;
    
    @FXML
    private Hyperlink feedbackEmailLaink;

    @FXML
    private Hyperlink supportEmailLink;

    @FXML
    private TextField verifyEmailTextField;

    @FXML
    private Button resendVerifyLinkButton;
    private final ObservableList<String> resetChoices = FXCollections.observableArrayList("Username", "Password");
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		WindowUtils.minimizeWindow(anchorPane, minimizeButton);		
		WindowUtils.closeWindow(anchorPane, closeButton);
		resendVerifyLinkButton.setOnAction(event -> sendVerificationLink());
		
		resetComboBox.setItems(resetChoices);
		resetComboBox.getSelectionModel().select(0);
		passwordField.setDisable(true);
		
		initListeners();
		
	}
	
	private void initListeners() {
		
		resetComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			
			if(newValue.equals("Username")) {
				passwordField.setDisable(true);
				userTextField.setDisable(false);
			} else if(newValue.equals("Password")) {
				userTextField.setDisable(true);
				passwordField.setDisable(false);
			}
		});
		
		passwordField.textProperty().addListener(new PasswordVerificationListener(passwordField));
		emailTextField.textProperty().addListener(new EmailVerificationListener(emailTextField));
		verifyEmailTextField.textProperty().addListener(new EmailVerificationListener(verifyEmailTextField));
	}
	
	private void sendVerificationLink() {
		
		String email = verifyEmailTextField.getText();
		String title, message;
		
		if(!Validations.isEmailValid(email)) {
			title = "Invalid Email";
			message = "Please check to make sure your email is valid.";
			DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
		} else {
			
			AccountEmail accountEmail = new AccountEmail();
			accountEmail.setEmail(email);
			
			if(!accountEmail.doesEmailExist()) {
				title = "Email Does Not Exist";
				message = "Please enter an existing email or create an account";
				DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
			} else {
				
				AccountHash accountHash = new AccountHash();
				accountHash.setEmail(email);
				
				if(accountHash.getAccountHashByEmail()) {
			
					String hashedUsername = accountHash.getAccountHashValue();
				
					SendVerificationEmail verify = new SendVerificationEmail();
					verify.setSmtpHost("localhost");
					verify.setFromEmail("root");
					verify.setPassword("pass");
					verify.setToEmail("root@localhost");

					if(!verify.send(hashedUsername)) {
				
						title = "Email Failed To Send";
						message = "Verification email could not be sent. Please try again or contact support.";
						DialogController.showDialog(title, message, new Image(DialogController.ERROR_ICON));
					} else {
				
						title = "Email Sent";
						message = "A verification email has been sent. If you do not recieve it please try again.";
						DialogController.showDialog(title, message, new Image(DialogController.SUCCESS_ICON));
						
						WindowUtils.closeWindow(anchorPane);
					}
				}
			}
		}
	}
}
