package NazirisLarkou.hw6;
import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a cell of the grid
 *
 * @author Andreas Naziris
 * @author Konstantinos Larkou
 *
 */
public class Cell {
	private boolean isInfected;
	private int timeInfected;
	private static int disinfectionPeriod;
	private static double infectionPropability;

	/**
	 * Class contructor.
	 */
	public Cell() {
		this.isInfected = false;
	}

	/**
	 * Getter for isInfected.
	 *
	 * Updates infected state and returns isInfected
	 *
	 * @param time int, current time
	 * @return isInfected
	 */
	public boolean getIsInfected(int time) {
		// Update state before returning
		this.updateInfected(false, time);

		return this.isInfected;
	}

	/**
	 * Setter for isInfected.
	 *
	 * Sets isInfected to true and infectionTime to currentTime
	 *
	 * @param time int, current time
	 */
	public void infect(int time) {
		double random = Math.random();

		if(random < Cell.infectionPropability) {
			this.updateInfected(true, time);
		}
	}

	/**
	 * Setter for disinfectionPeriod
	 * @param disinfectionPeriod
	 */
	public static void setDisinfectionPeriod(int disinfectionPeriod) {
		Cell.disinfectionPeriod = disinfectionPeriod;
	}

	/**
	 * Getter for disinfectionPeriod
	 * @return disinfectionPeriod
	 */
	public int getDisinfectionPeriod() {
		return Cell.disinfectionPeriod;
	}

	/**
	 * Setter for infectionPropability
	 * @param infectionPropability
	 */
	public static void setInfectionPropability(double infectionPropability) {
		Cell.infectionPropability = infectionPropability;
	}

	/**
	 * Getter for timeInfected
	 * @return timeInfected
	 */
	public int getTimeInfected() {
		return this.timeInfected;
	}

	/**
	 * Updates isInfected and timeInfected.
	 *
	 * If first parameter == true then isInfected is set to true and timeInfected
	 * is set to the second parameter.
	 * If first parameter == false && timeInfected + disinfectionPeriod smaller
	 * than second parameter then set isInfected to false.
	 * Else do nothing.
	 *
	 * @param isInfected boolean value to update this.isInfected
	 * @param timeInfected int value to update this.timeInfected
	 */
	public void updateInfected(boolean isInfected, int timeInfected) {
		if(isInfected) {
			this.isInfected = true;
			this.timeInfected = timeInfected;
			return;
		}

		if(this.timeInfected + Cell.disinfectionPeriod < timeInfected) {
			this.isInfected = false;
		}
	}

	/**
	 * Draw the cell.
	 * @param x the double x coordinate
	 * @param y the double y coordinate
	 * @param doubleW the double width of the cell
	 * @param doubleH the double height of the cell
	 */
	public void draw(double x, double y, double doubleW, double doubleH) {
		if(this.isInfected) {
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledRectangle((x * doubleW) + (doubleW / 2), (y * doubleH) + (doubleH / 2), doubleW / 2, doubleH / 2);
			StdDraw.setPenColor();
		}
	}
}
