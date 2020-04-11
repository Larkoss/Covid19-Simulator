package NazirisLarkou.hw5;

public abstract class Person {
	int x, y;
	boolean infected = false;
	static double mobility;
	static double vulnerability;

	private final static double groundPossibility = 0.4;
	private final static double personPossibility = 0.75;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
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
	abstract void draw();

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
			newPossibility = groundPossibility * vulnerability;
			random = Math.random();
			if (random > newPossibility)
				return true;
		}

		for (int i = 0; i < numNeighboursInfected; i++) {
			newPossibility = personPossibility;
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
