
import java.util.Random;

import acm.graphics.GImage;

/**
 * Enemies class creates enemy from png image and contains informations about x,
 * y locations and moves image from one side of play screen to another.
 * 
 * @author Farida Aliyeva
 */
public class Enemies implements Objects {
	GImage enemyIm;
	Random rgen = new Random();// random
	private boolean enemyToLeft = true;// direction
	// constructor

	public Enemies(String imageName) {
		enemyIm = new GImage(imageName);
	}

	// getters for X and Y coordinates of enemy
	public int getY() {
		return (int) enemyIm.getY();

	}

	public int getX() {
		return (int) enemyIm.getX();
	}

	/**
	 * Overriden move method which takes 2 integer values which checks for
	 * collision with left and right borders of the play screen.
	 */
	@Override
	public void move(int x, int y) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				if (enemyToLeft) {
					enemyIm.move(-x, y);
					if (enemyIm.getX() <= 0) {
						enemyToLeft = false;
					}
				} else {
					enemyIm.move(x, y);
					if (enemyIm.getX() >= CONSTANT.PLAY_WINDOW - enemyIm.getWidth()) {
						enemyToLeft = true;
					}
				}
				if (enemyIm.getY() > 600) {
					int random = rgen.nextInt(CONSTANT.PLAY_WINDOW - (int) enemyIm.getWidth());
					enemyIm.setLocation(random, -50);
				}

			}
		});
		thread.start();
	}
}