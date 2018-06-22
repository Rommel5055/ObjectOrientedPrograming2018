package assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalTime;



import javafx.application.Platform;

public class TaskChecker {
	private List<Task> listObjectTasks;
	private List<Task> listObjectTasksInProgress;
	private List<Task> listObjectTasksFinished;
	
	// and more
	private LocalTime now;
	private LocalDate today;
	
	
	public TaskChecker(){
		this.listObjectTasks = new ArrayList<Task>();
		this.listObjectTasksInProgress = new ArrayList<Task>();
		this.listObjectTasksFinished = new ArrayList<Task>();
		this.now = LocalTime.now();
		this.today = LocalDate.now();
	}
	
	public void updateTime(){
		now = LocalTime.now();
		today = LocalDate.now();
	}

	/*
	public void startTask(int day, int hour, User user){
		for (int i = 0; i < this.listObjectTasks.size(); i++){
			Task task = (Task) listObjectTasks.get(i);
			if (task.getSDay() == day && task.getStart() == hour && task.getStatus() == "Scheduled"){
				task.setStatus(1);
				if (user.currentStatus()){
					user.changeStatus();
				}
				user.changeActivity(task.getName());
				this.listObjectTasksInProgress.add(task);
				this.listObjectTasks.remove(i); //move to in progress list
				System.out.println("You have " + task.getName() + " now. Your status has changed to busy.");
			}
		}
	}
	
	public boolean endTask(int day, int hour, User user){
		for (int i = 0; i < this.listObjectTasksInProgress.size(); i++){// should be just one, for just in case
			Task task = (Task) listObjectTasksInProgress.get(i);
			if (task.getEDay() == day && task.getEnd() == hour && task.getStatus() == "InProgress"){
				task.setStatus(2);
				user.changeStatus();
				user.changeActivity("");
				this.listObjectTasksFinished.add(task);
				this.listObjectTasksInProgress.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean checkActivity(int day, int hour){
		for (int i = 0; i < this.listObjectTasks.size(); i++){
			Task task = (Task) listObjectTasks.get(i);
				if (task.getSDay() == day && task.getStart() == hour){
					System.out.println("You have to " + task.getName() + " now!\nYour Status is now busy");
					task.setStatus(1);
					return true;
				}
			}
		return false;
	}*/
	
	public void addTask(Task task){
		this.listObjectTasks.add(task);
	}
	
	public void updateLists(){
		int FutureTasks = listObjectTasks.size();
		int CurrentTask = listObjectTasksInProgress.size();
		
		for (int i = 0; i < FutureTasks; i++){
			if (listObjectTasks.get(i).getStatus() == "InProgress"){
				Task task = listObjectTasks.get(i);
				listObjectTasks.remove(i);
				listObjectTasksInProgress.add(task);
				
				if(listObjectTasks.size() > 0){
					i = i - 1;
					FutureTasks = FutureTasks - 1;
				}
				else{
					break;
				}
			}
		}
		
		for(int j = 0; j < CurrentTask; j++){
			if (listObjectTasksInProgress.get(j).getStatus() == "Ready"){
				Task task = listObjectTasksInProgress.get(j);
				listObjectTasksInProgress.remove(j);
				listObjectTasksFinished.add(task);
				
				if (listObjectTasksInProgress.size() > 0){
					j = j-1;
					CurrentTask = CurrentTask - 1;
				}
				else{
					break;
				}
			}
		}
	}

	public String checkReminders(){
		updateTime();
		int size = listObjectTasks.size();
		String s = "";
		if (size > 0){
			for (int i = 0; i < size; i++){
				s = listObjectTasks.get(i).taskReminder();
				if (s != ""){
					break;
				}
			}
		}
		return s;
	}
	
	public boolean startTask(User mySelf){
		updateTime();
		int size = listObjectTasks.size();
		if (size > 0){
			for (int i = 0; i < size; i ++){
				if (listObjectTasks.get(i).start(mySelf)){
					updateLists();
					return true;
				}
			}
		}
		return false;
	}
	
	public void endTask(User mySelf){
		listObjectTasksInProgress.get(0).setStatus(2);
		updateLists();
		mySelf.changeActivity("");
		mySelf.changeStatus();
	}
	
	public boolean checkEnd(User mySelf){
		if (listObjectTasksInProgress.size() > 0){
			if (listObjectTasksInProgress.get(0).checkEnd()){
				endTask(mySelf);
				return true;
			}
		}
		return false;
	}
	
	public String activityName(){
		if (listObjectTasksInProgress.size() > 0){
			return listObjectTasksInProgress.get(0).getName();
		}
		
		return "";
	}
	
	public List<Task> getTaskToDo(){
		return listObjectTasks;
	}
	
	public void updateTasksToDo(List<Task> newTasks){
		listObjectTasks = new ArrayList<Task>();
		for (int i = 0; i < newTasks.size(); i++){
			listObjectTasks.add(newTasks.get(i));
		}
	}
	
	
	
	
	
	
}
