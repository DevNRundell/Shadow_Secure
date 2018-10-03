package com.shadowsecure.listeners;

import com.shadowsecure.view.Validations;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class EmailVerificationListener implements ChangeListener<String> {
	
	private TextField textField;

	public EmailVerificationListener(TextField textField) {
		this.textField = textField;
	}
	
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

		if(observable.getValue().length() == 0) {
			textField.setStyle("textbox");
		}
		
		if(!newValue.isEmpty()) {
				
			if(Validations.isEmailValid(newValue)) {
				textField.setStyle("-fx-border-color: green;");
			} else if(!Validations.isEmailValid(newValue)) {
				textField.setStyle("-fx-border-color: red;");
			}	
		} 
	}
}
