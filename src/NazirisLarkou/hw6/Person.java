package NazirisLarkou.hw6;
import edu.princeton.cs.introcs.StdDraw;

/**
 * General abstract that represents a person
 * 
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 *
 */
public abstract class Person {
	private double x, y;
	private char grid;
	private boolean infected = false;
	private double mobility;
	private double vulnerability;

	private static double groundPossibility = 0.4;
	private static double personPossibility = 0.75;

	protected String type;

	/**
	 * Class constructor specifying x and y
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 * @param grid the char grid id the person belongs to
	 */
	public Person(double x, double y, char grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
	}
	
	/**
	 * Class constructor specifying x, y, mobility and vulnerability.
	 * 
	 * @param x the double X position
	 * @param y the double Y position
	 * @param grid the char grid id the person belongs to
	 * @param mobility the double mobility
	 * @param vulnerability the double vulnerability
	 */
	public Person(double x, double y, char grid, double mobility, double vulnerability) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		this.mobility = mobility;
		this.vulnerability = vulnerability;
	}

	/**
	 * Setter for probability of infection from the ground.
	 * @param possibility possibility of infection from the ground
	 */
	public static void setGroundPossibility(double possibility) {
		Person.groundPossibility = possibility;
	}

	/**
	 * Setter for probability of infectino from another person.
	 * @param possibility probability of infection from another person.
	 */
	public static void setPersonPossibility(double possibility) {
		Person.personPossibility = possibility;
	}

	/**
	 * Getter for X coordinate of person
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Getter for Y coordinate of person
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Getter for grid id
	 * @return the grid id
	 */
	public double getGrid() {
		return grid;
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

	/**
	 * Setter for grid.
	 * @param grid the char grid id
	 */
	public void setGrid(char grid) {
		this.grid = grid;
	}

	/**
	 * Setter for whether the person is infected or not
	 * @param infected
	 */
	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	private void drawImg(double doubleH, double doubleW, String name) {
		StdDraw.picture((this.getX() * doubleW) + (doubleW / 2), (this.getY() * doubleH) + (doubleH / 2), name + ".png", doubleW, doubleH);
	}

	/**
	 * Draws person.
	 * @param doubleH the width of the person on the canvas
	 * @param doubleW the height of the person on the canvas
	 * @param isCellInfected is the cell beneath the person infected
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
	 * Method that sets infected based on whether person should get infected.
	 * 
	 * @param isGroundInfected is the ground ifected
	 * @param numNeighboursInfected how many ifected neighbours the person has
	 */
	public void infect(boolean isGroundInfected, int numNeighboursInfected) {
		if (this.shouldGetInfected(isGroundInfected, numNeighboursInfected)) {
			this.infected = true;
		}
	}
	
	/**
	 * Getter for isInfected
	 * @return is infected
	 */
	public boolean getIsInfected() {
		return this.infected;
	}
}
