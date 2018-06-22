package assistant;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationWindowEndTask {
	private static boolean response;
	
	public static boolean display(){ 
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Confirmation needed");
		window.setMinWidth(250);
		Label label = new Label();
		label.setText("Are you sure you want to end the current Task?");
		Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        
        yesButton.setOnAction(e -> {
        	response = true;
        	window.close();
        });
        
        noButton.setOnAction(e -> {
        	response = false;
        	window.close();
        });
        
        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return response;
	}
}
