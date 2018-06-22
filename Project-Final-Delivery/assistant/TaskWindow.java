package assistant;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.awt.Event;
import java.beans.EventHandler;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TaskWindow{
	private static Task newTask;

	public static Task display(){
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Task");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("New Task");
        Label name = new Label("Name");
        TextField nameField = new TextField();
	
        Label dateLabel = new Label("Pick a Starting Date");
        DatePicker dateofEvent = new DatePicker();

        Label hourLabel = new Label("Insert Starting time (Hour)");
        TextField hourField = new TextField();
        
        Label minutesLabel = new Label("Insert Starting time (Minutes)");
        TextField minutesField = new TextField();
        
        Label duration = new Label("Duration (Hours)");
        TextField durationField = new TextField();
        
        Button createButton = new Button("Create Task");
        Button cancel = new Button("Cancel");
        
        createButton.setOnAction(e -> {
        	
            LocalDate newDate = dateofEvent.getValue();
            String timeField = hourField.getText() + ":" + minutesField.getText() + ":00";
            LocalTime newTime = LocalTime.parse(timeField); 
            
            if(newDate.isAfter(LocalDate.now()) || (newDate.isEqual(LocalDate.now()) && newTime.isAfter(LocalTime.now()))){
            	int nDays = (Integer.parseInt(durationField.getText()) + Integer.parseInt(hourField.getText()))/24;
            	int nHours = Integer.parseInt(durationField.getText())%24;
            	
            	LocalDate endDate = dateofEvent.getValue().plusDays(nDays);
            	LocalTime endTime = newTime.plusHours(nHours);
            	
            	newTask = new Task(nameField.getText(), newTime, endTime, dateofEvent.getValue(), endDate);
            }
            newTask.print();
            if (newTask != null){
            	window.close();
            }
        });
        
        
        cancel.setOnAction(e -> {
            newTask = null;
            window.close();
        });
        
        
        
        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, name, nameField, dateLabel, dateofEvent, hourLabel, hourField, minutesLabel, minutesField, duration, durationField, createButton, cancel);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
		return newTask;
	}
	
}