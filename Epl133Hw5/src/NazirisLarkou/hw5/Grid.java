package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing the grid
 * 
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 *
 */
public class Grid {
  private Cell[][] cells;

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
	public Grid(int h, int w) { // Constructor
    this.cells = new Cell[h][w];

    // Populate table
    for(int i = 0; i < h; i ++) {
      for(int j = 0; j < w; j ++) {
        this.cells[i][j] = new Cell();
      }
    }

		double hDivider = 100.0 / h;
		this.doubleH = hDivider / 100.0;

		double wDivider = 100.0 / w;
		this.doubleW = wDivider / 100.0;
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
   * Getter for grid area
   * @return grid area
   */
  public int getArea() {
    return this.cells.length * this.cells[0].length;
  }

  /**
   * Getter for if cell is infected
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @param time int, the current time
   * @return is cell infected
   */
  public boolean isCellInfected(int x, int y, int time) {
    return this.cells[y][x].getIsInfected(time);
  }

  /**
   * Infect cell.
   * @param x the x coordinate of the cell
   * @param y the y coordinate of the cell
   * @param time int, the current time
   */
  public void infectCell(int x, int y, int time) {
    this.cells[y][x].infect(time);
  }

	public void drawGrid() {
		for (double i = 0; i < 1; i += doubleW)
			for (double j = 0; j < 1; j += doubleH)
				StdDraw.line(i, j, i + doubleW, j);

		for (double j = 0; j < 1; j += doubleH)
			for (double i = 0; i < 1; i += doubleW)
				StdDraw.line(i, j, i, j + doubleH);
	}

}
