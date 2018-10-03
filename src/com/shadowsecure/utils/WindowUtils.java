package com.shadowsecure.utils;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowUtils {
	
	public static void minimizeWindow(Pane pane, Button button) {
		
		button.setOnAction(event -> {
			
			Stage stage = (Stage) pane.getScene().getWindow();
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			stage.setIconified(true);
			
		});
	}
	
	public static void closeWindow(Pane pane) {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}
	
	public static void closeWindow(Pane pane, Button button) {
		
		button.setOnAction(event -> {
			
			Stage stage = (Stage) pane.getScene().getWindow();
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			stage.close();
			
		});
	}
	
	public static void closeApplication(Pane pane, Button button) {
		button.setOnAction(event -> System.exit(0));
	}
	
	public static void windowMovement(Stage stage, Pane pane) {
		
		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			  @Override public void handle(MouseEvent mouseEvent) {
				  
				double dragDeltaX;
				double dragDeltaY;
				  
				 //records a delta distance for the drag and drop operation.
				 dragDeltaX = stage.getX() - mouseEvent.getScreenX();
				 dragDeltaY = stage.getY() - mouseEvent.getScreenY();
				 
				 pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
						
					  @Override public void handle(MouseEvent mouseEvent) {
						  
						  stage.setX(mouseEvent.getScreenX() + dragDeltaX);
						  stage.setY(mouseEvent.getScreenY() + dragDeltaY);
					  }
				});
			 }
		});
	}
}
