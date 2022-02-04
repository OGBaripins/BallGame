package Screens;

import javax.swing.*;
import Main.Game;

public class Options {
	public static JLabel backGround;
	public static JLayeredPane cont;
	public static JFrame frame = new JFrame("Brick Breaker Options");
	static int screenHeight = 1080, screenWidth = 1920;
	
	public Options(){
		setFrame();
		
		new Button("power_on", 700, 340, 500, 80, cont);
		new Button("endless_off", 700, 460, 500, 80, cont);
		new Button("diff_easy", 700, 580, 500, 80, cont);
		new Button("back", 700, 700, 500, 80, cont);
	}
	
	void setFrame(){
		frame.setSize(Game.screenSize.width,Game.screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		backGround = new JLabel (Game.otherBackground);
		backGround.setSize(Game.screenSize.width,Game.screenSize.height);
		
		cont = new JLayeredPane();
		cont.setBounds(0, 0, screenWidth, screenHeight);
		frame.add(cont);
		cont.add(backGround, (Integer) 0);
	}
}
