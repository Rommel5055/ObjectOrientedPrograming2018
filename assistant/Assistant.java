package assistant;
import java.util.Random;
import java.util.Scanner;

public class Assistant {
	
	public static int beginTaskTime(Scanner reader){
		System.out.print("Starting time: ");
		int taskBegin = reader.nextInt();
		if (taskBegin < 0 || taskBegin > 23){
			System.out.println("Your time is not valid!\nPlease try again!");
			taskBegin = beginTaskTime(reader);
		}
		return taskBegin;
	}
	
	public static int endTaskTime(Scanner reader){
		System.out.print("End time: ");
		int taskEnd = reader.nextInt();
		if (taskEnd < 0 || taskEnd > 23){
			System.out.println("Your time is not valid!\nPlease try again!");
			taskEnd = endTaskTime(reader);
		}
		return taskEnd;
	}
	
	public static int startDay(Scanner reader, int hour, int sHour, int day){
		System.out.print("Start Day: ");
		int SDay = reader.nextInt();
		if (SDay == day && sHour <= hour){
			System.out.println("Please insert a valid day");
			SDay = startDay(reader, hour, sHour, day);
		}
		return SDay;
	}
	
	public static int endDay(Scanner reader, int SDay, int sHour, int eHour){
		System.out.print("End Day: ");
		int EDay = reader.nextInt();
		if (SDay > EDay || (SDay == EDay && sHour > eHour)){
			System.out.println("Please insert a valid date");
			EDay = endDay(reader, SDay, sHour, eHour);
		}
		return EDay;
	}
	
	public static void main(String[] args){
		Random rand = new Random();
		User mySelf = new User();
		Scanner reader = new Scanner(System.in);
		ReturnList retList = new ReturnList();
		Sound sound = new Sound();
		int hour = 23;
		int day = 0;
		boolean programRunning = true;
		/*Creates Lists of both calls and news for the assistant */
		retList.CreateCalls();
		retList.CreateNews();
		/*********************************************************/
		
		while(programRunning){
			System.out.println("-----------------------------------------------");
			hour++;
			if (hour > 23){
				hour = 0;
				day += 1;
			}
			String th = "th";
			if (day == 11 || day == 12 || day == 13){
				th = "th";
			}else if (day == 1 || day % 10 == 1){// Program won't run for over a hundred days... right?
				th = "st";
			}else if (day == 2 || day % 10 == 2){
				th = "nd";
			}else if (day == 3 || day % 3 == 3){
				th = "rd";
			}
			System.out.println("Currently it's " + hour + ":00 of the " + day + th + " day");
			
			if (mySelf.checkActivity()){
				boolean taskEnd = retList.endTask(day, hour, mySelf);
				if (!taskEnd){
					System.out.println("You are at " + mySelf.getActivity() + " right now!");
				}
			}
			if (!mySelf.checkActivity()){ // Not else if because I want it to enter again
				
				retList.checkFutureTask(day, hour);
				retList.startTask(day, hour, mySelf);  // might change the result of checkactivity()
				
				if (mySelf.currentStatus() && !mySelf.checkActivity()){
					System.out.println("You are currently avaliable");
					System.out.println("[1] -> Press 1 to change your status\n"
									+ "[2] -> Press 2 to create a new task\n"
									+ "[3] -> press 3 to do nothing\n"
									+ "[0] -> Press 0 to exit");
					int userAnswer = reader.nextInt();
					if (userAnswer == 1){
						mySelf.changeStatus();
					}
					else if(userAnswer == 2){
						System.out.print("Task Title: ");
						String title= reader.next();
						int taskBegin = beginTaskTime(reader);
						int taskEnd = endTaskTime(reader);
						int sDay = startDay(reader, hour, taskBegin, day);
						int eDay = endDay(reader, sDay, taskBegin, taskEnd);
						//Avoid overlaping tasks needs to be added 
						
						Task newtask = new Task(title, taskBegin, taskEnd, sDay, eDay);
						retList.addTask(newtask);
					}
					else if(userAnswer == 0){
						programRunning = false;
					}
				}
				else if (!mySelf.checkActivity()){
					System.out.println("You are currently busy");
					System.out.println("[1] -> Press 1 to change your status\n"
									+ "[2] -> Press 2 to do nothing");
					int userAnswer = reader.nextInt();
					if (userAnswer == 1){
						mySelf.changeStatus();
						}
					}
				}
			
			
					
					
				if (programRunning){
					/*Checks for missed calls and missed news. If the user is
					 * Available then the assistant will show all missed items
					 * and then delete them, so it doesn't get printed more 
					 * than once*/ 
					retList.checkMissed(mySelf);
					/**********************************************************/
				
				
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
						 *If user is busy, then add it to missed News and update that list instead*/
						retList.ifNews(rand, mySelf, sound);
					
						/***************************************************************************/
					}
					else if (randomN == 1){//Calls
						/*There are new calls
						 *If the user is busy, add the call to missed calls and update the list*/
						retList.ifCall(rand, mySelf, sound);
						/***********************************************************************/
					}
					else{
						if (mySelf.currentStatus()){
							System.out.println("Nothing happened in this itteration.");
						}
					}
				}
			}
		/*Notify about last iteration so it doesn't end abruptly*/
		System.out.printf("Thank you for using this assistant\nThe end");
		reader.close();
		/********************************************************/
	}
}