package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Grid {
	private static double doubleH; // Percentage the height of the box is according to canvas
	private static double doubleW; // Percentage the width of the box is according to canvas

	/**
	 * Constructor that initializes the static doubles
	 * 
	 * @param h Y-axis
	 * @param w X-axis
	 */
	public Grid(int h, int w) { // Constructor
		double hDivider = 100.0 / h;
		Grid.doubleH = hDivider / 100.0;

		double wDivider = 100.0 / w;
		Grid.doubleW = wDivider / 100.0;
	}

	/**
	 * @return the doubleH
	 */
	public static double getDoubleH() {
		return doubleH;
	}

	/**
	 * @return the doubleW
	 */
	public static double getDoubleW() {
		return doubleW;
	}

	public static void drawGrid() {
		StdDraw.setCanvasSize(700, 700);
		for (double i = 0; i < 1; i += doubleW)
			for (double j = 0; j < 1; j += doubleH)
				StdDraw.line(i, j, i + doubleW, j);

		for (double j = 0; j < 1; j += doubleH)
			for (double i = 0; i < 1; i += doubleW)
				StdDraw.line(i, j, i, j + doubleH);
	}

}
