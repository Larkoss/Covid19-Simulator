package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Grid {
	private double doubleH; 		// Percentage the height of the box is according to canvas
									// The length of height, of a single square
	private double doubleW;			// Percentage the width of the box is according to canvas
									// The length of width, of a single square
	
	{								//Instance Initialization Block			
		StdDraw.setCanvasSize(700, 700);
	}
	
	
	/**
	 * Constructor that initializes the static doubles
	 * 
	 * @param h Y-axis
	 * @param w X-axis
	 */
	public Grid(int h, int w) { // Constructor
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
	public void drawGrid() {
		for (double i = 0; i < 1; i += doubleW)
			for (double j = 0; j < 1; j += doubleH)
				StdDraw.line(i, j, i + doubleW, j);

		for (double j = 0; j < 1; j += doubleH)
			for (double i = 0; i < 1; i += doubleW)
				StdDraw.line(i, j, i, j + doubleH);
	}

}
