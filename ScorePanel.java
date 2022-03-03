import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Creates the graphical representation of the snake game board object,
 * and updates the board's graphical representation based on
 * the snake's movement.

 */
public class ScorePanel extends JPanel{

	//The width and height of this panel
    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;

    //Represents board for snake game
    private SnakePanel panel;
    public static JLabel scoreLabel;

    public ScorePanel(int width, int height, SnakePanel panel){
        //Set width and height of maze display
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        this.panel = panel;
        scoreLabel = new JLabel("Score= " + panel.getScore());
        add(scoreLabel);
        //Set size of the maze display
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }
}
