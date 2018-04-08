package assistant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Assistant {

	public static void main(String[] args) {
		int numberOfCicles = Integer.parseInt(args[0]);
		
		Random rand = new Random();
		User mySelf = new User();
		Scanner reader = new Scanner(System.in);
		
		IncomingNews newNewsCreator = new IncomingNews();
		IncomingCalls newCallsCreator = new IncomingCalls(00, "Default"); 
		//Is there another way?
		List<String> news = newNewsCreator.createNews();
		List<Object> calls = newCallsCreator.creationOfCalls();
		
		List<Object> missedCalls = new ArrayList<Object>();
		List<Object> missedNews = new ArrayList<Object>();
		
		for(int i = 0; i < numberOfCicles; i++){
			
			if ((missedCalls.size() > 0 || missedNews.size() > 0) && mySelf.available == true){
				for (int j = 0; j < missedCalls.size(); j++){
					IncomingCalls call = (IncomingCalls) missedCalls.get(j);
					String callersName = call.callersName;
					int callersNumber = call.callersNumber;
					System.out.printf("Missed call from: %s (%d) \n", callersName, callersNumber);
				}
				for (int k = 0; k < missedNews.size(); k++){
					IncomingNews missedNew = (IncomingNews) missedNews.get(k);
					String title = missedNew.title;
					System.out.printf("Missed News: %s \n", title);
				}
			}
			
			
			if (mySelf.available == true){
				System.out.printf("Your status is available. Would you like to change it? (1 = Yes / 0 = No) \n");
				int userAnswer = reader.nextInt();
				if (userAnswer == 1){
					mySelf.changeStatus();
					System.out.printf("Your new status is busy. \n");
				}
			}
			else{
				System.out.printf("Are you still busy? (1 = Yes / 0 = No) \n");
				int userAnswer = reader.nextInt();
				if (userAnswer == 0){
					mySelf.changeStatus();
					System.out.printf("You are now available.\n");
				}
			}
			
			int randomN = rand.nextInt(3);// 0 -> News; 1 -> Calls; 2 -> Nothing
			if (randomN == 0){
				if (news.size() > 0){
					int randomNews = rand.nextInt(news.size());
					IncomingNews newNew = new IncomingNews();
					newNew.newNews(news.get(randomNews), false);
					news.remove(randomNews); // Avoid getting repeated news
					
					if (mySelf.available == true){
						System.out.printf("Breaking News!\n");
						System.out.printf("%s \n", newNew.title);
					}
					else{
						missedNews.add(newNew);
					}
					
				}
			}
			if (randomN == 1){
				int randomCalls = rand.nextInt(calls.size());
				IncomingCalls newCall = (IncomingCalls) calls.get(randomCalls);
				
				if (mySelf.available == true){
					System.out.printf("%s (%d) is calling. \n", (String)newCall.callersName, (int)newCall.callersNumber);
				} 
				else{
					missedCalls.add(newCall);
				}
			}
			
		}
		reader.close();
	}
}