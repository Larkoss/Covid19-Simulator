package NazirisLarkou.hw5;

/**
 * A class representing a boomer(old person)
 *
 * @author Andreas Naziris
 */
public class BoomerPerson extends Person {
	static {
		type = "Boomer";
	}

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public BoomerPerson(double x, double y) {
		super(x, y, 0.5, 1);
	}
}
