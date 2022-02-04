package Block;

import javax.swing.*;
import Main.Ball;

public class Extra_Balls extends Block {
	
	private static final long serialVersionUID = 1L;
	Icon image;
	public static ImageIcon powerUpIcon = (new ImageIcon("powerUp_Icons\\ball_power.png"));
	public static ImageIcon bigPowerUpIcon = (new ImageIcon("powerUp_Icons\\big_ball_power.png"));
	
	
	Extra_Balls(Icon image){
		super(image);
	}
	
	public static void power() {
		new Ball();
	}
}
