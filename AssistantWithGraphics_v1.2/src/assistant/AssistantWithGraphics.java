package assistant;

import java.util.Random;
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
	int hour = 0;
	int day = 1;
    String newsFeed = "";
    String callsFeed = "";
    String newsFeedAux = "";
    String callsFeedAux = "";
    String CurStatus = "You are currently avaliable";
	
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
	
	public void set_hour(){
		hour++;
		if (hour > 23){
			hour = 0;
			day += 1;
		}
	}
	
	public String th(){
		String th = "th";
		if (day == 11 || day == 12 || day == 13){
			th = "th";
		}else if (day == 1 || day % 10 == 1){// Program won't run for over a hundred days... right?
			th = "st";
		}else if (day == 2 || day % 10 == 2){
			th = "nd";
		}else if (day == 3 || day % 3 == 3){
			th = "rd";
		}
		return th;
	}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assistant with Graphics - Welcome");
        GridPane grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Random rand = new Random();
		User mySelf = new User();
		Scanner reader = new Scanner(System.in);
		ReturnList retList = new ReturnList();
		Sound sound = new Sound();

		/*Creates Lists of both calls and news for the assistant */
		retList.CreateCalls();
		retList.CreateNews();
		/*********************************************************/
		
		
        
        
        
        
        
        
        
        
        
        
        
        Text scenetitle = new Text("This is your assistant");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Text curStatus = new Text(CurStatus);
        grid.add(curStatus, 4, 0);
        curStatus.setFill(Color.BLUE);
        
        Text Today = new Text("Currently it's 0:00 of the 1st day");
        grid.add(Today, 3, 0);
        Today.setFill(Color.GREEN);
        
        
        Label LabelStartDay = new Label("Start Day:");
        grid.add(LabelStartDay, 0, 1);

        TextField userTextFieldStartDay = new TextField();
        grid.add(userTextFieldStartDay, 1, 1);
        
        Text dayErrorS = new Text("lele");
        dayErrorS.setFill(Color.FIREBRICK);
        grid.add(dayErrorS, 1, 2);
        
        Label LabelStartHour = new Label("Start Hour:");
        grid.add(LabelStartHour, 2, 1);

        TextField userTextFieldStartHour = new TextField();
        grid.add(userTextFieldStartHour, 3, 1);

        Text hourErrorS = new Text("lala");
        hourErrorS.setFill(Color.FIREBRICK);
        grid.add(hourErrorS, 3, 2);
        
        Label LabelEndDay = new Label("End Day:");
        grid.add(LabelEndDay, 0, 3);

        TextField userTextFieldEndDay = new TextField();
        grid.add(userTextFieldEndDay, 1, 3);
        
        Text dayErrorH = new Text("lele");
        dayErrorH.setFill(Color.FIREBRICK);
        grid.add(dayErrorH, 1, 4);
        
        Label LabelEndHour = new Label("End Hour:");
        grid.add(LabelEndHour, 2, 3);

        TextField userTextFieldEndHour = new TextField();
        grid.add(userTextFieldEndHour, 3, 3);
                
        Text hourErrorE = new Text("lala");
        hourErrorE.setFill(Color.FIREBRICK);
        grid.add(hourErrorE, 3, 4);

        Label LabelTaskName = new Label("Task Name:");
        grid.add(LabelTaskName, 4, 2);

        TextField userTextFieldTask = new TextField();
        grid.add(userTextFieldTask, 5, 2);
        
        Text nameError = new Text("lolo");
        nameError.setFill(Color.FIREBRICK);
        grid.add(nameError, 5, 3);
        
        
        //----------------Botones--------------------------
        Button status = new Button("Change Status");
        HBox hstatus = new HBox(100);
        hstatus.setAlignment(Pos.BOTTOM_RIGHT);
        hstatus.getChildren().add(status);
        grid.add(hstatus, 2, 5);
        
        Button taskCreation = new Button("Create Task");
        HBox htask = new HBox(100);
        htask.setAlignment(Pos.BOTTOM_RIGHT);
        htask.getChildren().add(taskCreation);
        grid.add(htask, 3, 5);

        Text NextTask = new Text("You have task in 1 hour");
        NextTask.setFill(Color.RED);
        grid.add(NextTask, 5, 5);
        
        
        Label NewsLabel = new Label("News:");
        grid.add(NewsLabel, 1, 6);
        
        Label NewsFeed = new Label(newsFeed);
        grid.add(NewsFeed, 1, 7);

        Label CallsLabel = new Label("Calls:");
        grid.add(CallsLabel, 3, 6);
        
        Text CallsFeed = new Text();
        CallsFeed.setText(callsFeed);
        grid.add(CallsFeed, 3, 7);
        
        callsFeed = CallsFeed.getText();
        
        Button btn = new Button("Next Itteration");
        HBox hbBtn = new HBox(100);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 8);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 9);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	set_hour();
            	String th = th();
            	String timemessage = "Currently it's " + hour + ":00 of the " + day + th + " day";
            	Today.setText(timemessage);
            	
            	
        		callsFeed = retList.checkMissedCalls(mySelf, callsFeed);
        		newsFeed = retList.checkMissedNews(mySelf, newsFeed);
				/**********************************************************/
			
			
				/*Generates a random number from 0 to 2. 
				 * 0 is there are new news
				 * 1 is there are new calls
				 * 2 is nothing happened in this iteration 
				 * 
				 * Check what random number we got and act accordingly*/
				int randomN = rand.nextInt(3);// 0 -> News; 1 -> Calls; 2 -> Nothing
				if (randomN == 0){//News
					/*There are new news
					 *Show new news
					 *If user is busy, then add it to missed News and update that list instead*/
					newsFeed = retList.ifNews(rand, mySelf, sound, newsFeed);
				
					/***************************************************************************/
				}
				else if (randomN == 1){//Calls
					/*There are new calls
					 *If the user is busy, add the call to missed calls and update the list*/
					callsFeed = retList.ifCall(rand, mySelf, sound, callsFeed);
					/***********************************************************************/
				}
				else{
					if (mySelf.currentStatus()){
					//	System.out.println("Nothing happened in this itteration.");
            	
					}
				}
            NewsFeed.setText(newsFeed);
            CallsFeed.setText(callsFeed);
            
            
            
            
            
            }	
        });
        
        taskCreation.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                hourErrorE.setFill(Color.FIREBRICK);
                hourErrorE.setText("Sign in button pressed");
            }
        });
        
        status.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                boolean change = mySelf.changeStatus();
                if (change){
                	newsFeed = newsFeedAux;
                	newsFeedAux = "";
                	callsFeed = callsFeedAux;
                	callsFeedAux = "";
                	CurStatus = "You are currently avaliable";
                	callsFeed = retList.checkMissedCalls(mySelf, callsFeed);
            		newsFeed = retList.checkMissedNews(mySelf, newsFeed);
    				
                	curStatus.setText(CurStatus);
                	NewsFeed.setText(newsFeed);
                	CallsFeed.setText(callsFeed);
                }
                else{
                	newsFeedAux = newsFeed;
                	newsFeed = "";
                	callsFeedAux = callsFeed;
                	callsFeed = "";
                	CurStatus = "You are now busy";
                	curStatus.setText(CurStatus);
                	NewsFeed.setText(newsFeed);
                	CallsFeed.setText(callsFeed);
                }
            }
        });
        
        Scene scene = new Scene(grid, 1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}