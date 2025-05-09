package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean up, down, left,right ,space;
	public boolean skillE;
	public boolean skillQ;
	public boolean pause;
	public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:{
            	up = true; break;
            }
            case KeyEvent.VK_S :{
            	down = true;break;
            }
            case KeyEvent.VK_A :{
            	left = true;break;
            }
            case KeyEvent.VK_D:{
            	right = true;break;
            }	
            
	        case KeyEvent.VK_P:{
				pause = true;break;
	        }
            case KeyEvent.VK_SPACE:{
            	space= true;break;
            }
            case KeyEvent.VK_E:{
            	skillE=true;break;
            }
            case KeyEvent.VK_Q:{
            	skillQ=true;break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
	        case KeyEvent.VK_W:{
	        	up = false; break;
	        }
	        case KeyEvent.VK_S :{
	        	down = false;break;
	        }
	        case KeyEvent.VK_A :{
	        	left = false;break;
	        }
	        case KeyEvent.VK_D:{
	        	right = false;break;
	        }
	        
	        case KeyEvent.VK_P:{
				pause = false;break;
	        }
	        case KeyEvent.VK_SPACE:{
	        	space= false;break;
	        }
	        case KeyEvent.VK_E:{
	        	skillE=false;break;
	        }
            case KeyEvent.VK_Q:{
            	skillQ=false;break;
            }
        }
    }

    public void keyTyped(KeyEvent e) {}
    

}
