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
  public void draw(double doubleH, double doubleW) {
	  StdDraw.picture((x*doubleW)+(doubleW/2), (y*doubleH)+(doubleH / 2), "Immune.png", doubleW, doubleH);
  }
}
