package Block;

import javax.swing.*;
import Main.Game;

public class Speed_Up extends Block{

	private static final long serialVersionUID = 1L;
	public static int speedUpTime;
	public static ImageIcon powerUpIcon = (new ImageIcon("powerUp_Icons\\speed_power.png"));
	public static ImageIcon bigPowerUpIcon = (new ImageIcon("powerUp_Icons\\big_speed_power.png"));

	Speed_Up(Icon image) {
		super(image);
		System.out.println("This is Speed Up");
	}
	
	public static void power(boolean start){
		if(start) {
			speedUpTime = 500;
			Game.newTimer.start();
		}
		if(speedUpTime > 0) {
			speedUpTime--;
			
		}else {
			Game.newTimer.stop();
		}
	}
}
