package assistant;

import java.time.LocalTime;

public class IncomingCalls{
	private String callersName;
	private int callersNumber;
	private String callStatus;
	private LocalTime time;

	public IncomingCalls(int number, String name, int n){
		callersNumber = number;
		callersName = name;
		if (n == 0){
			callStatus = "Incoming Call";
		}
		else{
			callStatus = "Missed Call";
		}
		time = LocalTime.now();
	}
	public int getCallersNumber(){
		return callersNumber;
	}
	public String getCallersName(){
		return callersName;
	}
	public String getStatus(){
		return callStatus;
	}
	public void setStatus(int n){
		if (n == 0){
			callStatus = "Incoming Call";
		}
		else{
			callStatus = "Missed Call";
		}
	}
	public LocalTime getTime(){
		return time;
	}
}
