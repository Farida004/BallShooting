import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTextField;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class BallTest extends GraphicsProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	/**
	 * Main class of the project. Runs the game.
	 *
	 * 
	 * @author Farida Aliyeva
	 */
	public static final int APPLICATION_WIDTH = 850;
	public static final int APPLICATION_HEIGHT = 600;
	private static final int BRICKWIDTH = (CONSTANT.PLAY_WINDOW - (CONSTANT.BRICK_PER_ROW) * CONSTANT.SPACE)
			/ CONSTANT.BRICK_PER_ROW;

	// objects intance variables
	private Brick[][] brick = new Brick[CONSTANT.BRICK_PER_ROW][CONSTANT.BRICK_PER_COL];
	private Enemies enemy = new Enemies("50x50.png");
	private Random rgen = new Random();
	private ScoreBoard board;
	private JTextField cheat;
	private Bullet bullet;
	private Slider slider;
	private Sound sounds;
	private GLabel label;
	private GRect rect;
	private Ball ball;
	// scores
	private int killed = 0;
	private int score = 0;
	private int highscore;
	private int extra = 0;
	private int ROUND = 1;
	// speeds
	private int bulletSpeed = 8;
	private int enemySpeed = 2;
	private int x = 40;// slider "speed"

	// number of bricks on play screen
	private int numofBricks = CONSTANT.BRICK_PER_COL * CONSTANT.BRICK_PER_ROW;

	// game pause
	private boolean pause = false;
	private int PAUSE = 15;
	private int timer = 0;

	public void init() {
		// adds buttons EXIT and ENTER and adds text field to enter cheatcode
		add(new JButton("EXIT"), EAST);
		cheat = new JTextField(5);
		add(cheat, EAST);
		cheat.setEnabled(false);
		add(new JButton("ENTER"), EAST);
		addActionListeners();
	}

	public void run() {
		addKeyListeners();
		startGame();
		initBoard();
		playGame();
		if (numofBricks > 0 && ball.getY() + CONSTANT.BALLSIZE >= APPLICATION_HEIGHT) {
			restart();

		}
		if (enemy.enemyIm.getBounds().intersects(slider.getBounds())) {
			restart();
		}

	}

	/**
	 * This method adds a welcome frame then waits for click and removes the
	 * frame
	 */
	private void startGame() {
		StartGame game = new StartGame(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(game);
		waitForClick();
		remove(game);
	}

	/**
	 * This method initializes the board by adding objects on the screen. Adds
	 * play screen score board adds bricks slider and ball snd enemy.
	 */
	private void initBoard() {
		int random = rgen.nextInt(CONSTANT.PLAY_WINDOW - (int) enemy.enemyIm.getWidth());
		rect = new GRect(CONSTANT.PLAY_WINDOW, APPLICATION_HEIGHT);
		rect.setFilled(true);
		rect.setColor(Color.CYAN);
		board = new ScoreBoard(APPLICATION_WIDTH - CONSTANT.PLAY_WINDOW, APPLICATION_HEIGHT);
		add(board, CONSTANT.PLAY_WINDOW, 0);
		add(rect);
		createBrick();
		addBall();
		addSlider();
		add(enemy.enemyIm, random, -50);
	}

	/**
	 * This method adds slider down in the center.
	 */
	public void addSlider() {
		slider = new Slider(CONSTANT.SLIDERWIDTH, CONSTANT.SLIDERHEIGHT);
		add(slider, CONSTANT.PLAY_WINDOW / 2 - CONSTANT.SLIDERWIDTH / 2,
				APPLICATION_HEIGHT - 6 * CONSTANT.SLIDERHEIGHT);

	}

	/**
	 * This method adds ball slightly above slider.
	 */
	public void addBall() {
		ball = new Ball(CONSTANT.BALLSIZE);
		add(ball.ball1, CONSTANT.PLAY_WINDOW / 2 - CONSTANT.SLIDERWIDTH / 2 + CONSTANT.SLIDERWIDTH / 2,
				APPLICATION_HEIGHT - 6 * CONSTANT.SLIDERHEIGHT - CONSTANT.BALLSIZE - 3);
	}

	/**
	 * This method initializes all bricks with 2 for loops putting small spaces
	 * between them.
	 */
	public void createBrick() {
		for (int i = 0; i < CONSTANT.BRICK_PER_ROW; i++) {
			for (int j = 0; j < CONSTANT.BRICK_PER_COL; j++) {
				brick[i][j] = new Brick(BRICKWIDTH, CONSTANT.BRICKHEIGHT);
				double x = 0 + j * BRICKWIDTH + j * CONSTANT.SPACE;
				double y = 0 + i * CONSTANT.BRICKHEIGHT + i * CONSTANT.SPACE;
				add(brick[i][j], x, y);

			}
		}
	}

	/**
	 * This method restarts the game, saves the score.
	 */
	private void restart() {
		try {
			saveScore();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		removeAll();
		gameOver();
		startAgain();

	}

	/**
	 * This method restarts the game by updating score variables, initializes
	 * boards and starts the game again.
	 */
	private void startAgain() {
		score = 0;
		extra = 0;
		numofBricks = CONSTANT.BRICK_PER_COL * CONSTANT.BRICK_PER_ROW;
		PAUSE = 15;
		ROUND = 1;
		initBoard();
		playGame();
		if (numofBricks > 0 && ball.getY() + CONSTANT.BALLSIZE >= APPLICATION_HEIGHT) {
			restart();
		}
		if (enemy.enemyIm.getBounds().intersects(slider.getBounds())) {
			restart();
		}

	}

	/**
	 * This method runs the game by moving the ball, checks for gameOver cases.
	 */
	public void playGame() {
		while (true) {
			moveBall();
			if (enemy.enemyIm.getBounds().intersects(slider.getBounds())) {
				break;
			}
			if (numofBricks > 0 && ball.getY() + CONSTANT.BALLSIZE >= APPLICATION_HEIGHT) {
				break;
			}
			if (numofBricks == 0) {
				ball.ball1.setLocation(ball.getX(), ball.getY());
				label = new GLabel("WINNER ", getWidth() / 2 - 200, getHeight() / 2);
				label.setFont("Timesnewroman-bold-50");
				label.setColor(Color.BLACK);
				add(label);
				break;
			}
		}
	}

	/**
	 * This method checks the collisions for ball while moving and moves ball
	 * and other objects if pause is not pressed.
	 */
	public void moveBall() {
		if (!pause) {
			moveBullet();
			changeScoreAndRound();
			ball.move(ball.getVSX(), ball.getVSY());
			// collision with slider
			if ((ball.getY() + CONSTANT.BALLSIZE <= slider.getY()
					&& ball.getY() + CONSTANT.BALLSIZE >= slider.getY() - 5
					&& ball.ball1.getBounds().intersects(slider.getBounds()))) {
				ball.setVSY(ball.resetVSY());
			}
			// collision with bricks
			GObject collObj = getElementAt(ball.getX(), ball.getY());
			if (collObj != slider && collObj != rect && collObj != null && collObj != board
					&& collObj != enemy.enemyIm) {
				if (ball.ball1.getBounds().intersects(collObj.getBounds())) {
					sounds = new Sound("bell.wav");
					sounds.play();
					pause(5);
					remove(collObj);
					ball.setVSY(ball.resetVSY());
					numofBricks--;
				}
			}
			moveEnemy();
		}
		pause(PAUSE);

	}

	/**
	 * This method moves the bullet and checks for its collision with enemy.
	 */
	private void moveBullet() {
		if (bullet != null) {
			bullet.move(0, bulletSpeed);
			if (bullet.getY() + 10 <= 0) {
				bullet = null;
			}
			if (bullet != null) {
				if (bullet.bullet1.getBounds().intersects(enemy.enemyIm.getBounds()) && enemy.enemyIm.getY() >= 0) {
					killed += 1;
					int random = rgen.nextInt(CONSTANT.PLAY_WINDOW - (int) enemy.enemyIm.getWidth());
					remove(bullet.bullet1);
					bullet = null;
					enemy.enemyIm.setLocation(random, -20);
					ScoreBoard.elabel.setLabel("Killed: " + killed);
				}
			}
		}
	}

	/**
	 * This method counts timer, score, refreshes score, round and adds
	 * appropriate labels.
	 */
	private void changeScoreAndRound() {
		timer++;
		if (timer % 150 == 0) {
			score += 10;
			ScoreBoard.label.setLabel("Score: " + score);// refreshed score
			if (score % 150 == 0) {
				ROUND++;
				if (ROUND <= 4) {// rounds
					PAUSE -= 2;
					rect.setColor(changeColor());
					label = new GLabel("ROUND# " + ROUND, getWidth() / 2 - 200, getHeight() / 2);
					label.setFont("Timesnewroman-bold-50");
					label.setColor(Color.BLACK);
					add(label);
					pause(400);
					remove(label);
				}
				if (ROUND == 5) {// rounds
					PAUSE = 9;
					rect.setColor(changeColor());
					label = new GLabel("INFINITY", getWidth() / 2 - 200, getHeight() / 2);
					label.setFont("Timesnewroman-bold-50");
					label.setColor(Color.BLACK);
					add(label);
					pause(400);
					remove(label);
				}
			}
		}
	}

	/**
	 * This method changes the Color of the background as new Round starts.
	 * 
	 * @return Color.
	 */
	private Color changeColor() {
		if (ROUND == 2) {
			Color purple = new Color(204, 0, 204);
			return purple;
		}
		if (ROUND == 3) {
			Color green = new Color(0, 255, 128);
			return green;
		}
		if (ROUND == 4) {
			Color orange = new Color(255, 153, 51);
			return orange;
		}
		Color cream = new Color(204, 153, 255);
		return cream;
	}

	/**
	 * This method starts moving enemies with different speeds on different
	 * Rounds.
	 */
	private void moveEnemy() {
		if ((ROUND == 2 || score == 150)) {
			enemy.move(enemySpeed, enemySpeed);

		}
		if (score >= 300) {
			enemy.move(enemySpeed, enemySpeed + 1);
		}
	}

	/**
	 * This method creates game over frame, reads information about
	 * game(highscore).
	 */
	private void gameOver() {
		readInfo();
		GameOver over = new GameOver(APPLICATION_WIDTH, APPLICATION_HEIGHT, highscore, score + extra);
		add(over);
		waitForClick();
		remove(over);
	}

	/**
	 * This method reads information about the game (high score) in the file.
	 */
	private void readInfo() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Ball.txt"));
			String thisLine;
			while ((thisLine = br.readLine()) != null) {
				// converts to integer.
				Integer parsed = Integer.parseInt(thisLine);
				highscore = parsed;
			}

		} catch (FileNotFoundException e) {
			System.err.println("The file you specified does not exist.");
		} catch (IOException e) {
			System.err.println("Some other IO exception occured. Message: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Invalid number");

		} finally

		{
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method manages different operations while user presses the keys.
	 */
	public void keyPressed(KeyEvent e) {
		if (slider != null && (!pause)) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				// moves the slider to the left
				slider.moveLeft(x, 0);
				// checks for border
				if (slider.getX() <= 0) {
					slider.moveRight(x, 0);
				}
				break;
			case KeyEvent.VK_RIGHT:
				// moves slider to the left
				slider.moveRight(x, 0);
				// checks for border
				if (slider.getX() + CONSTANT.SLIDERWIDTH >= CONSTANT.PLAY_WINDOW) {
					slider.moveLeft(x, 0);
				}
				break;
			case KeyEvent.VK_X:
				sounds = new Sound("pew.wav");
				// shots a bullet and makes a sound of a shot
				if (bullet == null) {
					sounds.play();
					bullet = new Bullet(CONSTANT.BULLET_WIDTH, CONSTANT.BULLET_HEIGHT);
					if (!pause) {
						add(bullet.bullet1, slider.getX() + CONSTANT.SLIDERWIDTH / 2, slider.getY());
					}
				}
			}
		}
		// checks for 2 keys pressed and enables a text field.
		if (e.isControlDown() && e.isShiftDown()) {
			pause = true;
			cheat.setEnabled(true);
		}
		// checks for space key pressed and pauses or "unpauses" the game.
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (pause == false) {
				pause = true;
			} else if (pause == true) {
				pause = false;
			}
		}
	}

	/**
	 * This method checks for buttons pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		// exits game when EXIT button is pressed.
		if (e.getActionCommand().equals("EXIT")) {
			exit();
		}
		// enters a value from the text field and checks if it is correct or
		// not.
		if (e.getActionCommand().equals("ENTER")) {
			sounds = new Sound("wrong.wav");
			// returns focus to a play window.
			getGCanvas().requestFocus();
			if (Integer.parseInt(cheat.getText()) == 409) {
				ScoreBoard.extralabel.setVisible(true);
				// adds extra 100 score if cheat code is correct.
				extra += 100;
				ScoreBoard.extralabel.setLabel("Extra: " + extra);
				cheat.setEnabled(false);
				cheat.setText("");
				pause = false;
			} else {
				// makes a "wrong answer sound" when cheat code is false.
				sounds.play();
				cheat.setEnabled(false);
				cheat.setText("");
				pause = false;

			}
		}

	}

	/**
	 * This method saves the score of the game(high score).
	 * 
	 * @throws IOException
	 */
	private void saveScore() throws IOException {
		boolean append;
		try {
			append = true;
			PrintWriter pw = new PrintWriter(new FileWriter("Ball.txt", append));
			// checks for a high score
			if (score + extra > highscore) {
				highscore = score + extra;
				// writes it in the file
				pw.println(highscore);
			}
			// closes writer.
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new BallTest().start();
	}
}
