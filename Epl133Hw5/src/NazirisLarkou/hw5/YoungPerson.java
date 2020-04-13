package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a youngster
 *
 * @author Konstantinos Larkou
 */
public class YoungPerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the int X position
   * @param y the int Y position
   */
  public YoungPerson(int x, int y) {
    super(x, y);
    YoungPerson.vulnerability = .33;
    YoungPerson.mobility = .8;
  }

  /**
   * Draws the person
   */
  public void draw(double doubleH, double doubleW) {
	  ;
  }
}
