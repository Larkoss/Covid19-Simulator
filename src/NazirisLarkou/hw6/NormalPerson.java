package NazirisLarkou.hw6;

/**
 * A class representing a Normal person
 *
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 */
public class NormalPerson extends Person {
	{
		type = "Normal";
	}

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public NormalPerson(int x, int y, char grid) {
		super(x, y, grid, 0.7, 0.66);
	}
}
