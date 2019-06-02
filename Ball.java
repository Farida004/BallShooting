import java.awt.Color;

import acm.graphics.GOval;

/**
 * Ball class creates ball contains information about x, y locations and moves
 * ball checking 4 sides boundaries.
 * 
 * @author Farida Aliyeva
 */
public class Ball implements Objects {
	GOval ball1;
	// speed of the ball
	private int vsx = 3;
	private int vsy = 3;

	// constructor
	public Ball(int width) {
		ball1 = new GOval(width, width);
		ball1.setFilled(true);
		ball1.setFillColor(Color.gray);
	}

	/**
	 * Overriden move method which takes 2 integer values.
	 */
	@Override
	public void move(int x, int y) {
		ball1.move(x, y);
		// checks for left and right collisions
		if ((ball1.getX() <= 0) || (ball1.getX() >= (CONSTANT.PLAY_WINDOW - CONSTANT.BALLSIZE))) {
			vsx = -vsx;
		}
		// check for ceiling collision
		if ((ball1.getY() <= 0)) {
			vsy = -vsy;
		}
	}

	// getters and setters to X and Y locations of ball and X and Y speeds of
	// the ball.
	public int getY() {
		return (int) ball1.getY();

	}

	public int getX() {
		return (int) ball1.getX();
	}

	public int getVSX() {
		return vsx;
	}

	public int getVSY() {
		return vsy;
	}

	/**
	 * This method changes y direction of the ball.
	 * 
	 * @return -vsy
	 */
	public int resetVSY() {
		return vsy * (-1);

	}

	/**
	 * Sets new y speed value.
	 * 
	 * @param vsy
	 */
	public void setVSY(int vsy) {
		this.vsy = vsy;

	}

	/**
	 * This method changes x direction of the ball.
	 * 
	 * @return -vsx
	 */
	public int resetVSX() {
		return vsx * (-1);

	}

	/**
	 * Sets new x speed value.
	 * 
	 * @param vsy
	 */
	public void setVSX(int vsx) {
		this.vsx = vsx;

	}
}