package NazirisLarkou.hw5;
import edu.princeton.cs.introcs.StdDraw;

/**
 * General abstract that represents a person
 * 
 * @author Konstantinos Larkou
 *
 */
public abstract class Person {
	private double x, y;
	private boolean infected = false;
	private double mobility;
	private double vulnerability;

	private final static double groundPossibility = 0.4;
	private final static double personPossibility = 0.75;

	protected String type;

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x2 the double X position
	 * @param y2 the double Y position
	 */
	public Person(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Person(double x, double y, double mobility, double vulnerability) {
		this.x = x;
		this.y = y;
		this.mobility = mobility;
		this.vulnerability = vulnerability;
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
	 * Setter for x and y.
	 * @param x the double x coordinate
	 * @param y the double y coordinate
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	private void drawImg(double doubleH, double doubleW, String name) {
		StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), name + ".png", doubleW, doubleH);
	}

	/**
	 * Abstract method that draws the person on the grid
	 */
	public void draw(double doubleH, double doubleW, boolean isCellInfected) {
		String name = type;

		if(this.infected) {
			name += "Infected";
			if(isCellInfected) {
				this.drawImg(doubleH, doubleW, name + "Both");
			} else {
				this.drawImg(doubleH, doubleW, name);
			}
		} else {
			if(isCellInfected) {
				this.drawImg(doubleH, doubleW, name + "InfectedFloor");
			} else {
				this.drawImg(doubleH, doubleW, name);
			}
		}
	}

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
			if (random < newPossibility)
				return true;
		}

		for (int i = 0; i < numNeighboursInfected; i++) {
			newPossibility = Person.personPossibility * this.vulnerability;
			random = Math.random();
			if (random < newPossibility)
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
	public void infect(boolean isGroundInfected, int numNeighboursInfected) {
		if (this.shouldGetInfected(isGroundInfected, numNeighboursInfected)) {
			this.infected = true;
		}
	}
	
	public boolean getIsInfected() {
		return this.infected;
	}
}
