package Main;

import javax.swing.JFrame;

public class GameManager {
	JFrame frame = new JFrame("Zombie Shooter (Swing)");
    public GameManager(){
    //	ZombieShooterGame gp = new ZombieShooterGame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GamePanel());
  //      frame.add(gp);
        frame.pack();
        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
  //      gp.launchGame();
    }
    public void start() {
    	frame.setVisible(true);
    }
}