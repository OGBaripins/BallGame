package Main;

import java.util.Random;

import javax.swing.*;
import Block.Block;
import Screens.Mouse_Listener;

public class Ball extends JLabel{
	private static final long serialVersionUID = 1L;
	public JLabel ballLabel;
	public static int ballX = 0, ballY = 0;
	public int dirY, dirX;
	public static int newDirX = 0;
	static Random rnd = new Random();

	public Ball(Icon image) {
		super(image);
	}

	public Ball() {
		this.dirX = 0;
		this.dirY = -1;
		this.ballLabel = new Ball(Game.playBall);
		this.ballLabel.setBounds(Game.player.getX()+65, Game.player.getY()-20, 20, 19);
		Game.cont.add(this.ballLabel, (Integer)1);
		Block.balls.add(this);
	}

	public static void ballMovement() {
		if (Block.balls.size() == 0) {
			Game.death();
		}
		for(Ball ball : Block.balls) {
			ballXY(ball);
			if (ball.ballLabel.getBounds().intersects(Game.player.getBounds())) {
				ball.dirY = -1;
				ball.dirX = newDirX;
			}
			if (ballX > Game.screenWidth - ball.ballLabel.getWidth() - Game.borderBounds || ballX < 0 + Game.borderBounds) {
				ball.dirX *= -1;
			}
			if (ballY < 0 + Game.borderBounds) {
				ball.dirY *= -1;
			}
			if (ballY > 800 - ball.ballLabel.getHeight()) {
				if(Mouse_Listener.endlessState.equals("off") && Block.breakableBlockCount == 0) {
					ball.dirY *= -1;
					int a = 1 - rnd.nextInt(3);
					ball.dirX = a;
					break;
				}
				Game.cont.remove(ball.ballLabel);
				ball.dirX *= 0;
				ball.dirY *= 0;
				Block.balls.remove(ball);
				break;
			}
		}
		
	}

	public static void ballXY(Ball ball) {
		ballX = ball.ballLabel.getX() + (3 * ball.dirX);
		ballY = ball.ballLabel.getY() + (5 * ball.dirY);
		Block.blockHit();
		if (Game.start == 1) {
			ball.ballLabel.setLocation(ballX, ballY);
		}
	}
}
