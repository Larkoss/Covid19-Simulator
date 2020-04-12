package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing an Immune to disease
 *
 * @author Konstantinos Larkou
 */
public class ImmunePerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the int X position
   * @param y the int Y position
   */
  public ImmunePerson(int x, int y) {
    super(x, y);
    this.vulnerability = 0;
    this.mobility = .75;
  }

  /**
   * Draws the person
   */
  public void draw() {
	  StdDraw.setPenColor(StdDraw.WHITE);
	  StdDraw.circle(this.x, this.y, 0.5);
  }
}
