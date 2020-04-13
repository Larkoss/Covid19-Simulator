package NazirisLarkou.hw5;

/**
 * General abstract that represents a person
 * 
 * @author Konstantinos Larkou
 *
 */
public abstract class Person {
	double x, y;
	boolean infected = false;
	double mobility;
	double vulnerability;

	private final static double groundPossibility = 0.4;
	private final static double personPossibility = 0.75;

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x2 the double X position
	 * @param y2 the double Y position
	 */
	public Person(double x2, double y2) {
		this.x = x2;
		this.y = y2;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the infected
	 */
	public boolean isInfected() {
		return infected;
	}

	/**
	 * Abstract method that draws the person on the grid
	 */
	public abstract void draw(double doubleH, double doubleW);

	/**
	 * Method that depending on probability decides if a person should move
	 * 
	 * @return true if should move
	 */
	public boolean shouldMove() {
		double random;
		random = Math.random();
		if (random > this.mobility)
			return true;
		return false;
	}

	/**
	 * Method that depending on probability decides if a person should get infected
	 * 
	 * @param isGroundInfected      Is the ground infected?
	 * @param numNeighboursInfected How many infected neighbors you got?
	 * @return true if should move
	 */
	public boolean shouldGetInfected(boolean isGroundInfected, int numNeighboursInfected) {
		double newPossibility;
		double random;

		if (isGroundInfected) {
			newPossibility = Person.groundPossibility * this.vulnerability;
			random = Math.random();
			if (random > newPossibility)
				return true;
		}

		for (int i = 0; i < numNeighboursInfected; i++) {
			newPossibility = Person.personPossibility * this.vulnerability;
			random = Math.random();
			if (random > newPossibility)
				return true;
		}

		return false;
	}

	/**
	 * Method that sets infected.
	 * 
	 * @param isGroundInfected
	 * @param numNeighboursInfected
	 */
	public void GetInfected(boolean isGroundInfected, int numNeighboursInfected) {
		if (this.shouldGetInfected(isGroundInfected, numNeighboursInfected))
			this.infected = true;
	}
}
