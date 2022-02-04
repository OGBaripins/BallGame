package Screens;

import javax.swing.*;
import Main.Game;


public class Main_Menu {
	
	public static JLabel backGround;
	public static JLayeredPane cont;
	public static JFrame frame = new JFrame("Brick Breaker Main Menu");
	static int screenHeight = 1080, screenWidth = 1920;
	
	public Main_Menu(){
		setFrame();
		
		new Button("start", 700, 340, 500, 80, cont);
		new Button("option", 700, 460, 500, 80, cont);
		new Button("exit", 700, 580, 500, 80, cont);
		
	}
	
	void setFrame(){
		frame.setSize(Game.screenSize.width,Game.screenSize.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		backGround = new JLabel (Game.gameNotOver);
		backGround.setSize(Game.screenSize.width,Game.screenSize.height);
		
		cont = new JLayeredPane();
		cont.setBounds(0, 0, screenWidth, screenHeight);
		frame.add(cont);
		cont.add(backGround, (Integer) 0);
	}
}
