import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates the graphical representation of the snake game game object,
 * and updates the game's graphical representation based on
 * the snake's movement.

 */
public class SnakePanel extends JPanel implements ActionListener, KeyListener{

	//The width and height of this panel
    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;

    //Variables that represent the board and objects on the board
    public final static int NUM_ROWS = 10;
    public final static int NUM_COLS = 10;
    private SnakeGame game;
    private Snake snake;
    private Point food;
    private int direction;
    private Point snakeHead;

    //Timer object that specifies how often to repaint canvas
    private Timer timer;
    //Total score in game
    private int score;
    //Current direction of snake's movement on board
    public final static int LEFT=0, UP=1, DOWN=2, RIGHT=3;

    public SnakePanel(int width, int height){
        //Set width and height of maze display
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;

        //Set size of the maze display
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        //Initialize the snake head
        snakeHead = new Point(5,5);
        //Initializes the snake object
        snake = new Snake(snakeHead);
        //Initializes the food object
        food = new Point(1,1);
        //Initialize game object
        game = new SnakeGame(snake, food);
        //Start timer and execute timer event every 500 milliseconds (.5 sec)
        timer = new Timer(500, this);
        timer.start();

        //Starting direction that the snake will move in
        direction = LEFT;
        //Starting score of the game
        score = 0;
    }

    public int getScore(){
        return score;
    }

    /**
     * Generates panel display.
     */
    public void paintComponent(Graphics g)
    {
        //Sets the background color of the panel
        setBackground(Color.BLACK);
        //Paints the background onto the panel
        //This will fill-in the parts you don't draw on
        super.paintComponent(g);
        //Draws the colored squares on the panel
        drawSnakeCells(g);
        drawFoodCell(g);
    }

    /**
     * Draws the snake cells on the game board.
     *
     * @param g, the graphics context on which to draw the grid cells
     */
    private void drawSnakeCells(Graphics g)
    {
        g.setColor(Color.GREEN);
        ArrayList<Point> snakeBody = snake.getBody();

        for (Point point: snakeBody)
        {
            //Draw a rectangle representing the cell on the panel
            g.fillRect((int) point.getX()*(PANEL_HEIGHT/SnakePanel.NUM_ROWS) + 1, (int) point.getY()*(PANEL_WIDTH/SnakePanel.NUM_COLS) + 1, (PANEL_WIDTH/SnakePanel.NUM_COLS - 1), (PANEL_HEIGHT/SnakePanel.NUM_ROWS - 1));
        }

    }

    /**
     * Draws the food cell on the game board.
     *
     * @param g, the graphics context on which to draw the grid cells
     */
    private void drawFoodCell(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect((int)food.getX()*(PANEL_HEIGHT/SnakePanel.NUM_ROWS) + 1, (int)food.getY()*(PANEL_WIDTH/SnakePanel.NUM_COLS) + 1, (PANEL_WIDTH/SnakePanel.NUM_COLS - 1), (PANEL_HEIGHT/SnakePanel.NUM_ROWS - 1));
    }

    /**
     * The timer executes the actionPerformed method every .5 seconds
     * @param, ev is the ActionEvent (timer) that triggers the method
     */
    public void actionPerformed(ActionEvent ev){

        if(ev.getSource() == timer){
          
          Point newHead = game.getNewHead(direction);
          
          if (game.isCollision(newHead)) {
            timer.stop();
          }

          if (game.isFood(newHead)) {
            snake.grow();
            score += 1;
            game.updateFood();
            game.getNewHead(direction);
          }
          
          snake.move(newHead);

          //Sets the text at the top of the game window to the string in ()'s
          ScorePanel.scoreLabel.setText("Score: " + score);
            
          //Redraws the canvas  
          repaint();
            
      }
    }

    /**
    *Changes the direction variable based on which key was last 
    * pressed by the player.
    * @param e is a KeyEvent that represents which key was pressed by the player.
    */
    @Override
    public void keyPressed(KeyEvent e) {
      
      //Key code that represents which key was pressed
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT) {
        direction = 0;
      }
      if (key == KeyEvent.VK_UP) {
        direction = 1;
      }
      if (key == KeyEvent.VK_DOWN) {
        direction = 2;
      }
      if (key == KeyEvent.VK_RIGHT) {
        direction = 3;
      }
    }

    //Ignore the functions below
    //They are required by the interface but we will not used them for this game
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
