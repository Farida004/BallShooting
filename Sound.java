import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class contains information about sounds used in a game and make it more
 * beautiful and funny.
 * 
 * @author Farida Aliyeva
 */
public class Sound {
	private static Clip pew;
	private static String pewSound;

	// constructor
	public Sound(String fileName) {
		pewSound = fileName;
	}

	/**
	 * This method plays the music file and throws an exception if it is not
	 * found or not appropriate music file user uses.
	 */
	public synchronized void play() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					pew = AudioSystem.getClip();
					AudioInputStream input = AudioSystem.getAudioInputStream(new File(pewSound));
					pew.open(input);
					pew.start();
				} catch (Exception e) {
					System.out.println("sound " + e.getMessage() + pewSound);
				}

			}

		});
		thread.start();
	}
}
