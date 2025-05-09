package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Input.KeyHandler;
import Main.GamePanel;

public class Player {
	GamePanel gp;
	KeyHandler keyH;
	public int playerLive = 5;
    public boolean isAlive = true;
    public int playerX = 400;
	public int playerY = 300;
    public int playerSize = 100;
    int playerSpeed =6;
    boolean isMoving=false;
    boolean isShooting = false;
    boolean isIdle = false;
 // SkillE dash
    int dashDistance =250;
    long lastDashTime = 0;
    int dashCooldown = 2000;
    //Skill Q shoot
    long lastShootTime=0;
    int shootCooldown = 500;
    //iimage
    public BufferedImage[] moveFrames;
    public BufferedImage shooting;
    int currentFrame = 0;
    int frameCount = 0;
    int frameDelay = 12;
    public BufferedImage player1,player2,player;
    public Player(GamePanel gp,KeyHandler keyH) {
    	this.gp= gp;
		this.keyH = keyH;
		getPlayerImage();
    }
    public void getPlayerImage() {
    	moveFrames = new BufferedImage[6];
    	try {
    		shooting = ImageIO.read(getClass().getResourceAsStream("/player/ezshoot1.png"));
			moveFrames[0] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove1.png"));
			moveFrames[1] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove3.png"));
			moveFrames[2] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove4.png"));
			moveFrames[3] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove5.png"));
			moveFrames[4] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove3.png"));
			moveFrames[5] = ImageIO.read(getClass().getResourceAsStream("/player/ezmove2.png"));
			 System.out.println("Ảnh Player đã load.");
		} catch (IOException e) {
			System.out.println("Khong the load");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
	        System.out.println("Ảnh Player KHÔNG tồn tại ở đường dẫn!");
	    }
}
    public void reset() {
    	 playerLive = 5;
         isAlive = true;
         playerX = 400;
         playerY = 300;
         playerSize = 100;
         playerSpeed =6;
    }
    public void update() {
    	if(playerLive ==0) {
    		isAlive =false;
    	}
        
        frameCount++;
        if (frameCount >= frameDelay) {
            if(isMoving) {
            	currentFrame = (currentFrame + 1) % moveFrames.length;
            }
            if(isIdle) {
            	currentFrame =0;
            }
            frameCount =0;
       }
       
        // Di chuyen nhan vat
        if (gp.movingToTarget==true) {
    		isMoving = true;
    		isIdle = false;  
    	    double dx = gp.targetX - (playerX + playerSize / 2);
    	    double dy = gp.targetY - (playerY + playerSize / 2);
    	    double dist = Math.sqrt(dx * dx + dy * dy);

    	    if (dist > 1) {
    	    	// tốc độ di chuyển
    	        double nx = dx / dist;
    	        double ny = dy / dist;
    	        playerX += (int)(nx * playerSpeed);
    	        playerY += (int)(ny * playerSpeed);
    	    } else {
    	        gp.movingToTarget = false; // đã đến nơi
    	        isMoving = false;
    	        isIdle = true;
    	    }
    	}
    	//Toc Bien
    	if(keyH.skillE) {
        	long now = System.currentTimeMillis();
            if (now - lastDashTime >= dashCooldown) {
                dashTowardsMouse();           // thực hiện dash
                lastDashTime = now;           // cập nhật thời gian lần dùng gần nhất
            } else {
                System.out.println("Đang hồi chiêu E...");
            }
        }
    	//Ban Dan
    	
        if(keyH.skillQ) {
        	long now = System.currentTimeMillis();
            if (now - lastShootTime >= shootCooldown) {
            	isShooting =true;
            	isIdle = false;
            	gp.shootBullet();       
                lastShootTime = now;           // cập nhật thời gian lần dùng gần nhất
            } else {
                System.out.println("Đang hồi chiêu Q...");
                isShooting =false;
            }
        	
        }
    }
    public void draw(Graphics2D g2) {
    	BufferedImage img = null;
    	if(isShooting) {
    		img = shooting;
    	}else{
    		img = moveFrames[currentFrame];
    	}

    	double dx = gp.mouseX - (playerX + playerSize / 2);
	    double dy = gp.mouseY - (playerY + playerSize / 2);
	    double angle = Math.atan2(dy, dx);
	    
	     // Di chuyển hệ trục về giữa nhân vật
	    g2.translate(playerX + playerSize / 2, playerY + playerSize / 2);
	    g2.rotate(angle);
	    g2.drawImage(img,-playerSize / 2, -playerSize / 2, playerSize, playerSize,null);
	    
    }
    public void dashTowardsMouse() {
        double dx = gp.mouseX - (playerX + playerSize / 2);
        double dy = gp.mouseY - (playerY + playerSize / 2);
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist == 0) return; // tránh chia cho 0

        // chuẩn hóa vector
        double nx = dx / dist;
        double ny = dy / dist;

        // cập nhật vị trí
        playerX += (int)(nx * dashDistance);
        playerY += (int)(ny * dashDistance);


    }
    
}
