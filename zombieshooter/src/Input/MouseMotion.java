package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Main.GamePanel;

public class MouseMotion implements MouseListener,MouseMotionListener{
	GamePanel gp;
	public MouseMotion(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		gp.mouseX = e.getX();
        gp.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		/*    	//ban bang chuot
        mouseX = e.getX();
        mouseY = e.getY();
        bullets.add(new Bullet(playerX + playerSize / 2, playerY + playerSize / 2, mouseX, mouseY)); 
        */
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		gp.targetX= e.getX();
    	gp.targetY= e.getY();
    	gp.movingToTarget = true;
//    	shootBullet();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
