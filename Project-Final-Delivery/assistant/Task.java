package assistant;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
	private String status; //"Scheduled", "InProgress", "Ready"
	private String name;
	private LocalTime localStartTime;
	private LocalTime localEndTime;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Task(String taskname, LocalTime start, LocalTime end, LocalDate startDate, LocalDate endDate){
		this.status = "Scheduled";
		this.name = taskname;
		localStartTime = start;
		localEndTime = end;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public LocalTime getLocalStartTime(){
		return this.localStartTime;
	}
	
	public LocalTime getEnd(){
		return this.localEndTime;
	}

	public String getName(){
		return this.name;
	}
	
	public LocalDate getStartDate(){
		return this.startDate;
	}
	
	public LocalDate getEndDate(){
		return this.endDate;
	}
	
	public String getStatus(){
		return this.status;  
	}
	
	public void setStatus(int n){
		if (n == 0){
			this.status = "Scheduled";
		}
		else if (n == 1){
			this.status = "InProgress";
		}
		else if (n == 2){
			this.status = "Ready";
		}
	}
	
	public void checkFutureTask(){
		if(getStatus() == "Scheduled"){
			setStatus(1);	
		}
	}
	
	public void checkActiveTask(){
		if (getStatus() == "InProgress"){
			setStatus(2);
		}
	}
	
	public String taskReminder(){
		if (getStatus() == "Scheduled"){
			if (getStartDate().equals(LocalDate.now()) && LocalTime.now().isAfter(getLocalStartTime().minusMinutes(5))){
				int start = getLocalStartTime().getMinute();
				int now = LocalTime.now().getMinute();
				System.out.println(start + " " + now);
				int n = start - now;
				if(n < 0){
					n = 60 + now - start;
				}
				String s = "You have " + getName() + " starting in " + n + " minutes.";
				System.out.println(s);
				return s;
			}
		}
		return "";
	}
	
	public void print(){
		System.out.println(getName() + " starts on "  + getStartDate() + " at " + getLocalStartTime() + " and ends on " + getEndDate() + " at " + getEnd());
	}

	public boolean start(User mySelf){
		if (getStatus() == "Scheduled"){
			if (getStartDate().equals(LocalDate.now()) && LocalTime.now().isAfter(getLocalStartTime())){
				if (mySelf.currentStatus()){
					mySelf.changeStatus();
					mySelf.changeActivity(getName());
					setStatus(1);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkEnd(){
		if (getStatus() == "InProgress"){
			if (getEndDate().equals(LocalDate.now()) && LocalTime.now().isAfter(getEnd())){
				return true;
			}
		}
		return false;
	}
}
