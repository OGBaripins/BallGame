package Screens;

import java.util.*;
import javax.swing.*;

public class Button extends JLabel {
	
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	private static final long serialVersionUID = 1L;
	static JLabel button;
	ImageIcon[] buttonIcons = new ImageIcon[3];
	static ImageIcon onPress = new ImageIcon("Buttons\\"+"on_press.png");
	String name;
	boolean onIt;

	Button(String name, int x, int y, int width, int height, JLayeredPane cont) {
		this.name = name;
		this.onIt = false;
		this.setBounds(x, y, width, height);
		this.buttonIcons[0] = new ImageIcon("Buttons\\"+name+".png"); 
		this.buttonIcons[1] = new ImageIcon("Buttons\\"+name+"_on_it.png");
		this.buttonIcons[2] = onPress;
		this.setIcon(buttonIcons[0]);
		this.setVisible(true);
		buttons.add(this);
		new Mouse_Listener (this);
		cont.add(this ,(Integer)1);
	}
	
	void changeIcon(int i, boolean onIt){
		this.setIcon(buttonIcons[i]);
		this.onIt = onIt;
	}
}
