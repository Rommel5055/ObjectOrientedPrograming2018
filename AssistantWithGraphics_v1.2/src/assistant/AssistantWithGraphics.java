package assistant;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AssistantWithGraphics extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    public static int beginTaskTime(Scanner reader){
		System.out.print("Starting time: ");
		int taskBegin = reader.nextInt();
		if (taskBegin < 0 || taskBegin > 23){
			System.out.println("Your time is not valid!\nPlease try again!");
			taskBegin = beginTaskTime(reader);
		}
		return taskBegin;
	}
	
	public static int endTaskTime(Scanner reader){
		System.out.print("End time: ");
		int taskEnd = reader.nextInt();
		if (taskEnd < 0 || taskEnd > 23){
			System.out.println("Your time is not valid!\nPlease try again!");
			taskEnd = endTaskTime(reader);
		}
		return taskEnd;
	}
	
	public static int startDay(Scanner reader, int hour, int sHour, int day){
		System.out.print("Start Day: ");
		int SDay = reader.nextInt();
		if (SDay == day && sHour <= hour){
			System.out.println("Please insert a valid day");
			SDay = startDay(reader, hour, sHour, day);
		}
		return SDay;
	}
	
	public static int endDay(Scanner reader, int SDay, int sHour, int eHour){
		System.out.print("End Day: ");
		int EDay = reader.nextInt();
		if (SDay > EDay || (SDay == EDay && sHour > eHour)){
			System.out.println("Please insert a valid date");
			EDay = endDay(reader, SDay, sHour, eHour);
		}
		return EDay;
	}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assistant with Graphics - Welcome");
        GridPane grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        
        
        
        
        
        
        
        
        
        
        String newsFeed = "lalalala\nlalalala";
        String callsFeed = "lalalala\nlalalala";
        
        Text scenetitle = new Text("This is your assistant");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label NewsLabel = new Label("News:");
        grid.add(NewsLabel, 0, 1);
        
        Label NewsFeed = new Label(newsFeed);
        grid.add(NewsFeed, 0, 2);

        Label CallsLabel = new Label("Calls:");
        grid.add(CallsLabel, 4, 1);
        
        Text CallsFeed = new Text();
        CallsFeed.setText(callsFeed);
        grid.add(CallsFeed, 4, 2);
        
        callsFeed = CallsFeed.getText();
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(100);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
                CallsFeed.setText(callsFeed + "\nlalalalal");
            }
        });

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}