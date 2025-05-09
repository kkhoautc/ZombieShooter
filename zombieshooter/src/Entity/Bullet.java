package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {
    public double x;
	public double y;
    public double vx, vy;
    public int size = 15;
    double speed = 20;
    
    public Bullet(int startX, int startY, int targetX, int targetY) {
        this.x = startX;
        this.y = startY;

        double dx = targetX - startX;
        double dy = targetY - startY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        vx = (dx / distance) * speed;
        vy = (dy / distance) * speed;
    }
    public void update() {
        x += vx;
        y += vy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, size, size);
    }
}

