package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Main.GamePanel;

public class Zombie {
	public BufferedImage zombie;
    public int zombieX;
	public int zombieY;
    public int zombieSize = 70;
    public double zombieSpeed = 4.0;
    int playerX,playerY;
    public ArrayList<Zombie> zombies = new ArrayList<>();
    GamePanel gp;
    
 //   double zombieMaxSpeed = 5.0;
//    double speedIncrement = 0.05;
    
    Random rand = new Random();
    int spawnCounter = 0;
    public Zombie(GamePanel gp) {
    	this.gp= gp;
    	getZombieImage();
    }
    public Zombie(int x, int y) {
        this.zombieX = x;
        this.zombieY = y;
        this.getZombieImage();
    }
    public void getZombieImage() {
    	try {
    		
			zombie = ImageIO.read(getClass().getResourceAsStream("/zed/teemo.png"));
			 System.out.println("Ảnh zombie đã load.");
		} catch (IOException e) {
			System.out.println("Khong the load");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
	        System.out.println("Ảnh zombie KHÔNG tồn tại ở đường dẫn!");
	    }
    }
    public void update(int playerX,int playerY) {
    	this.playerX= playerX;
    	this.playerY= playerY;
    	spawnCounter++;
    	if (spawnCounter > 30) {
            zombies.add(spawnZombie());
            spawnCounter = 0;
        }
    	for (Zombie z : zombies) {
    		z.updatePlayerPosition(playerX, playerY);
            z.moveTowards(playerX, playerY);
        }
    }
    private Zombie spawnZombie() {
        int side = rand.nextInt(4);
        int x = 0, y = 0;

        switch (side) {
            case 0: x = rand.nextInt(gp.GAME_WIDTH); y = -30; break; // top
            case 1: x = gp.GAME_WIDTH + 30; y = rand.nextInt(gp.GAME_HEIGTH);break; // right
            case 2: x = rand.nextInt(gp.GAME_WIDTH); y = gp.GAME_HEIGTH + 30; break; // bottom
            case 3: x = -30; y = rand.nextInt(gp.GAME_HEIGTH); break; // left
        }

        return new Zombie(x, y);
    }
    public void updatePlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void moveTowards(int playerX, int playerY) {
 /*       // Tăng tốc độ dần dần theo thời gian
        if (zombieSpeed < zombieMaxSpeed) {
            zombieSpeed += speedIncrement;
        }
*/
        // Cách tính di chuyển zombie
        double dx = playerX - zombieX; 
        double dy = playerY - zombieY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            zombieX += (dx / distance) * zombieSpeed;
            zombieY += (dy / distance) * zombieSpeed;
        }
    }
    public void clear() {
    	zombies.clear();
    }
    public void draw(Graphics2D g2) {
    	Graphics2D g2Rotated = (Graphics2D) g2.create();
    	double zombieDx = playerX   - (zombieX + zombieSize / 2);
    	double zombieDy = playerY  - (zombieY + zombieSize / 2);
    	double zombieAngle = Math.atan2(zombieDy, zombieDx);
    	

    	// Di chuyển hệ trục về giữa zombie
    	g2Rotated.translate(zombieX + zombieSize / 2, zombieY + zombieSize / 2);
        g2Rotated.rotate(zombieAngle);

    	// Vẽ zombie (ở đây là hình chữ nhật – có thể thay bằng ảnh sau)
    //	g2.setColor(Color.GREEN);
   // 	g2.fillRect(-zombieSize / 2, -zombieSize / 2, zombieSize, zombieSize);
        g2Rotated.drawImage(zombie, -zombieSize / 2, -zombieSize / 2, zombieSize, zombieSize, null);
    	// Nếu bạn có ảnh zombie: drawImage(...) thay cho fillRect

    	g2Rotated.dispose();
    }
}
