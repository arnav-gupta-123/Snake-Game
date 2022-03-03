import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame{

  //Snake object to represent the snake's body
  private Snake snake;
  //Point object variable for food
  private Point food;

  /* Initializes instance variables
  *
  *@param snake is a Snake object representing the snake's body
  *@param food is a Point object representing the food's 
  *locatio on the board
  */
  public SnakeGame(Snake snake, Point food) {
    this.snake = snake;
    this.food = food;
  }

  /*
  * Determines which Point the snake will move to next based on 
  * the last key pressed by the user (LEFT/DOWN/UP/RIGHT).
  * @param direction is an integer value representing the last 
  * key pressed by the user (LEFT/DOWN/UP/RIGHT).
  * @return a Point object that represents where the snake should move next.
  */
  public Point getNewHead(int direction){
    int x = (int) this.snake.getHead().getX();
    int y = (int) this.snake.getHead().getY();
    //left
    if (direction == 0) {
      x -= 1;
    }
    //up
    if (direction == 1) {
      y -= 1;
    }
    //down
    if (direction == 2) {
      y += 1;
    }
    //right
    if (direction == 3) {
      x += 1;
    }
    return new Point(x,y);
  }

  /**
  * Checks to see if a point on the board is food.
  * @param point is a Point on the board
  * @return True if the point has the same coordinates as the food. False otherwise.
  */
  public boolean isFood(Point point){
    if (this.food.equals(point)) {
      return true;
    }
    return false;
  }

  /**
  * Checks to see if a point is outside the boundaries of the board's coordinates.
  * @param point is a Point on the board
  * @return True if the point is out of bounds of the board. False otherwise.
  */
  public boolean isWall(Point point){
    double pointX = point.getX();
    double pointY = point.getY();
    if (pointX < 0 || pointX > 9 || pointY < 0 || pointY > 9) {
      return true;
    }
    return false;
  }

  /**
  * Checks to see if a point is part of the snake's body.
  * @param point is a Point on the board
  * @return True if the point is part of the snake. False otherwise.
  */
  public boolean isSnake(Point point){
    ArrayList<Point> snakeBody = this.snake.getBody();
    for (int i = 0; i < snakeBody.size(); i++) {
      if (snakeBody.get(i).equals(point)) {
        return true;
      }
    }
    return false;
  }

  /**
  * Checks to see if a point is outside of the bounds of the board or part of the snake, i.e. a collision has occurred.
  * @param point is a Point on the board
  * @return True if there is a collision. False otherwise.
  */
  public boolean isCollision(Point point){
    if (isSnake(point) || isWall(point)) {
      return true;
    }
    return false;
  }

  /**
  * Updates the row and col coordinates of the food to a new set
  * of random coordinates that do not intersect with the snake's
  * body.
  */
  public void updateFood(){
    int randCol = (int) (Math.random() * 10);
    int randRow = (int) (Math.random() * 10);
    boolean isPartOfSnake = false;
    this.food.setLocation(randCol, randRow);
    if (isSnake(this.food)) {
      isPartOfSnake = true;
    }
    while (isPartOfSnake) {
      randCol = (int) (Math.random() * 10);
      randRow = (int) (Math.random() * 10);
      this.food.setLocation(randCol, randRow);
      if (isSnake(this.food)) {
      isPartOfSnake = true;
      } 
      else {
        isPartOfSnake = false;
      }
    }
  }
}
