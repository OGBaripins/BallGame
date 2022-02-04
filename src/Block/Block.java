package Block;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import Main.*;
import Screens.Mouse_Listener;

public class Block extends JLabel {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
	public static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	static Random rnd = new Random();
	private static final long serialVersionUID = 1L;
	public static int width, height, spacing, powerUpY, powerUpX;
	public static int breakableBlockCount, blockRows;
	int blockChoice;
	public boolean blockIsHit;
	Rectangle bottomLine, topLine, leftLine, rightLine;
	Block block;
	ImageIcon powerUpIcon;
	Icon image;

	public Block(Icon image) {
		super();
		this.setIcon(image);
		this.image = image;
	}

	public Block() {
		makeBlocks();
	}

	public void makeBlocks() {
		height = 30;
		width = 96;
		spacing = 10;

		if (Mouse_Listener.diffState.equals("easy"))
			blockRows = 6;
		else if (Mouse_Listener.diffState.equals("medium"))
			blockRows = 8;
		else
			blockRows = 13;

		for (int j = 0; j < blockRows; j++) {
			for (int i = 0; i < 25; i++) {
				whatBlock();
				block.setBounds((spacing + 2) + ((width + spacing) * i), spacing + ((height + spacing) * j), width,
						height);
				Game.cont.add(block, (Integer) 1);
				blocks.add(block);
				Game.cont.repaint();
			}
		}
	}

	public static void blockHit() {
		for (Block block : blocks) {
			for (Ball ball : balls) {
				if (ball.ballLabel.getBounds().intersects(block.getTopLine())) {
					onBlockHit(block, ball, -1, false);
				}
				if (ball.ballLabel.getBounds().intersects(block.getBottomLine())) {
					onBlockHit(block, ball, 1, false);
				}
				if (ball.ballLabel.getBounds().intersects(block.getLeftLine())) {
					onBlockHit(block, ball, -1, true);
				}
				if (ball.ballLabel.getBounds().intersects(block.getRightLine())) {
					onBlockHit(block, ball, 1, true);
				}
			}
		} 
		if (Mouse_Listener.endlessState.equals("on") && breakableBlockCount == 0) {
			Catch.onPlayer();
			Catch.power(true);
			blocks.clear();
			new Block();
			Game.gameScore += breakableBlockCount;
		}
		
	}

	static void onBlockHit(Block block, Ball ball, int ballDir, boolean x) {
		if (block.blockIsHit == false && block.blockChoice != 2) {
				if (block.blockChoice > 0) {
					new PowerUp(block.blockChoice, block.getX(), block.getY(), block.powerUpIcon);
					Game.gameScore += 10;
				}
				Game.gameScore += 10;
				block.setOpaque(false);
				block.setBounds(0, 0, 0, 0);
				block.blockIsHit = true;
				breakableBlockCount--;
		}
		if (x) {
			ball.dirX = ballDir;
		} else {
			ball.dirY = ballDir;
		}

	}

	public Block whatBlock() {
		int a = rnd.nextInt(5) + 1;
		if (Mouse_Listener.powerState.equals("off"))
			a = 0;
		if (a == 5) {
			int b = rnd.nextInt(4) + 1;
			switch (b) {
			case 1:
				block = new Speed_Up(image);
				return blockConstruct(block, Speed_Up.powerUpIcon, Game.playBlock, b, 1);
			case 2:
				block = new Unbreakable(image);
				return blockConstruct(block, Speed_Up.powerUpIcon, Game.emptyPlayBlock, b, 0);
			case 3:
				block = new Catch(image);
				return blockConstruct(block, Catch.powerUpIcon, Game.playBlock, b, 1);
			default:
				block = new Extra_Balls(image);
				return blockConstruct(block, Extra_Balls.powerUpIcon, Game.playBlock, b, 1);
			}
		}
		block = new Block(image);
		return blockConstruct(block, null, Game.playBlock, 0, 1);
	}

	public static Block blockConstruct(Block block, ImageIcon powerupIcon, ImageIcon blockIcon, int blockChoice, int count) {
		block.setIcon(blockIcon);
		block.powerUpIcon = powerupIcon;
		block.blockChoice = blockChoice;
		breakableBlockCount += count;
		return block;
	}

	Rectangle getTopLine() {
		return new Rectangle(this.getX() + 1, this.getY(), width - 2, 1);
	}

	Rectangle getBottomLine() {
		return new Rectangle(this.getX() + 1, this.getY() + height, width - 2, 1);
	}

	Rectangle getLeftLine() {
		return new Rectangle(this.getX(), this.getY(), 1, height);
	}

	Rectangle getRightLine() {
		return new Rectangle(this.getX() + width - 1, this.getY(), 1, height);
	}
}
