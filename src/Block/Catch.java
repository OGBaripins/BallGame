package Block;

import javax.swing.*;
import Main.*;

public class Catch extends Block {

	private static final long serialVersionUID = 1L;
	Icon image;
	public static boolean isCatched;
	public static ImageIcon powerUpIcon = (new ImageIcon("powerUp_Icons\\catch_power.png"));
	public static ImageIcon bigPowerUpIcon = (new ImageIcon("powerUp_Icons\\big_catch_power.png"));

	Catch(Icon image) {
		super(image);
		System.out.println("This is Catch");
	}

	public static void power(boolean start) {
		if (start) {
			isCatched = true;
			Ball.newDirX = 0;
			Game.start = -1;
		}
	}
	public static void onPlayer() {
		for (Ball ball : balls) {
			ball.dirX = Game.dirX;
			ball.ballLabel.setLocation(Game.player.getX() + 70, Game.player.getY() - 20);
		}
		if (Game.start == 1) {
			return;
		}
	}

}
