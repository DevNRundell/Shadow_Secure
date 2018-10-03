package com.shadowsecure.view;

import javafx.scene.control.TextField;

public class Validations {
	
	public static boolean isPasswordValid(String password) {
		
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
		
		if(password.matches(pattern)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmailValid(String email) {
		
		String pattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		
		if(email.matches(pattern)) {
			return true;
		}

		return false;
	}
	
	public static boolean isTextFieldEmpty(TextField[] textFields) {
		
		for(TextField textField: textFields) {
			
			String text = textField.getText();
			
			if(text.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
}
