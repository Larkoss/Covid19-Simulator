package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing an immune to the disease person
 *
 * @author Konstantinos Larkou
 */
public class ImmunePerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the double X position
   * @param y the double Y position
   */
  public ImmunePerson(int x, int y) {
    super(x, y);
    this.vulnerability = 0;
    this.mobility = .7;
  }

  /**
   * Draws the person
   */
  public void draw(double doubleH, double doubleW) {
		StdDraw.picture((x * doubleW) + (doubleW / 2), (y * doubleH) + (doubleH / 2), "Immune.png", doubleW, doubleH);
  }
}
