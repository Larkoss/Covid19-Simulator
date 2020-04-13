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
  public void draw(double doubleH, double doubleW) {

	  StdDraw.picture((x*doubleW)+(doubleW/2), (y*doubleH)+(doubleH / 2), "Boomer.png", doubleW, doubleH);
  }
 
  
}
