package Main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import Block.*;
import Screens.Main_Menu;
import Screens.Mouse_Listener;

public class Game implements ActionListener {

	public static JFrame frame = new JFrame("Brick Breaker Game");
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static JLayeredPane cont;
	public static ArrayList<JLabel> hearts = new ArrayList<JLabel>();
	public static JLabel player, backGround, statBlock, scoreText, powerUpScore, powerUpScore2, previousPower,
			instructions, instructions1, currentPower, heart;
	public static Timer timer, newTimer;
	public static ImageIcon gameOver = (new ImageIcon("backgrounds\\game_over_screen_2.png")),
			gameNotOver = (new ImageIcon("backgrounds\\bakground1.png")), otherBackground = (new ImageIcon("backgrounds\\bakground3.png")),
			playBlock = (new ImageIcon("misc\\block_my_3.png")), emptyPlayBlock = (new ImageIcon("misc\\block_my_empty.png")),
			playBall = (new ImageIcon("misc\\ball_my.png")), unknownPower = (new ImageIcon("powerUp_Icons\\unknown_power.png")), 
			heartImage = (new ImageIcon("misc\\heart.png")), gameWon = (new ImageIcon("backgrounds\\bakground_won.png")),
			playerImage = (new ImageIcon("misc\\player5.png"));
	public static Font font = new Font("Serif", Font.PLAIN, 30), smallFont = new Font("Serif", Font.PLAIN, 17);
	
	public static int screenHeight = 1080, screenWidth = 1920, borderBounds = 3;
	public static int playerStartPositionY = 750, playerStartPositionX = 900, deathCount;

	public static int dirY = -1, dirX = 0, start = 0, playerX = 0, playerY = 0, timerTime, gameScore;
	public static boolean pressed;

	public Game() {
		settingUp();
		hearts();
	}

	public void actionPerformed(ActionEvent e) {
		playerXY();
		playerMovement();
		Ball.ballMovement();
		powerUpFunction();
		timer.setDelay(timerTime);
		scoreText.setText("CURENT SCORE: " + gameScore);
	}

	void playerMovement() {
		if (dirX == 1 && start == 1 || dirX == -1 && start == 1 || Catch.isCatched == true) {
			player.setLocation(playerX, playerY);
		}
		if (player.getX() <= 0 + borderBounds) {
			dirX = 0;
		}
		if (player.getX() >= screenWidth - 150 - borderBounds) { // Šos divus "if'us" nevarēju salikt kopā, jo tad
																// nekorekti strādā spēlētāja apstādināšana
			dirX = 0;
		}
	}

	static void death() {
		deathCount++;
		PowerUp.killThem();
		backGround.setIcon(gameOver);
		start = -1;
		player.setLocation(playerStartPositionX, playerStartPositionY);
		currentPower.setIcon(unknownPower);
		previousPower.setIcon(unknownPower);
		cont.remove(hearts.get(hearts.size()-1));
		hearts.remove(hearts.size()-1);
		new Ball();
		dirX = 0;
		if (deathCount == 3) {
			timer.stop();
			newTimer.stop();
			start = 1;
			return;
		}
	}

	static void playerXY() {
		playerY = player.getY();
		playerX = player.getX() + (8 * dirX);
		Block.blockHit();
		if (Mouse_Listener.endlessState.equals("off") && Block.breakableBlockCount == 0) {
			backGround.setIcon(gameWon);
			new Ball();
		}
	}

	public static void keyPress(int keyPressed, int i) {
		pressed = false;
		if (i == 1) {
			pressed = true;
		}
		switch (keyPressed) {
		case (68): // Right
			dirX = 1 * i;
			Ball.newDirX = 1 * i;
			break;
		case (65): // Left
			dirX = -1 * i;
			Ball.newDirX = -1 * i;
			break;
		case (32): // Start the game
			instructions.setVisible(false);
			instructions1.setVisible(false);
			if (Catch.isCatched) {
				Catch.isCatched = false;
			}
			if (start != 1) {
				start = 1;
				backGround.setIcon(gameNotOver);
			}
			break;
		case (27): //Back to Main Menu
			setupGame();
			frame.dispose();
			Main_Menu.frame.setVisible(true);
		}
	}

