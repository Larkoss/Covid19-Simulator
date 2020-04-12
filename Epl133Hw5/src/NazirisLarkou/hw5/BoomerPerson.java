package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a Boomer(old person)
 *
 * @author Andreas Naziris
 */
public class BoomerPerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the int X position
   * @param y the int Y position
   */
  public BoomerPerson(double x, double y) {
    super(x, y);
    BoomerPerson.vulnerability = 1.0;
    BoomerPerson.mobility = .5;
  }

  /**
   * Draws the person
   */
  public void draw() {
	  int n = 5;
	  int newn = 5 *100;
	  StdDraw.setCanvasSize(newn, newn);
	  StdDraw.setPenColor(StdDraw.RED);
	  StdDraw.filledCircle(this.x, this.y, newn / 1000.0);
	  double x1, y1;
	  for(double i = 0; i< 2 * Math.PI; i+= (Math.PI / 4)) {
		  x1 = x + 0.2 * Math.sin(i);
		  y1 = y + 0.2 * Math.cos(i);
		  StdDraw.line(x, y, x1, y1);
		  StdDraw.filledCircle(x1, y1, 0.01);
	  }
  }
  
  public static void main(String args[]) {
	  System.out.println("Hello World1");
	  BoomerPerson b = new BoomerPerson(0.5, 0.5);
	  b.draw();
  }
  
}
