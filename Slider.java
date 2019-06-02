
import java.awt.Color;

import acm.graphics.GRoundRect;

/**
 * Slider class which is made up of Round Rectangle and has two move methods:
 * moveLeft and moveRight to change the location of slider.
 * 
 * @author Farida Aliyeva
 */
public class Slider extends GRoundRect {
	// constructor
	public Slider(int width, int height) {
		super(width, height);
		this.setFilled(true);
		this.setFillColor(Color.BLACK);
	}

	/**
	 * This method moves the slider to the Left.
	 * 
	 * @param x
	 * @param y
	 */
	public void moveLeft(double x, double y) {

		this.move(-x, y);

	}

	/**
	 * This method moves the slider to the Right.
	 * 
	 * @param x
	 * @param y
	 */
	public void moveRight(double x, double y) {
		this.move(x, y);
	}

}
