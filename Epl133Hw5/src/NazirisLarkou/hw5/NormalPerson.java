package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a normal person
 *
 * @author Konstantinos Larkou
 */
public class NormalPerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the int X position
   * @param y the int Y position
   */
  public NormalPerson(int x, int y) {
    super(x, y);
    NormalPerson.vulnerability = .66;
    NormalPerson.mobility = .7;
  }

  /**
   * Draws the person
   */
  public void draw(double doubleH, double doubleW) {
	  ;
  }
}
