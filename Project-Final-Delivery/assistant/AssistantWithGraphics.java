package assistant;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AssistantWithGraphics extends Application {
    private String CurStatus = "You are currently avaliable";
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	private LocalTime now;
	private LocalDate today;
	private TableView<IncomingNews> table = new TableView();
	private TableView<IncomingCalls> callsTable = new TableView();
	private ObservableList<IncomingNews> newsLists = FXCollections.observableArrayList();
	private ObservableList<IncomingCalls> callLists = FXCollections.observableArrayList();
	private IncomingNews news = null;
	private IncomingCalls call = null;
    
	
    public static void main(String[] args) {
        launch(args);
    }
    
	public LocalTime getTime(){
		return now;
	}
	
	public LocalDate getToday(){
		return today;
	}
	
	public IncomingNews getNews(){
		return news;
	}
	
	public IncomingCalls getCall(){
		return call;
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
	
	public static String getMonth(int n){
		String months[] = new String[13];
		months[0] = null;
		months[1] = "January";
		months[2] = "February";
		months[3] = "March";
		months[4] = "April";
		months[5] = "May";
		months[6] = "June";
		months[7] = "July";
		months[8] = "August";
		months[9] = "September";
		months[10] = "October";
		months[11] = "November";
		months[12] = "December";
		return months[n];
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
	
	public String th(){
		int day = getToday().getDayOfMonth();
		String th = "th";
		if (day == 11 || day == 12 || day == 13){
			th = "th";
		}else if (day == 1 || day % 10 == 1){
			th = "st";
		}else if (day == 2 || day % 10 == 2){
			th = "nd";
		}else if (day == 3 || day % 3 == 3){
			th = "rd";
		}
		return th;
	}
	
	public void stop() {
        executorService.shutdownNow();
    }
	
	private Runnable tick(Text Today, ReturnList retList, User mySelf, Random rand, Sound sound, TaskChecker taskChecker, Text NewTask, Text curStatus) {
		Runnable runnableObject = new Runnable(){
        		@Override
        		public void run() {
        			now = LocalTime.now();
                	today = LocalDate.now();
                	String th = th();
                	String timemessage = "Currently it's " + getTime() + " of the " + getToday().getDayOfMonth() + th + " of " + getMonth(getToday().getMonthValue()) + ", " + getToday().getYear();
                	String reminder = taskChecker.checkReminders();
                	boolean startActivity = taskChecker.startTask(mySelf);
                	boolean endActivity = taskChecker.checkEnd(mySelf);
                	if (!startActivity){
                		if(endActivity){
                			taskChecker.endTask(mySelf);
                        	CurStatus = "You are currently avaliable";
                		}
                	}
                	
                	String activityName = taskChecker.activityName();
                	if(startActivity){
                    	CurStatus = "You are now working on " + activityName;
                	}
        			/**********************************************************/
        			
        			/*Generates a random number from 0 to 2. 
    				 * 0 is "there are new news"
    				 * 1 is "there are new calls"
    				 * 2 is "nothing happened in this iteration" 
    				 * Check what random number we got and act accordingly*/
    				
        			if (now.getSecond() % 10 == 0){//So it gets the news or a call every 10 seconds
        			
	        			int randomN = rand.nextInt(3);// 0 -> News; 1 -> Calls; 2 -> Nothing
	        			System.out.println(randomN);
	    				if (randomN == 0){//News
	    					/*There are new news
	    					 *Show new news
	    					 *If user is busy, then add it to missed News and update that list instead*/
	    					news = retList.ifNews(rand, mySelf, sound);
	    					/***************************************************************************/
	    				}
	    				else if (randomN == 1){//Calls
	    					/*There are new calls
	    					 *If the user is busy, add the call to missed calls and update the list*/
	    					call = retList.ifCall(rand, mySelf, sound);
	    					/***********************************************************************/
	    				}
	    				else{
	    					if (mySelf.currentStatus()){
	    						System.out.println("Nothing happened in this itteration.");
	    					}
	    				}
        			}
        			
                	Platform.runLater(new Runnable(){
						@Override
						public void run() {
							ArrayList<IncomingNews> missedNews = retList.checkMissedNews(mySelf);
							ArrayList<IncomingCalls> missedCalls = retList.checkMissedCalls(mySelf);

							if (missedNews != null){
	                    		for (int i = 0; i < missedNews.size(); i++){
	                    			newsLists.add(missedNews.get(i));
	                    		}
	                    	}
							
							if (missedCalls != null){
	                    		for (int i = 0; i < missedCalls.size(); i++){
	                    			callLists.add(missedCalls.get(i));
	                    		}
	                    	}
							
							if (getNews() != null){
	    						newsLists.add(news);
	    						news = null;
	    					}
							
							if (getCall() != null){
								callLists.add(call);
								call = null;
							}
							
		                	if(!startActivity){
								Today.setText(timemessage);
			    	            NewTask.setText(reminder);
			                	System.out.println(Today.getText());	
		                	}
		                	else{
	                        	curStatus.setText(CurStatus);
		                	}
	                		curStatus.setText(CurStatus);
						}
                	});
        		}
        	};
     	return runnableObject;
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
		ReturnList retList = new ReturnList();
		Sound sound = new Sound();

		/*Creates Lists of both calls and news for the assistant */
		retList.CreateCalls();
		retList.CreateNews();
		TaskChecker taskChecker = new TaskChecker();
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
        
        
        //----------------Botones--------------------------
        Button status = new Button("Change Status");
        HBox hstatus = new HBox(100);
        hstatus.setAlignment(Pos.BOTTOM_LEFT);
        hstatus.getChildren().add(status);
        grid.add(hstatus, 1, 5);
        
        Button taskCreation = new Button("Create Task");
        HBox htask = new HBox(100);
        htask.setAlignment(Pos.BOTTOM_RIGHT);
        htask.getChildren().add(taskCreation);
        grid.add(htask, 3, 5);

        Text NextTask = new Text("You have task in 1 hour");
        NextTask.setFill(Color.RED);
        grid.add(NextTask, 5, 5);
        
        Button toDo = new Button("To Do List");
        HBox htoDo = new HBox(100);
        htoDo.setAlignment(Pos.BOTTOM_RIGHT);
        htoDo.getChildren().add(toDo);
        grid.add(htoDo, 4, 5);
        
        Button contacts = new Button("Contact List");
        HBox hContacts = new HBox(100);
        hContacts.setAlignment(Pos.BOTTOM_CENTER);
        hContacts.getChildren().add(contacts);
        grid.add(hContacts, 3, 8);
        
        
        Label NewsLabel = new Label("News:");
        grid.add(NewsLabel, 1, 6);
        
        table.setEditable(true);
        table.setMinWidth(450);
        callsTable.setEditable(true);
        
        TableColumn<IncomingNews, String> statusColumn = new TableColumn<IncomingNews, String>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<IncomingNews, String>("status"));
        
        TableColumn<IncomingNews, String> newsColumn = new TableColumn<IncomingNews, String>("News");
        newsColumn.setCellValueFactory(new PropertyValueFactory<IncomingNews, String>("title"));
        newsColumn.setMinWidth(400);
        
        TableColumn<IncomingCalls, String> callStatusColumn = new TableColumn<IncomingCalls, String>("Status");
        callStatusColumn.setCellValueFactory(new PropertyValueFactory<IncomingCalls, String>("status"));
        
        TableColumn<IncomingCalls, String> callersNameColumn = new TableColumn<IncomingCalls, String>("Callers Name");
        callersNameColumn.setCellValueFactory(new PropertyValueFactory<IncomingCalls, String>("callersName"));
        
        TableColumn<IncomingCalls, Integer> callersNumberColumn = new TableColumn<IncomingCalls, Integer>("Callers Number");
        callersNumberColumn.setCellValueFactory(new PropertyValueFactory<IncomingCalls, Integer>("callersNumber"));        
        
        TableColumn<IncomingCalls, LocalTime> callTimeColumn = new TableColumn<IncomingCalls, LocalTime>("Call Time");
        callTimeColumn .setCellValueFactory(new PropertyValueFactory<IncomingCalls, LocalTime>("time"));
        
        table.setItems(newsLists);
        table.getColumns().addAll(statusColumn, newsColumn);
        grid.add(table, 1, 7);
        
        callsTable.setItems(callLists);
        callsTable.getColumns().addAll(callTimeColumn, callersNameColumn, callersNumberColumn, callStatusColumn);

        table.getStyleClass().add("newsTable");
        
        Label CallsLabel = new Label("Calls:");
        grid.add(CallsLabel, 3, 6);
        
        grid.add(callsTable, 3, 7);
        
        Scene scene = new Scene(grid, 1100, 680);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        executorService.scheduleAtFixedRate(tick(Today, retList, mySelf, rand, sound, taskChecker, NextTask, curStatus), 0, 1, TimeUnit.SECONDS);
        
        status.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent e) {
                if(!mySelf.checkActivity()){
                	boolean change = mySelf.changeStatus();
                    if (change){
                    	CurStatus = "You are currently avaliable";
                    	table.setMaxHeight(450);
                    	callsTable.setMaxHeight(450);
                		
                    	ArrayList<IncomingNews> missedNews = retList.checkMissedNews(mySelf);
                    	if (missedNews != null){
                    		for (int i = 0; i < missedNews.size(); i++){
                    			newsLists.add(missedNews.get(i));
                    		}
                    	}
                    	
                    	ArrayList<IncomingCalls> missedCalls= retList.checkMissedCalls(mySelf);
                    	if (missedCalls != null){
                    		for (int i = 0; i < missedCalls.size(); i++){
                    			callLists.add(missedCalls.get(i));
                    		}
                    	}
                    }
                    else{
                    	CurStatus = "You are now busy";
                    	table.setMaxHeight(0);
                    	callsTable.setMaxHeight(0);
                    }
                }
                else{
                	boolean response = ConfirmationWindowEndTask.display();
                	System.out.println(response);
                	if (response){
                		taskChecker.endTask(mySelf);
                    	CurStatus = "You are currently avaliable";
                    	table.setMaxHeight(450);
                    	callsTable.setMaxHeight(450);
                    	ArrayList<IncomingNews> newsList = retList.checkMissedNews(mySelf);
                    	if (newsList != null){
                    		for (int i = 0; i < newsList.size(); i++){
                    			newsLists.add(newsList.get(i));
                    		}
                    	}
                    	ArrayList<IncomingCalls> missedCalls= retList.checkMissedCalls(mySelf);
                    	if (missedCalls != null){
                    		for (int i = 0; i < missedCalls.size(); i++){
                    			callLists.add(missedCalls.get(i));
                    		}
                    	}
                	}
                }
            	curStatus.setText(CurStatus);
            }
        });
        
        taskCreation.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e){
        		if(mySelf.currentStatus()){
	        		Task newTask = TaskWindow.display();
	        		if (newTask != null){
	        			taskChecker.addTask(newTask);
	        		}
	        	}
        	}
        });
        
        toDo.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e){
        		if(mySelf.currentStatus()){
	        		ToDoView.create(taskChecker);
	        		ToDoView.display();
        		}
	        }
        });
        
        contacts.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e){
        		if(mySelf.currentStatus()){
        			Contacts.display(retList.retlistObjectCalls());
        		}
        	}
        });
    }
}