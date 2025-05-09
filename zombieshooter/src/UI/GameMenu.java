package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Input.KeyHandler;
import Main.GamePanel;

public class GameMenu extends JPanel{
	GamePanel gp;
	public KeyHandler keyH;
	public String gameState = "menu";
	
	JButton startBtn = new JButton("Start");
    JButton resumeBtn = new JButton("Resume");
    JButton restartBtn = new JButton("Restart");
    JButton quitBtn = new JButton("Quit");
	public GameMenu(GamePanel gp,KeyHandler keyH) {
		this.gp = gp;
		this.keyH= keyH;
        setLayout(new GridLayout(4, 1, 10, 10));
        setBounds(150, 100, 200, 200);
        setOpaque(false);

        add(startBtn);
        add(resumeBtn);
        add(restartBtn);
        add(quitBtn);
        
        startBtn.addActionListener(e -> {
            gameState = "playing";
            gp.remove(this);
            gp.requestFocusInWindow();
        });

        resumeBtn.addActionListener(e -> {
            gameState = "playing";
            gp.remove(this);
            gp.requestFocusInWindow();
        });

        restartBtn.addActionListener(e -> {
            gp.restartGame();
            gameState = "playing";
            gp.remove(this);
            gp.requestFocusInWindow();
        });

        quitBtn.addActionListener(e -> System.exit(0));
        
	}
	public void refreshButtons(String state) {
	    if (state.equals("menu")) {
	        startBtn.setVisible(true);
	        resumeBtn.setVisible(false);
	        restartBtn.setVisible(false);
	    } else if (state.equals("paused")) {
	        startBtn.setVisible(false);
	        resumeBtn.setVisible(true);
	        restartBtn.setVisible(true);
	    } else if (state.equals("gameover")) {
	        startBtn.setVisible(false);
	        resumeBtn.setVisible(false);
	        restartBtn.setVisible(true);
	    }
	}

}
