package Main;

import java.awt.event.*;

public class MyKeyListener implements KeyListener{
	static boolean pressed;

	public void keyPressed(KeyEvent e) {
		if (Game.pressed == false) {
			int keyPressed = e.getKeyCode();
			Game.keyPress(keyPressed, 1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int keyRelesed = e.getKeyCode();
		Game.keyPress(keyRelesed, 0);
	}

	public void keyTyped(KeyEvent e) {}

}
