package assistant;

import java.util.Scanner;

public class User {
	private boolean available = true;
	
	public void changeStatus(){
		if (available == true){
			available = false;			
		}
		else{
			available = true;
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
	
	
	
	

}
