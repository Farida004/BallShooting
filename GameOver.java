import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/**
 * GameOver class creates a compound object made up of rectangle and labels
 * (gameover label), (high score label), and (score label) and their locations.
 * 
 * @author Farida Aliyeva
 */
public class GameOver extends GCompound {
	// constructor
	public GameOver(int width, int height, int heighscore, int score) {
		// frame
		GRect frame = new GRect(width, height);
		frame.setFilled(true);
		frame.setFillColor(Color.magenta);
		add(frame);
		// game over label
		GLabel label = new GLabel("GAME OVER ", getWidth() / 2 - 200, getHeight() / 2);
		label.setFont("Timesnewroman-bold-50");
		label.setColor(Color.BLACK);
		add(label);
		// high score label
		GLabel labelH = new GLabel("High Score: " + heighscore, getWidth() / 2 - 200, getHeight() / 2 + 100);
		labelH.setFont("Timesnewroman-bold-30");
		labelH.setColor(Color.BLACK);
		add(labelH);
		// score label
		GLabel labelS = new GLabel("Score: " + score, getWidth() / 2 + 50, getHeight() / 2 + 100);
		labelS.setFont("Timesnewroman-bold-30");
		labelS.setColor(Color.BLACK);
		add(labelS);

	}
}
