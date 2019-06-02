import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/**
 * ScoreBoard class is a compound object which is made up of rectangle and
 * labels (score label), (killed label), (extra label), and their locations.
 * 
 * @author Farida Aliyeva
 */
public class ScoreBoard extends GCompound {
	static GLabel label, elabel, extralabel;

	// constructor
	public ScoreBoard(int width, int height) {
		// score board
		GRect rect = new GRect(width, height);
		rect.setFilled(true);
		rect.setFillColor(Color.gray);
		add(rect);
		// score label
		label = new GLabel("Score: ", getWidth() - 250, getHeight() / 2 - 200);
		label.setFont("Timesnewroman-bold-30");
		label.setColor(Color.white);
		add(label);
		// label of number of killed enemies
		elabel = new GLabel("Killed: ", getWidth() - 250, getHeight() / 2 + 100);
		elabel.setFont("Timesnewroman-bold-30");
		elabel.setColor(Color.white);
		add(elabel);
		// extra score label
		extralabel = new GLabel("Extra: ", getWidth() - 250, getHeight() / 2 - 100);
		extralabel.setFont("Timesnewroman-bold-30");
		extralabel.setColor(Color.white);
		add(extralabel);
		extralabel.setVisible(false);
	}
}
