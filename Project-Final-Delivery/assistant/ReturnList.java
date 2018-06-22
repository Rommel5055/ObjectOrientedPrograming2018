package assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ReturnList {
	/*Class created to return one or more lists at once*/
	private List<Object> listObjectMissedNews;
	private List<String> listStringNews;
	private List<IncomingCalls> listObjectCalls;
	private List<Object> listObjectMissedCalls;
	private String urlAdress;

	/**************************************************/
	public ReturnList(){
		this.listObjectMissedNews = new ArrayList<Object>();
		this.listStringNews = new ArrayList<String>();
		this.listObjectCalls = new ArrayList<IncomingCalls>();
		this.listObjectMissedCalls = new ArrayList<Object>();
		this.urlAdress = "http://rss.cnn.com/rss/edition.rss";
	}
	
	public ArrayList<IncomingCalls> checkMissedCalls(User mySelf){
		if ((this.listObjectMissedCalls.size() > 0) && mySelf.currentStatus() == true){
			ArrayList<IncomingCalls> returnCalls = new ArrayList<IncomingCalls>();
			for (int j = 0; j < this.listObjectMissedCalls.size(); j++){
				/*If there are missed calls and the user is avaliable then shows the missed calls
				 *At the end, it will delete all missed calls from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingCalls call = (IncomingCalls) this.listObjectMissedCalls.get(j);
				call.setStatus(1);
				returnCalls.add(call);
				/***************************************************************************************/
			}
			this.listObjectMissedCalls = new ArrayList<Object>();
			return returnCalls;
		}
		return null;
	}
		
	public ArrayList<IncomingNews> checkMissedNews(User mySelf){
		if (this.listObjectMissedNews.size() > 0 && mySelf.currentStatus() == true){
			ArrayList<IncomingNews> returnNews = new ArrayList<IncomingNews>();
			for (int k = 0; k < this.listObjectMissedNews.size(); k++){
				/*If there are missed news and the user is available then shows the missed news
				 *At the end, it will delete all missed news from the list, therefore it is only shown
				 *when the user changes his status*/
				IncomingNews missedNew = (IncomingNews) this.listObjectMissedNews.get(k);
				missedNew.setStatus(1);
				returnNews.add(missedNew);
				/****************************************************************************************/
			}
			this.listObjectMissedNews = new ArrayList<Object>();
			return returnNews;
		}
		return null;
		
	}
	
	public IncomingNews ifNews(Random rand, User mySelf, Sound sound){
		if (this.listStringNews.size() > 0){
			/*If there are still avaliable news, then it picks one randomly. Then, the
			 *picked news is deleted from the main news list to avoid getting duplicates*/
			int randomNews = rand.nextInt(this.listStringNews.size());
			IncomingNews newNew = new IncomingNews(this.listStringNews.get(randomNews), 0);
			this.listStringNews.remove(randomNews); // Avoid getting repeated news
			if (mySelf.currentStatus() == true){
				/*If the user is available, then it will be shown. */
				String message = "Breaking News!\n" + newNew.getTitle() + "\n\n";//Console output for checking
				System.out.println(message);
				sound.soundNews();
				return newNew;
				/**************************************************/
			}
			else{
				/*If user is busy, then it will be added to missed news to show them later.*/
				newNew.setStatus(1);
				String message = newNew.getStatus() + "\n" + newNew.getTitle() + "\n\n";//Console output for checking
				System.out.println(message);
				this.listObjectMissedNews.add(newNew);
				System.out.println("News succesfully added");
				/***************************************************************************/
			}
			/*****************************************************************************/
		}
		return null;
	}
	
	public IncomingCalls ifCall(Random rand, User mySelf, Sound sound){
		/*Select incoming call from list of possible calls*/
		int randomCalls = rand.nextInt(this.listObjectCalls.size());
		IncomingCalls newCall = (IncomingCalls) this.listObjectCalls.get(randomCalls);
		System.out.println(newCall.getCallersName());
		/**************************************************/
		
		if (mySelf.currentStatus() == true){//Notify the user of incoming call if he is avaliable
			System.out.println((String)newCall.getCallersName() + " (" + (int)newCall.getCallersNumber() + ") is calling. \n\n");
			//Console Output
			sound.soundCalls();
			return newCall;
		} 
		else{
			System.out.println((String)newCall.getCallersName() + " (" + (int)newCall.getCallersNumber() + ") called. \n\n");
			/*Don't notify the user of an incoming call if he is busy
			 *Add the call to the missed calls list instead*/
			newCall.setStatus(1);
			this.listObjectMissedCalls.add(newCall);
			System.out.println("Call succesfully added");
			/*****************************************************/
		}
		return null;
	}
	
	public void CreateCalls(){
		String fileName = "src\\assistant\\calls.txt";
        String line = null;
        List<IncomingCalls> calls = new ArrayList<IncomingCalls>();
        
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
            	calls.add(new IncomingCalls(callerNumber, caller, 0));//Create an Object with the new 
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
        try{
        	URL rssUrl = new URL(urlAdress);
        	BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
        	String line;
        	while(((line = in.readLine()) != null) ){
        		String newLine = line;
        		while (newLine.contains("<title><![CDATA[")){
        			int firstPos = newLine.indexOf("<title><![CDATA[");
        			String temp = newLine.substring(firstPos);
        			newLine = newLine.substring(firstPos);
        			temp = temp.replace("<title><![CDATA[", "");
        			int lastPos = temp.indexOf("]]></title>");
        			temp = temp.substring(0, lastPos);
        			newLine = newLine.substring(lastPos + 11, newLine.length());
        			System.out.println(temp);
        			listStringNews.add(temp);
        		}
        	}
        	in.close();
        }
        catch(MalformedURLException ue){
        	System.out.println("Malformed URL Exception");
        }
        catch(IOException ioe){
        	System.out.println("Something went wrong with the RSS");
        }
    }
	
	public List<Object> retlistObjectMissedNews(){
		return this.listObjectMissedNews;
	}
	public List<String> listStringNews(){
		return this.listStringNews;
	}
	public List<IncomingCalls> retlistObjectCalls(){
		return this.listObjectCalls;
	}
	public List<Object> retlistObjectMissedCalls(){
		return this.listObjectMissedCalls;
	}
}