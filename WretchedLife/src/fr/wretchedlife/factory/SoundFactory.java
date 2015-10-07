package fr.wretchedlife.factory;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundFactory {

	public static final String walkSoundFilePath = ".//sounds//walk.wav";
	public static final String drinkSoundFilePath = ".//sounds//drink.wav";
	public static final String eatSoundFilePath = ".//sounds//eat.wav";
	public static final String attackSoundFilePath = ".//sounds//slash.wav";
	public static final String equipSoundFilePath = ".//sounds//equip.wav";
	public static final String storeItemSoundFilePath = ".//sounds//storeitem.wav";
	public static final String doorFilePath = ".//sounds//door.wav";
	public static final String impossibleFilePath = ".//sounds//impossible.wav";

	public SoundFactory() {}
	
	public static void playSound( String soundFilePath ) {
		
		Clip clip = null; 
		
		try {
			File soundFile = new File( soundFilePath );
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			
			clip = AudioSystem.getClip();
			clip.open( audioInputStream );
			clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void playAmbientSound() {
		
		Clip clip = null; 
		
		try {
			File soundFile = new File( ".//sounds//ambient.wav" );
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			
			clip = AudioSystem.getClip();
			clip.open( audioInputStream );
			clip.loop( Clip.LOOP_CONTINUOUSLY );
			clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
