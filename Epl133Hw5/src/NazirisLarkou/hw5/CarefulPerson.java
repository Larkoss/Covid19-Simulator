package NazirisLarkou.hw5;

/**
 * A class representing a careful person
 *
 * @author Konstantinos Larkou
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
	public CarefulPerson(int x, int y) {
		super(x, y, 0.7, 0.33);
	}
}
