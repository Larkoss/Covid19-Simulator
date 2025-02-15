package team6.hw6;

/**
 * A class representing an immune to the disease person
 *
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 */
public class ImmunePerson extends Person {
	{
		type = "Immune";
	}

	/**
	 * Class constructor specifying x and y
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public ImmunePerson(int x, int y, char grid) {
		super(x, y, grid, 0.8, 0);
	}
}
