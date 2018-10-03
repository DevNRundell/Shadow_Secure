package com.shadowsecure.view;

import com.shadowsecure.utils.WindowUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadView {
	
	public static <T> void loadRegisterWindow(Class<T> loadedClass) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(loadedClass.getClassLoader().getResource("fxml/register.fxml"));
			Pane root = loader.load();
			Scene scene = new Scene(root);	
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT); 
			stage.setScene(scene);
			stage.setTitle("Shadow Secure - Register");
			stage.setResizable(false);
			stage.show();
			
			WindowUtils.windowMovement(stage, root);

		} catch(Exception e) {
				e.printStackTrace();
		}
	}
	
	public static <T> void loadForgotWindow(Class<T> loadedClass) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(loadedClass.getClassLoader().getResource("fxml/issues.fxml"));
			Pane root = loader.load();
			Scene scene = new Scene(root);	
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT); 
			stage.setScene(scene);
			stage.setTitle("Shadow Secure - Forgot?");
			stage.setResizable(false);
			stage.show();
			
			WindowUtils.windowMovement(stage, root);

		} catch(Exception e) {
				e.printStackTrace();
		}
	}

}
