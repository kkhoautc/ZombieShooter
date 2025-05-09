package Main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.Bullet;
import Entity.Player;
import Entity.Zombie;
import Input.KeyHandler;
import Input.MouseMotion;
import UI.GameMenu;
import UI.UI;

public class GamePanel extends JPanel implements ActionListener {
    public int GAME_WIDTH = 1000;
    public int GAME_HEIGTH =600;
	Timer timer;
	//Anh nen
	BufferedImage background;
	// Score 
	public int Score= 0;
    //Move */
    public int targetX;
	public int targetY;
    public boolean movingToTarget = false;
    
    //pause game
    boolean isPaused = false;
//    boolean up, down, left,right ,space,skillE;
    public int mouseX = 400; // mặc định ở giữa
	public int mouseY = 300;
    
    //KeyHandler,mouse
	MouseMotion mouseMotion = new MouseMotion(this);
    KeyHandler keyHandler = new KeyHandler();
    
    //GameMenu
    public GameMenu menu = new GameMenu(this,keyHandler);
   
    // game UI
    public UI gameUI = new UI(this);
    //Player
    public Player player = new Player(this,keyHandler);
    
    //Zombie
    Zombie zombie = new Zombie(this);
    ArrayList<Bullet> bullets = new ArrayList<>();
    Random rand = new Random();
    int spawnCounter = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGTH));
        this.addKeyListener(keyHandler);
        
        //set Background
        getBackgroundImage();
        this.setFocusable(true);
        requestFocusInWindow();
        
        this.addMouseListener(mouseMotion);
        this.addMouseMotionListener(mouseMotion);
        
        //set game menu
        setLayout(null);
        menu.refreshButtons("menu");
        this.add(menu);
        
        timer = new Timer(1000/60, this); // 60fps
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    		if(menu.keyH.pause==true) {
    			menu.gameState = "paused";
    		}
    		if(player.isAlive==true&&menu.gameState.equals("playing")) {
	    		player.update();
	    		
	    		zombie.update(player.playerX,player.playerY);
		        for (Bullet b : bullets) {
		            b.update();
		        }
		        checkCollisions();
		        checkZombieCollision();
		
		        repaint();
    		}
    		else if(player.isAlive==true && menu.gameState.equals("menu")){
    			menu.refreshButtons("menu");
    			add(menu);
    			repaint();
    		}
    		else if(player.isAlive==true && menu.gameState.equals("paused")) {
    			menu.refreshButtons("paused");
    			add(menu);
    			repaint();
    		}
    		else{
    			menu.gameState = "gameover";
    			menu.refreshButtons("gameover");
    			add(menu);
    			repaint();
    		}
    }
    public void getBackgroundImage() {
    	try {
			background = ImageIO.read(getClass().getResourceAsStream("/background/screen.png"));
			System.out.println("Anh nen da load");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void checkZombieCollision() {
    	Iterator<Zombie> zombieIterator = zombie.zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie z = zombieIterator.next();
            double dx = (player.playerX - z.zombieX);
            double dy = (player.playerY - z.zombieY);
            double distance = Math.sqrt(dx * dx + dy * dy);
            if(distance <= player.playerSize/2) {
            	player.playerLive -=1;
            	System.out.println("Live = " + player.playerLive);
            	if(player.playerLive==0) {
            		player.isAlive = false;
            	}
            	zombieIterator.remove();
            	
            }
        }  
    }
    private void checkCollisions() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();

            Iterator<Zombie> zombieIterator = zombie.zombies.iterator();
            while (zombieIterator.hasNext()) {
                Zombie z = zombieIterator.next();

                double dx = (b.x - z.zombieX);
                double dy = (b.y - z.zombieY);
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < z.zombieSize / 2 + b.size / 2) {
                    zombieIterator.remove();
                    bulletIterator.remove();
                    Score +=10;
                    break;
                }
            }
        }
    }
    public void restartGame() {
        player.reset();
        zombie.clear();
        bullets.clear();
        Score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        
        	//ve background
            g2d.drawImage(background, 0	, 0,GAME_WIDTH,GAME_HEIGTH, null);
            
          //ve gameUI
            gameUI.draw(g2d);
            
            // ve nhan vat
    	    player.draw(g2d);
    	    
    	     //ve zombie
            for (Zombie z : zombie.zombies) {
            	Graphics2D g2z = (Graphics2D) g.create();
            	z.draw(g2z);
            }
            // ve dan
            for (Bullet b : bullets) {
                b.draw(g);
            }
    }
    public void shootBullet() {
    	bullets.add(new Bullet(player.playerX + player.playerSize / 2, player.playerY + player.playerSize / 2, mouseX, mouseY));
    }

	

    /*
    public void mouseClicked(MouseEvent e) {
    	//ban bang chuot
        mouseX = e.getX();
        mouseY = e.getY();
        bullets.add(new Bullet(playerX + playerSize / 2, playerY + playerSize / 2, mouseX, mouseY)); 
        
    }
   
    @Override 
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }
    
    @Override
    public void mouseDragged(MouseEvent e) { 
    }

    public void mousePressed(MouseEvent e) {
    	targetX= e.getX();
    	targetY= e.getY();
    	movingToTarget = true;
//    	shootBullet();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

   */

    

}