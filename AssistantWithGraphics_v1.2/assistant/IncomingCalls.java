package assistant;

public class IncomingCalls{
	private String callersName;
	private int callersNumber;

	public IncomingCalls(int number, String name){
		callersNumber = number;
		callersName = name;
	}
	public int getNumber(){
		return callersNumber;
	}
	public String getName(){
		return callersName;
	}
	
}
