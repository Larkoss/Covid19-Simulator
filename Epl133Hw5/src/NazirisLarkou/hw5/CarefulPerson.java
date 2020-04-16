package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a careful person
 *
 * @author Konstantinos Larkou
 */
public class CarefulPerson extends Person {
	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public CarefulPerson(int x, int y) {
		super(x, y, 0.7, 0.33);
	}

	/**
	 * Draws the person
	 */
	public void draw(double doubleH, double doubleW) {
		if(this.getInfected())
			StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "CarefulInfected.png", doubleW, doubleH);
		else
			StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "Careful.png", doubleW, doubleH);
	}
}
