package assistant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToDoView {
	private static TaskChecker tasks;
	private static ObservableList<Task> taskLists = FXCollections.observableArrayList();
	private static TableView<Task> table;
	
	public static void create(TaskChecker task){
		ToDoView.tasks = task;
		List<Task> getTask = tasks.getTaskToDo();
		for (int j = 0; j < getTask.size(); j++){
			taskLists.add(getTask.get(j));
		}
		table = new TableView();
	}
	
	public static TaskChecker display(){
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("To Do List");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("To Do Table");
		
		table.setEditable(true);
		table.setMinWidth(450);
		TableColumn<Task, String> nameColumn = new TableColumn<Task, String>("Name");
	    nameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
	    nameColumn.setMinWidth(200); 
	    
		TableColumn<Task, LocalDate> startDayColumn = new TableColumn<Task, LocalDate>("Start Day");
		startDayColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("startDate"));
		 
		TableColumn<Task, LocalTime> startTimeColumn = new TableColumn<Task, LocalTime>("Start Time");
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<Task, LocalTime>("localStartTime"));
		
		table.setItems(taskLists);
		table.getColumns().addAll(nameColumn, startDayColumn, startTimeColumn);
		
        Button deleteButton = new Button("Delete Task");
        
        deleteButton.setOnAction(e -> {
        	Task deleteTask = table.getSelectionModel().getSelectedItem();
        	List<Task> newList = new ArrayList<Task>();
        	for (int k = 0; k < tasks.getTaskToDo().size(); k++){
        		if ((tasks.getTaskToDo().get(k).getName() != deleteTask.getName()) && tasks.getTaskToDo().get(k).getLocalStartTime() != deleteTask.getLocalStartTime()){
        			newList.add(tasks.getTaskToDo().get(k));
        		}
        	}
        	tasks.updateTasksToDo(newList);
        	taskLists.clear();
        	for (int j = 0; j < newList.size(); j++){
    			taskLists.add(newList.get(j));
    		}
        });
		
		VBox layout = new VBox(10);

        layout.getChildren().addAll(label, table, deleteButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
		return tasks;
	}

}
