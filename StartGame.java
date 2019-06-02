import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/**
 * StartGame class is a compound object which is made of a rectangle and label
 * (start a game label), and their locations.
 * 
 * @author Farida Aliyeva
 */
public class StartGame extends GCompound {
	// contructor
	public StartGame(int width, int height) {
		// welcome frame
		GRect frame = new GRect(width, height);
		frame.setFilled(true);
		frame.setFillColor(Color.magenta);
		add(frame);
		// start a game label
		GLabel label = new GLabel("START A GAME ", getWidth() / 2 - 200, getHeight() / 2);
		label.setFont("Timesnewroman-bold-50");
		label.setColor(Color.BLACK);
		add(label);
	}
}
