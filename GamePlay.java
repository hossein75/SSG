package newGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	Random R = new Random();
	public int playerX = 150, playerY = 350 ;
	private int bulletX = playerX;
	private int bulletY1 = 350;
	//private int bulletY2 = 370;
	//private int bulletY3 = 390;
	private Timer timer ;
	private int score = 0 ;
	private int delay = 8;
	private boolean play = true;
	private int enemyX = R.nextInt(250)+20, enemyY=-10;
	public boolean fire = false; 
	private boolean attack = false;
	public GamePlay(){
		addKeyListener(this);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	
	
	public void paint(Graphics g) {
		//background 
		g.setColor(Color.black);
		g.fillRect(0, 0, 300, 400);
		
		//ship
		g.setColor(Color.blue);
		g.fillRect(playerX, playerY, 10, 20);
		
		//bullet
		if(fire) {
			g.setColor(Color.blue);
			g.drawRect(bulletX, bulletY1, 2, 3);
			bulletY1 -= 10;
			/*
			g.setColor(Color.red);
			g.drawRect(bulletX, bulletY2, 2, 3);
			bulletY2 -= 10;

			g.setColor(Color.green);
			g.drawRect(bulletX, bulletY3, 2, 3);
			bulletY3 -= 10;
			*/
		}
		
		//enemy
		if(attack) {
			g.setColor(Color.red);
			g.drawOval(enemyX, enemyY, 20, 20);
			enemyY += 2;
		}
		
		//score
		if(play) {
		g.setColor(Color.green);
		g.drawString("score: " +score, 4, 12);
		}
		
		//game over
		if(play == false) {
			attack = false;
			enemyY = 10;
			g.setColor(Color.red);
			g.drawString("Game Over", 75, 150);
			g.setColor(Color.green);
			g.drawString("Final Score " +score, 75, 160);
			g.setColor(Color.white);
			g.drawString("please Click enter ", 100, 200);
		}
		
		
		g.dispose();	
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			attack = true;
			if (playerX >= 290) {
			playerX = 280;
		}else if (playerX < 290) {
			playerX += 10;
		}
	}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 0) {
				playerX = 5;
			}else if (playerX > 0)  {
				playerX -= 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fire = true;
			attack = true;
			if(bulletY1 < 1) {
				bulletX = playerX;
				bulletY1 = 350;
				//bulletY2 = 370;
				//bulletY3 = 390;
				fire = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			play = true;
			attack = true;
		}
		
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(new Rectangle(bulletX, bulletY1, 2, 3).intersects(enemyX, enemyY, 20, 20)) {
			enemyX = R.nextInt(250)+20;
			enemyY=-10;
			score += 2;
		}
		if (enemyY > 350) {
			play = false;
		}
		timer.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		repaint();
	}




	
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

}
