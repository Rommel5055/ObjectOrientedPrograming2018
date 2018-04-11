package assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

public class ReturnList {
	/*Class created to return one or more lists at once*/
	public List<Object> listObjectMissedNews;
	public List<String> listStringNews;
	public List<Object> listObjectCalls;
	public List<Object> listObjectMissedCalls;
	/**************************************************/
	
	public ReturnList checkMissed(List<Object> missedCalls, List<Object> missedNews, User mySelf, ReturnList returnList){
		if ((missedCalls.size() > 0) && mySelf.available == true){
			for (int j = 0; j < missedCalls.size(); j++){
				/*If there are missed calls and the user is avaliable then shows the missed calls
				 *At the end, it will delete all missed calls from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingCalls call = (IncomingCalls) missedCalls.get(j);
				String callersName = call.callersName;
				int callersNumber = call.callersNumber;
				System.out.printf("Missed call from: %s (%d) \n", callersName, callersNumber);
				returnList.listObjectMissedCalls = new ArrayList<Object>();
				/***************************************************************************************/
			}
		}
		else{
			/*Returns unchanged missedCalls list*/
			returnList.listObjectMissedCalls = missedCalls;
		}
		if (missedNews.size() > 0 && mySelf.available == true){
			for (int k = 0; k < missedNews.size(); k++){
				/*If there are missed news and the user is avaliable then shows the missed news
				 *At the end, it will delete all missed news from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingNews missedNew = (IncomingNews) missedNews.get(k);
				String title = missedNew.title;
				System.out.printf("Missed News: %s \n", title);
				returnList.listObjectMissedNews = new ArrayList<Object>();
				/****************************************************************************************/
			}
		}
		else{
			/*Returns unchanged missedCalls list*/
			returnList.listObjectMissedNews = missedNews;
		}
		return returnList; 		
	}
	
	public static ReturnList ifNews(List<String> news, Random rand, User mySelf, List<Object> missedNews, ReturnList retList){
		if (news.size() > 0){
			/*If there are still avaliable news, then it picks one randomly. Then, the
			 *picked news is deleted from the main news list to avoid getting duplicates*/
			int randomNews = rand.nextInt(news.size());
			IncomingNews newNew = new IncomingNews();
			newNew.newNews(news.get(randomNews), false); // false meant it had not yet been read. However, this is just a leftover and is unused. It might be important in a future update 
			news.remove(randomNews); // Avoid getting repeated news
			
			if (mySelf.available == true){
				/*If the user is available, then it will be shown. */
				System.out.printf("Breaking News!\n");
				System.out.printf("%s \n", newNew.title);
				/**************************************************/
			}
			else{
				/*If user is busy, then it will be added to missed news to show them later.*/
				missedNews.add(newNew);
				/***************************************************************************/
			}
			/*****************************************************************************/
		}
	retList.listStringNews = news; //Update list of news, after one of them might have been deleted
	retList.listObjectMissedNews = missedNews; //Update list of missed news, after there might have been added a new news
	return retList;
	}
	
	public static ReturnList ifCall(Random rand, List<Object> calls, User mySelf, List<Object> missedCalls, ReturnList retList){
		/*Select incoming call from list of possible calls*/
		int randomCalls = rand.nextInt(calls.size());
		IncomingCalls newCall = (IncomingCalls) calls.get(randomCalls);
		/**************************************************/
		
		if (mySelf.available == true){//Notify the user of incoming call if he is avaliable
			System.out.printf("%s (%d) is calling. \n", (String)newCall.callersName, (int)newCall.callersNumber);
		} 
		else{
			/*Don't notify the user of an incoming call if he is busy
			 *Add the call to the missed calls list instead*/
			missedCalls.add(newCall);
			retList.listObjectMissedCalls = missedCalls;
			/*****************************************************/
		}
		return retList;
	}
	
	public ReturnList CreateCalls(ReturnList retList){
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
            retList.listObjectCalls = calls;
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
        return retList;
    }
	
	public ReturnList CreateNews(ReturnList retList){
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
            retList.listStringNews = news;
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
        return retList;
    }
}