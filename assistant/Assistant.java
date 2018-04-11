package assistant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Assistant {

	public static User changeStatus(Scanner reader, User mySelf){
		if (mySelf.available == true){
			/*Option to change user status from available to busy*/
			System.out.printf("Your status is available. Would you like to change it? (1 = Yes / 0 = No) \n");
			int userAnswer = reader.nextInt();
			if (userAnswer == 1){
				mySelf.changeStatus();
				System.out.printf("Your new status is busy. \n");
			}
			/***************************************************/
		}
		else{
			/*Option to change user status from busy to avaliable*/
			System.out.printf("Are you still busy? (1 = Yes / 0 = No) \n");
			int userAnswer = reader.nextInt();
			if (userAnswer == 0){
				mySelf.changeStatus();
				System.out.printf("You are now available.\n");
			}
			/****************************************************/
		}
		return mySelf;
	}
	
	public static void main(String[] args) {
		int numberOfCicles = Integer.parseInt(args[0]);
		
		Random rand = new Random();
		User mySelf = new User();
		Scanner reader = new Scanner(System.in);
		ReturnList retList = new ReturnList();
		
		/*Creates Lists of both calls and news for the assistant */
		retList = retList.CreateCalls(retList);
		retList = retList.CreateNews(retList);
		List<Object> calls = retList.listObjectCalls;
		List<String> news = retList.listStringNews;
		/*********************************************************/
		
		/*Declare lists which will be filled with missed calls and news, 
		 * when the use is "busy"*/
		List<Object> missedCalls = new ArrayList<Object>();
		List<Object> missedNews = new ArrayList<Object>();
		/**********************************************************/
		for(int i = 0; i < numberOfCicles; i++){
			/*Checks for missed calls and missed news. If the user is
			 * avaliable then the assistant will show all missed items
			 * and then delete them, so it doesn't get printed more 
			 * than once*/ 
			retList = retList.checkMissed(missedCalls, missedNews, mySelf, retList);
			missedCalls = retList.listObjectMissedCalls;
			missedNews = retList.listObjectMissedNews;
			/**********************************************************/
			
			/*Asks the user if he is still busy or avaliable and changes the status 
			 * of user according to the user input*/
			mySelf = changeStatus(reader, mySelf);
			/*********************************************************/
			
			/*Generates a random number from 0 to 2. 
			 * 0 is there are new news
			 * 1 is there are new calls
			 * 2 is nothing happened in this iteration 
			 * 
			 * Check what random number we got and act accordingly*/
			int randomN = rand.nextInt(3);// 0 -> News; 1 -> Calls; 2 -> Nothing
			if (randomN == 0){//News
				/*There are new news
				 *Show new news
				 *If user is busy, then add it to missed News and uopdate that list instead*/
				ReturnList.ifNews(news, rand, mySelf, missedNews, retList);
				news = retList.listStringNews;
				missedNews = retList.listObjectMissedNews;
				/***************************************************************************/
			}
			else if (randomN == 1){//Calls
				/*There are new calls
				 *If the user is busy, add the call to missed calls and update the list*/
				retList = ReturnList.ifCall(rand, calls, mySelf, missedCalls, retList);
				missedCalls = retList.listObjectMissedCalls;
				/***********************************************************************/
			}
		}
		/*Notify about last iteration so it doesn't end abruptly*/
		System.out.printf("Thank you for using this assistant\nThe end");
		reader.close();
		/********************************************************/
	}
}