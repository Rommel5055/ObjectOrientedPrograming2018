package assistant;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	//Sounds which shall be used
		String fileRing = "default/sounds/electronics023.wav"; //Ringtone
		String fileCameraShutter = "default/sounds/electronics026.wav"; //Camera Shuttle sound to be used when there are new news (What is the sound of news anyway?)
		static Clip soundClipRing;
		static Clip soundClipCameraShutter;  
		
		public Sound(){
			try {
				/*Check the sounds exist and there won't have problems */
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
		public void soundCalls(){
			if (soundClipRing.isRunning()) soundClipRing.stop();
				soundClipRing.setFramePosition(0); // rewind to the beginning
				soundClipRing.start();
		}
		public void soundNews(){
			if (soundClipCameraShutter.isRunning()) soundClipCameraShutter.stop();
				soundClipCameraShutter.setFramePosition(0); // rewind to the beginning
				soundClipCameraShutter.start();
		}
		
		
}
