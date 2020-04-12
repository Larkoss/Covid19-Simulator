package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing an Immune to the disease
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
    ImmunePerson.vulnerability = 0;
    ImmunePerson.mobility = .7;
  }

  /**
   * Draws the person
   */
  public void draw() {
	  StdDraw.setPenColor(StdDraw.WHITE);
	  StdDraw.circle(this.x, this.y, 0.5);
  }
}
