package com.shadowsecure.launch;

import com.shadowsecure.utils.WindowUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LaunchApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
			Pane root = loader.load();
			Scene scene = new Scene(root);			
			primaryStage.initStyle(StageStyle.TRANSPARENT); 
			primaryStage.setScene(scene);
			primaryStage.setTitle("Shadow Secure - Login");
			primaryStage.getIcons().add(new Image("images/lock_icon.png"));
			primaryStage.setResizable(false);
			primaryStage.show();
			
			WindowUtils.windowMovement(primaryStage, root);

		} catch(Exception e) {
				e.printStackTrace();
		}
	}
}
