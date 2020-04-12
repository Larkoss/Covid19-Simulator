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
	  StdDraw.setPenColor(StdDraw.BLACK);
	  StdDraw.circle(this.x, this.y, 0.5);
  }
}
