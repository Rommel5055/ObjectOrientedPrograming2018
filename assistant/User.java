package assistant;

public class User {
	public boolean available = true;
	
	public void changeStatus(){
		if (available == true){
			available = false;			
		}
		else{
			available = true;
		}
	}
	

}
