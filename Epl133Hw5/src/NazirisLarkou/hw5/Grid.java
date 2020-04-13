package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing the grid
 * 
 * @author Konstantinos Larkou
 *
 */
public class Grid {
	
	
	private double doubleH; // Percentage the height of the box is according to canvas
							// The length of height, of a single square
	private double doubleW; // Percentage the width of the box is according to canvas
							// The length of width, of a single square

	{ // Instance Initialization Block
		StdDraw.setCanvasSize(700, 700);
	}

	/**
	 * Constructor that initializes the static doubles
	 * 
	 * @param h Y-axis
	 * @param w X-axis
	 */
	public Grid(int h, int w) {
		this.doubleH = 1.0 / h;

		this.doubleW = 1.0 / w;
	}

	/**
	 * @return the doubleH
	 */
	public double getDoubleH() {
		return doubleH;
	}

	/**
	 * @return the doubleW
	 */
	public double getDoubleW() {
		return doubleW;
	}

  /**
   * Draw the grid.
   */
	public void drawGrid() {
    // Draws horizontal lines
    for (double j = 0; j < 1; j += doubleH)
      StdDraw.line(0, j, 1, j);

    // Draws vertical lines
    for (double i = 0; i < 1; i += doubleW)
      StdDraw.line(i, 0, i, 1);
	}
}
