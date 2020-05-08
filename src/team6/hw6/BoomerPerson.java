package team6.hw6;

/**
 * A class representing a boomer(old person)
 *
 * @author Andreas Naziris
 * @author Konstantinos Larkos
 */
public class BoomerPerson extends Person {
	{
		type = "Boomer";
	}

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public BoomerPerson(double x, double y, char grid) {
		super(x, y, grid, 0.5, 1);
	}
}
