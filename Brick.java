
import java.awt.Color;
import java.util.Random;

import acm.graphics.GRoundRect;

/**
 * Brick class creates brick from Round Rectangle and paints each brick with
 * random color.
 * 
 * @author Farida Aliyeva
 */
public class Brick extends GRoundRect {
	// constructor
	public Brick(int width, int height) {
		super(width, height);
		this.setFilled(true);
		this.setFillColor(selectColor());
	}

	/**
	 * This method gives random colors to bricks.
	 * 
	 * @return Color
	 */
	public Color selectColor() {
		Random r = new Random();
		int color = r.nextInt(9);
		switch (color) {
		case 0:
			return Color.YELLOW;
		case 1:
			return Color.PINK;
		case 2:
			return Color.RED;
		case 3:
			return Color.BLUE;

		case 4:
			return Color.GRAY;

		case 5:
			return Color.MAGENTA;

		case 6:
			return Color.ORANGE;

		case 7:
			return Color.GREEN;

		case 8:
			return Color.LIGHT_GRAY;

		default:
			return Color.BLACK;
		}

	}
}
