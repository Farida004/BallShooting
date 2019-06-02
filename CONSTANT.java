/**
 * Constant interface holds constants which are useful throughout the game.
 * 
 * @author Farida Aliyeva
 */
public interface CONSTANT {
	static final int BALLSIZE = 20;
	static final int SLIDERWIDTH = 100;
	static final int SLIDERHEIGHT = 10;
	static final int PLAY_WINDOW = 600;
	static final int BRICK_PER_ROW = 5;
	static final int BRICK_PER_COL = 5;
	static final int BRICKHEIGHT = 15;
	static final int SPACE = 5;
	static final int BRICKWIDTH = (PLAY_WINDOW - (BRICK_PER_ROW) * SPACE) / BRICK_PER_ROW;
	static final int BULLET_WIDTH = 5;
	static final int BULLET_HEIGHT = 10;
	static final int bulletSpeed = 8;
}
