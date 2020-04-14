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
    super(x, y, 0.8, 0);
  }

  /**
   * Draws the person
   */
  public void draw(double doubleH, double doubleW) {
	  	if(infected)
	  		StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "ImmuneInfected.png", doubleW, doubleH);
  		else
  			StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "Immune.png", doubleW, doubleH);
	}

}
