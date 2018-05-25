package assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.*;

public class ReturnList {
	/*Class created to return one or more lists at once*/
	private List<Object> listObjectMissedNews;
	private List<String> listStringNews;
	private List<Object> listObjectCalls;
	private List<Object> listObjectMissedCalls;
	private List<Object> listObjectTasks;
	private List<Object> listObjectTasksInProgress;
	private List<Object> listObjectTasksFinished;
	/**************************************************/
	public ReturnList(){
		this.listObjectMissedNews = new ArrayList<Object>();
		this.listStringNews = new ArrayList<String>();
		this.listObjectCalls = new ArrayList<Object>();
		this.listObjectMissedCalls = new ArrayList<Object>();
		this.listObjectTasks = new ArrayList<Object>();
		this.listObjectTasksInProgress = new ArrayList<Object>();
		this.listObjectTasksFinished = new ArrayList<Object>();
	}
	
	public String checkMissedCalls(User mySelf, String callsFeed){
		if ((this.listObjectMissedCalls.size() > 0) && mySelf.currentStatus() == true){
			for (int j = 0; j < this.listObjectMissedCalls.size(); j++){
				/*If there are missed calls and the user is avaliable then shows the missed calls
				 *At the end, it will delete all missed calls from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingCalls call = (IncomingCalls) this.listObjectMissedCalls.get(j);
				String callersName = call.getName();
				int callersNumber = call.getNumber();
				callsFeed = callsFeed + "Missed call from: " + callersName + "(" + callersNumber + ") \n";
				/***************************************************************************************/
			}
			this.listObjectMissedCalls = new ArrayList<Object>();
		}
		return callsFeed;
	}
		
	public String checkMissedNews(User mySelf, String newsFeed){
		if (this.listObjectMissedNews.size() > 0 && mySelf.currentStatus() == true){
			for (int k = 0; k < this.listObjectMissedNews.size(); k++){
				/*If there are missed news and the user is avaliable then shows the missed news
				 *At the end, it will delete all missed news from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingNews missedNew = (IncomingNews) this.listObjectMissedNews.get(k);
				String title = missedNew.getTitle();
				newsFeed = newsFeed + "Missed News:  "+ title +  "\n";
				/****************************************************************************************/
			}
			this.listObjectMissedNews = new ArrayList<Object>();
		}
		return newsFeed;
		
	}
	
	public String ifNews(Random rand, User mySelf, Sound sound, String newsFeed){
		if (this.listStringNews.size() > 0){
			/*If there are still avaliable news, then it picks one randomly. Then, the
			 *picked news is deleted from the main news list to avoid getting duplicates*/
			int randomNews = rand.nextInt(this.listStringNews.size());
			IncomingNews newNew = new IncomingNews(this.listStringNews.get(randomNews), false);// false meant it had not yet been read. However, this is just a leftover and is unused. It might be important in a future update
			this.listStringNews.remove(randomNews); // Avoid getting repeated news
			
			if (mySelf.currentStatus() == true){
				/*If the user is available, then it will be shown. */
				newsFeed = newsFeed + "Breaking News!\n" + newNew.getTitle() + "\n\n";
				sound.soundNews();
				/**************************************************/
			}
			else{
				/*If user is busy, then it will be added to missed news to show them later.*/
				this.listObjectMissedNews.add(newNew);
				/***************************************************************************/
			}
			/*****************************************************************************/
		}
		return newsFeed;
	}
	
	public String ifCall(Random rand, User mySelf, Sound sound, String callsFeed){
		/*Select incoming call from list of possible calls*/
		int randomCalls = rand.nextInt(this.listObjectCalls.size());
		IncomingCalls newCall = (IncomingCalls) this.listObjectCalls.get(randomCalls);
		/**************************************************/
		
		if (mySelf.currentStatus() == true){//Notify the user of incoming call if he is avaliable
			callsFeed = callsFeed + (String)newCall.getName() + " (" + (int)newCall.getNumber() + ") is calling. \n\n";
			sound.soundCalls();
		} 
		else{
			/*Don't notify the user of an incoming call if he is busy
			 *Add the call to the missed calls list instead*/
			this.listObjectMissedCalls.add(newCall);
			/*****************************************************/
		}
		return callsFeed;
	}
	
