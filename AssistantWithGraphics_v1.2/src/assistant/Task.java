package assistant;

public class Task {
	private String status; //"Scheduled", "InProgress", "Ready"
	private String name;
	private int startDay;
	private int start;
	private int end;
	private int endDay;
	
	public Task(String taskname, int starts, int ends, int SDay, int EDay){
		this.status = "Scheduled";
		this.name = taskname;
		this.startDay = SDay;
		this.endDay = EDay;
		this.start = starts;//hour
		this.end = ends;//hour
	}
	
	public int getSDay(){
		return this.startDay;
	}
	public int getEDay(){
		return this.endDay;
	}
	public int getStart(){
		return this.start;
	}
	public int getEnd(){
		return this.end;
	}
	public String getName(){
		return this.name;
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
		else if (n == 3){
			this.status = "Ready";
		}
	}

}
