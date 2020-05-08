package team6.hw6;

/**
 * A class representing a careful person
 *
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 */
public class CarefulPerson extends Person {
	{
		type = "Careful";
	}

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public CarefulPerson(int x, int y, char grid) {
		super(x, y, grid, 0.7, 0.33);
	}
}
