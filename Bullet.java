import java.awt.Color;

import acm.graphics.GOval;

/**
 * Bullet class creates bullet from oval contains information about x, y
 * locations and moves bullet.
 * 
 * @author Farida Aliyeva
 */
public class Bullet implements Objects {
	GOval bullet1;

	// constructor
	public Bullet(int width, int height) {
		bullet1 = new GOval(width, height);
		bullet1.setFilled(true);
		bullet1.setFillColor(Color.red);

	}

	/**
	 * Overriden move method which takes 2 integer values.
	 */
	@Override
	public void move(int x, int bulletSpeed) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				bullet1.move(0, -bulletSpeed);
			}

		});
		thread.start();
	}

	// getters for X and Y coordiates of bullet.
	public int getY() {
		return (int) bullet1.getY();

	}

	public int getX() {
		return (int) bullet1.getX();
	}
}
