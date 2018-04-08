package assistant;

import java.util.ArrayList;
import java.util.List;

public class IncomingCalls{
	public String callersName;
	public int callersNumber;

	public IncomingCalls(int number, String name){
		callersNumber = number;
		callersName = name;
	}
		
	public List<Object> creationOfCalls(){
		List<Object> calls = new ArrayList<Object>();
		calls.add(new IncomingCalls(562991274, "Polola"));
		calls.add(new IncomingCalls(569966463, "Numero Desconocido"));
		calls.add(new IncomingCalls(569323124, "Publicidad"));
		calls.add(new IncomingCalls(568299346, "Grupo de Trabajo"));
		calls.add(new IncomingCalls(564578754, "Amigo 00"));
		calls.add(new IncomingCalls(563284732, "Amigo 01"));
		calls.add(new IncomingCalls(564565436, "Amigo 02"));
		calls.add(new IncomingCalls(564564573, "Amigo 03"));
		calls.add(new IncomingCalls(563678336, "Amigo 04"));
		calls.add(new IncomingCalls(562452379, "Amigo 05"));
		calls.add(new IncomingCalls(563646547, "Amigo 06"));
		calls.add(new IncomingCalls(564533456, "Amigo 07"));
		calls.add(new IncomingCalls(565677455, "Amigo 08"));
		calls.add(new IncomingCalls(563453466, "Amigo 09"));
		calls.add(new IncomingCalls(563335676, "Amigo 10"));
		calls.add(new IncomingCalls(564467856, "Amigo 11"));
		calls.add(new IncomingCalls(564647568, "Amigo 12"));
		return calls;
	}
}
