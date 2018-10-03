package com.shadowsecure.listeners;

import com.shadowsecure.view.Validations;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.PasswordField;

public class PasswordVerificationListener implements ChangeListener<String>{
	
	private PasswordField passwordField;
	
	public PasswordVerificationListener(PasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		
		if(observable.getValue().length() == 0) {
			passwordField.setStyle("textbox");
		} else {
			
			if(Validations.isPasswordValid(newValue)) {				
				passwordField.setStyle("-fx-border-color: green;");					
			} else if(!Validations.isPasswordValid(newValue)) {
				passwordField.setStyle("-fx-border-color: red;");
			}	
		}
	}
}
