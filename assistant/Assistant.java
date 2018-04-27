package assistant;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

 
public class Assistant {
	
	//Se declaran los sonidos que se usaran en el programa
	String fileRing = "default/sounds/electronics023.wav"; // Audio de llamada telefonica
	String fileCameraShutter = "default/sounds/electronics026.wav"; // Audio de camara fotografica, que sera usado en las noticias
	static Clip soundClipRing;
	static Clip soundClipCameraShutter;  
	
	public Assistant(){
		try {
			/*Verificar que todo funcione respecto a los audios que se usaran */
			   URL url = this.getClass().getClassLoader().getResource(fileRing);
			   if (url == null) {
			      System.err.println("Couldn't find file: " + fileRing);
			   } else {
			      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			      soundClipRing = AudioSystem.getClip();
			      soundClipRing.open(audioIn);
			   }
			       
			   url = this.getClass().getClassLoader().getResource(fileCameraShutter);
			   if (url == null) {
			      System.err.println("Couldn't find file: " + fileCameraShutter);
			   } else {
			      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			      soundClipCameraShutter = AudioSystem.getClip();
			      soundClipCameraShutter.open(audioIn);
			   }
			} catch (UnsupportedAudioFileException e) {
			   System.err.println("Audio Format not supported!");
			} catch (Exception e) {
			   e.printStackTrace();
			}
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		int numberOfCicles = Integer.parseInt(args[0]);
		new Assistant();
		Random rand = new Random();
		User mySelf = new User();
		Scanner reader = new Scanner(System.in);
		ReturnList retList = new ReturnList();
		
		
		
		/*Creates Lists of both calls and news for the assistant */
		retList.CreateCalls(retList);
		retList.CreateNews(retList);
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
			retList.checkMissed(missedCalls, missedNews, mySelf);
			missedCalls = retList.listObjectMissedCalls;
			missedNews = retList.listObjectMissedNews;
			/**********************************************************/
			
			/*Asks the user if he is still busy or avaliable and changes the status 
			 * of user according to the user input*/
			mySelf.checkStatus(reader);
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
				 *If user is busy, then add it to missed News and update that list instead*/
				ReturnList.ifNews(news, rand, mySelf, missedNews, soundClipCameraShutter);
				news = retList.listStringNews;
				missedNews = retList.listObjectMissedNews;
					
				/***************************************************************************/
			}
			else if (randomN == 1){//Calls
				/*There are new calls
				 *If the user is busy, add the call to missed calls and update the list*/
				ReturnList.ifCall(rand, calls, mySelf, missedCalls, soundClipRing);
				missedCalls = retList.listObjectMissedCalls;
				/***********************************************************************/
			}
			else{
				System.out.println("Nothing happened in this itteration.");
			}
		}
		/*Notify about last iteration so it doesn't end abruptly*/
		System.out.printf("Thank you for using this assistant\nThe end");
		reader.close();
		/********************************************************/
	}
}