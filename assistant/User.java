package assistant;

import java.util.Scanner;

public class User {
	private boolean available = true;
	private boolean inActivity = false;
	private String workingAt = ""; // you are currently working at: 
	
	public void changeStatus(){
		if (available == true){
			available = false;			
		}
		else{
			available = true;
		}
	}
	
	public void changeActivity(String activity){
		if (this.inActivity == true){
			this.inActivity = false;
		}
		else{
			this.inActivity = true;
		}
		if(this.workingAt == ""){
			this.workingAt = activity;
		}
		else{
			this.workingAt = "";
		}
	}
	
	
	public void checkStatus(Scanner reader){
		if (available == true){
			/*Option to change user status from available to busy*/
			System.out.printf("Your status is available. Would you like to change it? (1 = Yes / 0 = No) \n");
			int userAnswer = reader.nextInt();
			if (userAnswer == 1){
				changeStatus();
				System.out.printf("Your new status is busy. \n");
			}
			/***************************************************/
		}
		else{
			/*Option to change user status from busy to available*/
			System.out.printf("Are you still busy? (1 = Yes / 0 = No) \n");
			int userAnswer = reader.nextInt();
			if (userAnswer == 0){
				changeStatus();
				System.out.printf("You are now available.\n");
			}
			/****************************************************/
		}
		
		
	}
	public boolean currentStatus(){
		return available;
	}
	public boolean checkActivity(){
		return inActivity;
	}
	public String getActivity(){
		return this.workingAt;
	}
	
	
	

}
