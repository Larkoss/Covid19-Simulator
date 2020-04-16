package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a boomer(old person)
 *
 * @author Andreas Naziris
 */
public class BoomerPerson extends Person {
	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public BoomerPerson(double x, double y) {
		super(x, y, 0.5, 1);
	}

	/**
	 * Draws the person
	 */
	public void draw(double doubleH, double doubleW) {
		if(this.getIsInfected())
			StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "BoomerInfected.png", doubleW, doubleH);
		else
			StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), "Boomer.png", doubleW, doubleH);
	}

}