	public static void setupGame() {
		Block.balls.clear();
		Block.blocks.clear();
		Block.powerUps.clear();
		hearts.clear();
		cont.removeAll();
		timer.stop();
		newTimer.stop();
		gameScore = 0;
		player.setLocation(playerStartPositionX, playerStartPositionY);
		dirX = 0;
		start = -1;

	}
	
	static void hearts() {
		for (int i = 0; i < 3; i++) {
			heart = new JLabel(heartImage);
			heart.setBounds(830 + i*110, 900, 80, 80);
			hearts.add(heart);
			cont.add(heart, (Integer) 3);
			cont.repaint();
		}
	}
	
	void powerUpFunction() {
		Speed_Up.power(false);
		if (Catch.isCatched) {
			Catch.onPlayer();
		}
		Catch.power(false);
		PowerUp.powerupMovement();
	}

	public void settingUp() {
		cont = new JLayeredPane();
		cont.setSize(screenSize.width,screenSize.height);

		statBlock = new JLabel(otherBackground);
		statBlock.setBounds(0, 800, 1920, 220);

		backGround = new JLabel(gameNotOver);
		backGround.setSize(screenSize.width,screenSize.height);

		frame.setBounds(0, 0, screenWidth, screenHeight);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(null);

		player = new JLabel(playerImage);
		player.setBackground(Color.BLACK);
		player.setOpaque(true);
		player.setBounds(playerStartPositionX, playerStartPositionY, 150, 30);

		currentPower = new JLabel(unknownPower);
		currentPower.setBounds(70, 870, 120, 120);

		previousPower = new JLabel(unknownPower);
		previousPower.setBounds(1720, 870, 120, 120);

		instructions = new JLabel("Press 'Esc' to go back to Main Menu");
		instructions.setBounds(20, 470, 600, 500);
		instructions.setFont(smallFont);
		instructions.setForeground(Color.BLACK);
		instructions.setOpaque(false);

		instructions1 = new JLabel("Press 'w,a,s,d' to move the play block");
		instructions1.setBounds(20, 500, 600, 500);
		instructions1.setFont(smallFont);
		instructions1.setForeground(Color.BLACK);
		instructions1.setOpaque(false);

		powerUpScore = new JLabel("LAST POWER UP");
		powerUpScore.setBounds(1620, 590, 400, 500);
		powerUpScore.setFont(font);
		powerUpScore.setForeground(Color.WHITE);
		powerUpScore.setOpaque(false);

		powerUpScore2 = new JLabel("CURENT POWER UP");
		powerUpScore2.setBounds(10, 590, 400, 500);
		powerUpScore2.setFont(font);
		powerUpScore2.setForeground(Color.WHITE);
		powerUpScore2.setOpaque(false);

		scoreText = new JLabel("CURENT SCORE: ");
		scoreText.setBounds(850, 590, 400, 500);
		scoreText.setFont(font);
		scoreText.setForeground(Color.WHITE);
		scoreText.setOpaque(false);

		cont.add(backGround, (Integer) 0);
		cont.add(player, (Integer) 1);
		cont.add(powerUpScore2, (Integer) 2);
		cont.add(instructions1, (Integer) 2);
		cont.add(instructions, (Integer) 2);
		cont.add(powerUpScore, (Integer) 2);
		cont.add(scoreText, (Integer) 2);
		cont.add(statBlock, (Integer) 2);
		cont.add(previousPower, (Integer) 3);
		cont.add(currentPower, (Integer) 3);

		MyKeyListener kl1 = new MyKeyListener();
		frame.add(cont);
		frame.addKeyListener(kl1);

		new Block();
		new Ball();

		timerTime = 10;
		deathCount = 0;
		timer = new Timer(timerTime, this);
		newTimer = new Timer(timerTime / 3, this);
		timer.start();

	}

}