	public void CreateCalls(){
		String fileName = "src\\assistant\\calls.txt";
        String line = null;
        List<Object> calls = new ArrayList<Object>();
        
        try {
        	/*Read file to get a list with the calls which the assistant will work with*/
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            /*Each line in the text file is a possible call
             *in the format of 
             *number-caller*/
            while((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("-");
                String caller = split[1];//Select the caller's name
                int callerNumber = Integer.parseInt(split[0]);//Select the callers number
            	calls.add(new IncomingCalls(callerNumber, caller));//Create an Object with the new 
            	/*information and add it to the list of calls*/
            }   
            this.listObjectCalls = calls;
            bufferedReader.close();    
            /***************************************************/
        }
        
        /*Something went wrong*/        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            	ex.printStackTrace();
        }
        /*********************/
    }
	
	public void CreateNews(){
		String fileName = "src\\assistant\\news.txt";
        String line = null;
        List<String> news = new ArrayList<String>();
        
        try {
        	/*Read a text file to get the incoming news. Each line is the
        	 *title of the news, and the assistant will work with it*/
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            news.add(line);
            }   
            this.listStringNews = news;
            bufferedReader.close();    
            /**********************************************************/
        }
        
        /*Something went wrong*/
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
                ex.printStackTrace();
        }
        /********************/
    }
	
	public void startTask(int day, int hour, User user){
		for (int i = 0; i < this.listObjectTasks.size(); i++){
			Task task = (Task) listObjectTasks.get(i);
			if (task.getSDay() == day && task.getStart() == hour && task.getStatus() == "Scheduled"){
				task.setStatus(1);
				if (user.currentStatus()){
					user.changeStatus();
				}
				user.changeActivity(task.getName());
				this.listObjectTasksInProgress.add(task);
				this.listObjectTasks.remove(i); //move to in progress list
				System.out.println("You have " + task.getName() + " now. Your status has changed to busy.");
			}
		}
	}
	
	public boolean endTask(int day, int hour, User user){
		for (int i = 0; i < this.listObjectTasksInProgress.size(); i++){// should be just one, for just in case
			Task task = (Task) listObjectTasksInProgress.get(i);
			if (task.getEDay() == day && task.getEnd() == hour && task.getStatus() == "InProgress"){
				task.setStatus(2);
				user.changeStatus();
				user.changeActivity("");
				this.listObjectTasksFinished.add(task);
				this.listObjectTasksInProgress.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void checkFutureTask(int day, int hour){
		int h = 0;
		int d = 0;
		if (hour == 23){
			h = 0;
			d = day + 1;
		}
		else{
			h = hour + 1;
			d = day;
		}
		for (int i = 0; i < this.listObjectTasks.size(); i++){
			Task task = (Task) listObjectTasks.get(i);
			if (task.getSDay() == d && task.getStart() == h && task.getStatus() == "Scheduled"){
				System.out.println("You have " + task.getName() + " in 1 hour.");
			}
		}
	}

	public boolean checkActivity(int day, int hour){
		for (int i = 0; i < this.listObjectTasks.size(); i++){
			Task task = (Task) listObjectTasks.get(i);
				if (task.getSDay() == day && task.getStart() == hour){
					System.out.println("You have to " + task.getName() + " now!\nYour Status is now busy");
					task.setStatus(1);
					return true;
				}
			}
		return false;
	}
	
	public void addTask(Task task){
		this.listObjectTasks.add(task);
	}
	
	public List<Object> retlistObjectMissedNews(){
		return this.listObjectMissedNews;
	}
	public List<String> listStringNews(){
		return this.listStringNews;
	}
	public List<Object> retlistObjectCalls(){
		return this.listObjectCalls;
	}
	public List<Object> retlistObjectMissedCalls(){
		return this.listObjectMissedCalls;
	}
}