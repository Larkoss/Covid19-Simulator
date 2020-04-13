package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a youngster
 *
 * @author Konstantinos Larkou
 */
public class CarefulPerson extends Person {
  /**
   * Class constructor specifying x and y
   * @param x the int X position
   * @param y the int Y position
   */
  public CarefulPerson(int x, int y) {
    super(x, y);
    CarefulPerson.vulnerability = .33;
    CarefulPerson.mobility = .8;
  }

  /**
   * Draws the person
   */
  public void draw(double doubleH, double doubleW) {
	  StdDraw.picture((x*doubleW)+(doubleW/2), (y*doubleH)+(doubleH / 2), "Careful.png", doubleW, doubleH);
  }
}
