package NazirisLarkou.hw5;

/**
 * A class representing a Normal person
 *
 * @author Konstantinos Larkou
 */
public class NormalPerson extends Person {
	static {
		type = "Normal";
	}

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 */
	public NormalPerson(int x, int y) {
		super(x, y, 0.7, 0.66);
	}
}