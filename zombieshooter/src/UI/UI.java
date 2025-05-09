package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GamePanel;

public class UI {
	GamePanel gp;
	Font arial_40,arial_60;
	public UI(GamePanel gp) {
		this.gp=gp;
		arial_40 = new Font("Arial",Font.PLAIN,40);
		arial_60 = new Font("Arial",Font.PLAIN,60);
	}
	
	public void draw(Graphics2D g2) {
		if(gp.player.isAlive==false) {
			g2.setFont(arial_60);
			g2.setColor(Color.red);
			String text;
			int textLength;
			int x;
			int y;
			
			text = "Game Over !!!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x= gp.GAME_WIDTH /2 ;
			y = gp.GAME_HEIGTH/2 - 150;
			g2.drawString(text, x, y);
			
			g2.setColor(Color.yellow);
			text = "Score : " + gp.Score;
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x= gp.GAME_WIDTH /2 ;
			y = gp.GAME_HEIGTH/2;
			g2.drawString(text, x, y);
		
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			g2.drawString("Live : "+ gp.player.playerLive, 25, 50);
			g2.drawString("Score : "+ gp.Score,25,100);
		}
	}
}
