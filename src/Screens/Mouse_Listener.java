package Screens;

import java.awt.event.*;
import javax.swing.*;
import Main.Game;

public class Mouse_Listener implements MouseListener {
	public static String powerState = "on";
	public static String endlessState = "off";
	public static String diffState = "easy";
	
	Mouse_Listener(JLabel button) {
		button.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		((Button) e.getSource()).changeIcon(2, true);
	}

	public void mouseReleased(MouseEvent e) {
		Button button = (Button)e.getSource();
		if (button.onIt == true) {
			if (button.name.equals("start")) {
				if (!Game.frame.isActive()) {
					new Game();
				}
				Game.frame.setVisible(true);
				Main_Menu.frame.setVisible(false);
			}
			if (button.name.equals("option")) {
				if (!Options.frame.isActive()) {
					new Options();
				}
				Options.frame.setVisible(true);
				Main_Menu.frame.setVisible(false);
			}
			if (button.name.equals("exit")) {
				System.exit(0);

			}
			if (button.name.equals("back")) {
				Options.frame.setVisible(false);
				Game.frame.setVisible(false);
				Main_Menu.frame.setVisible(true);

			}
			if (button.name.equals("power_on")) {
				if(powerState.equals("off")) powerState = "on";
				else powerState = "off"; 
				
				button.buttonIcons[0] = new ImageIcon("Buttons\\"+"power_"+powerState+".png"); 
				button.buttonIcons[1] = new ImageIcon("Buttons\\"+"power_"+powerState+"_on_it.png");
				button.buttonIcons[2] = Button.onPress;
			}
			if (button.name.equals("endless_off")) {
				if(endlessState.equals("on")) endlessState = "off";
				else endlessState = "on"; 
				
				button.buttonIcons[0] = new ImageIcon("Buttons\\"+"endless_"+endlessState+".png"); 
				button.buttonIcons[1] = new ImageIcon("Buttons\\"+"endless_"+endlessState+"_on_it.png");
				button.buttonIcons[2] = Button.onPress;
			}
			if (button.name.equals("diff_easy")) {
				if(diffState.equals("easy")) diffState = "medium";
				else if(diffState.equals("medium")) diffState = "hard";
				else diffState = "easy";
				
				button.buttonIcons[0] = new ImageIcon("Buttons\\"+"diff_"+diffState+".png"); 
				button.buttonIcons[1] = new ImageIcon("Buttons\\"+"diff_"+diffState+"_on_it.png");
				button.buttonIcons[2] = Button.onPress;
			}
			if(button.onIt) {
				button.setIcon(button.buttonIcons[1]);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		((Button) e.getSource()).changeIcon(1, true);
	}

	public void mouseExited(MouseEvent e) {
		((Button) e.getSource()).changeIcon(0, false);
	}

	public void mouseClicked(MouseEvent e) {}

}
