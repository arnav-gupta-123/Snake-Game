import java.util.ArrayList;
import java.awt.Point;

public class Snake{
  //An ArrayList of Point objects that represents the snake's body
  private ArrayList<Point> body;
 /**
  * Initializes the instance variables of the Snake class.
  * @param head a Point representing the starting positon of the snake's head.
  * Note: You can make your snake start where you would like, and the snake can have any starting size you would like. The board will be a 10 by 10 grid.
  */
	public Snake(Point head){
    //Initialize the body ArrayList
    body = new ArrayList<Point>();
    //adding head to ArrayList
    //body.add(0, head);
    body.add(head);
    Point body2 = new Point((int) head.getX() + 1, (int) head.getY());
    body.add(body2);
	}
	
  /**
  *@return the Point ArrayList that represents the snake's body.
  */
	public ArrayList<Point> getBody(){
		return body;
	}
	
  /**
  *@return the Point that represents the snake's head.
  */
	public Point getHead(){
		return body.get(0);
	}
	
  /**
  *Increases the snake's size by one by adding an extra Point to the end of the list.
  Note: You can do this by duplicating the last point in the list.
  */
	public void grow(){
		int pos = body.size() - 1;
    body.add(new Point(body.get(pos)));
	}
	
  /**
  * Moves the snake one Point on the board.
  *@param The Point that snake is moving to, which will be the snake's new head.
  */
	public void move(Point head){
		body.add(0, head);
    body.remove(body.size()-1);
	}
	
  /**
  * @return A String representation of the snake's body.
  */
	public String toString(){
    String bodyStr = "";
    for(Point point: body){
      bodyStr += point.toString();
    }

    //Alternate loop
    //for(int i = 0; i < body.size(); i++){
      //bodyStr += body.get(i).toString();
    //}
    
    return bodyStr;
	}
	
}
