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
  public BoomerPerson(int x, int y) {
    super(x, y);
    BoomerPerson.vulnerability = 1.0;
    BoomerPerson.mobility = .5;
  }

  /**
   * Draws the person
   */
  public void draw() {
	  StdDraw.setCanvasSize(1, 1);
	  StdDraw.setPenColor(StdDraw.GREEN);
	  StdDraw.circle(this.x, this.y, 0.1);
	  double x1, y1;
	  for(double i = 0; i< 2 * Math.PI; i+= (Math.PI / 4)) {
		  x1 = 0.2 * Math.sin(i);
		  y1 = 0.2 * Math.cos(i);
		  StdDraw.line(x, y, x1, y1);
	  }
  }
  
}
