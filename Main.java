import javax.swing.*;
import java.awt.*;

public class Main extends JApplet{

	//SnakePanel object that initializes the snake game's graphical interface
	private SnakePanel snakePanel;
  private ScorePanel scorePanel;

	//The width and height of the panel containing the game
	private static final int PANEL_WIDTH = 500;
  private static final int PANEL_HEIGHT = 500;

	public Main(){
		snakePanel = new SnakePanel(PANEL_WIDTH, PANEL_HEIGHT);
        scorePanel = new ScorePanel(PANEL_WIDTH, 50, snakePanel);
        snakePanel.addKeyListener(snakePanel);
        snakePanel.setFocusable(true);
	}

	public void init(){
		try
        {
            SwingUtilities.invokeAndWait(
                new Runnable()
                {
                    public void run()
                    {
                        getContentPane().setLayout(new BorderLayout());
                        add(snakePanel, BorderLayout.CENTER);
                        add(scorePanel, BorderLayout.NORTH);
                    }
                }
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	/**
	 * Starts maze program
	 * @param args, Main method requires two arguments, the filepath of the maze .txt file
	 * and the filepath of the robot icon file.
	 */
	public static void main(String[] args){

			Main applet = new Main();
		    applet.init();

		    JFrame frame = new JFrame("AP Comp Sci: Snake Game");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.getContentPane().add(applet);
		    frame.pack();
		    frame.setVisible(true);
		    applet.start();
	}

}
