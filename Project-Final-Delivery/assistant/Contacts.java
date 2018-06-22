package assistant;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Contacts {
	
	public static void display(List<IncomingCalls> Calls){
		ObservableList<IncomingCalls> callsLists = FXCollections.observableArrayList();
		for (int j = 0; j < Calls.size(); j++){
			callsLists.add(Calls.get(j));
		}
		TableView<IncomingCalls> table = new TableView();
		
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Contact List");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Contact List");
        
        table.setEditable(true);
		table.setMinWidth(450);
		
		TableColumn<IncomingCalls, String> nameColumn = new TableColumn<IncomingCalls, String>("Name");
	    nameColumn.setCellValueFactory(new PropertyValueFactory<IncomingCalls, String>("callersName"));
	    nameColumn.setMinWidth(200);
	    
	    TableColumn<IncomingCalls, String> numberColumn = new TableColumn<IncomingCalls, String>("Number");
	    numberColumn.setCellValueFactory(new PropertyValueFactory<IncomingCalls, String>("callersNumber"));
	    numberColumn.setMinWidth(200);
	    
	    table.setItems(callsLists);
		table.getColumns().addAll(nameColumn, numberColumn);
		
		VBox layout = new VBox(10);

        layout.getChildren().addAll(label, table);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
		
	}
}
