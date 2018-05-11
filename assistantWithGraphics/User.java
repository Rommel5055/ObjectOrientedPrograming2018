package assistantWithGraphics;

public class User {
	public boolean available = true;
	public String status = "Available";
	
	public void changeStatus(){
		/*Change user status from available to busy*/
		if (available == true){
			available = false;
			status = "Busy";
		}
		/***************************************************/
		
		
		else{
			/*Change user status from busy to available*/
			available = true;
			status = "Available";
		}
		/***************************************************/
	}
}