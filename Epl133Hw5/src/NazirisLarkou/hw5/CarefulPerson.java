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
		super(x, y);
		this.vulnerability = .33;
		this.mobility = .8;
	}

	/**
	 * Draws the person
	 */
	public void draw(double doubleH, double doubleW) {
		StdDraw.picture((x * doubleW) + (doubleW / 2), (y * doubleH) + (doubleH / 2), "Careful.png", doubleW, doubleH);
	}
}
