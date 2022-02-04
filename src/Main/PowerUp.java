package Main;

import javax.swing.*;
import Block.*;

public class PowerUp extends JLabel {
	private static final long serialVersionUID = 1L;

	int powerUpY, powerUpX;
	JLabel powerUpLabel;
	int blockChoice;

	public PowerUp(int blockChoice, int blockX, int blockY, ImageIcon icon) {
		this.powerUpX = blockX + (Block.width / 2);
		this.powerUpY = blockY;
		this.blockChoice = blockChoice;
		this.powerUpLabel = new JLabel(icon);
		this.powerUpLabel.setVisible(false);
		this.powerUpLabel.setBounds(powerUpX, powerUpY, 30, 30);
		Block.powerUps.add(this);
		Game.cont.add(this.powerUpLabel, (Integer) 1);
	}

	public static void powerupMovement() {
		for (PowerUp powerUp : Block.powerUps) {
			powerUp.powerUpLabel.setVisible(true);
			powerUp.powerUpY = powerUp.powerUpY + 3;
			powerUp.powerUpLabel.setLocation(powerUp.powerUpX, powerUp.powerUpY);
			if (powerUp.getY() >= Game.screenHeight - 91 - Game.borderBounds) {
				powerUp.powerUpLabel.setVisible(false);
				Block.powerUps.remove(powerUp);
				break;
			}
			if (powerUp.powerUpLabel.getBounds().intersects(Game.player.getBounds())) {
				collision(powerUp);
				break;
			}
		}
	}

	public static void collision(PowerUp powerUp) {
		if(Game.currentPower.getIcon() != Game.unknownPower) {
			Game.previousPower.setIcon(Game.currentPower.getIcon());
		}
		switch (powerUp.blockChoice) {
		case (1):
			Speed_Up.power(true);
			Game.currentPower.setIcon(Speed_Up.bigPowerUpIcon);
			break;
		case (3):
			Catch.power(true);
			Game.currentPower.setIcon(Catch.bigPowerUpIcon);
			break;
		case (4):
			Extra_Balls.power();
			Game.currentPower.setIcon(Extra_Balls.bigPowerUpIcon);
			break;
		}
		Game.cont.remove(powerUp.powerUpLabel);
		Block.powerUps.remove(powerUp);
	}
	
	public static void killThem() {
		for(PowerUp powerUp: Block.powerUps) {
			Game.cont.remove(powerUp.powerUpLabel);
		}
		Block.powerUps.clear();
	}
}
