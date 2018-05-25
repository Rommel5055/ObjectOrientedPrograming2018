package assistant;

import java.util.Scanner;

public class User {
	private boolean available = true;
	private boolean inActivity = false;
	private String workingAt = ""; // you are currently working at: 
	
	public boolean changeStatus(){
		if (available == true){
			available = false;	
			return false;
		}
		available = true;
		return true;
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
	
	
	public boolean checkStatus(){
		if (available == true){
			return true;
			}
			/***************************************************/
		return false;		
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
