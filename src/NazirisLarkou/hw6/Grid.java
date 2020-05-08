package NazirisLarkou.hw6;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing the grid
 * 
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 *
 */
public class Grid {
	private char id;
	
	private Cell[][] cells;

	private double doubleH; // Percentage the height of the box is according to canvas
							// The length of height, of a single square
	private double doubleW; // Percentage the width of the box is according to canvas
							// The length of width, of a single square


	/**
	 * Constructor that specifies height, width and id.
	 *
	 * For height and width the properties initialised are doubleH and doubleW
	 * 
	 * @param h the height of the grid
	 * @param w the width of the grid
	 * @param id the char id of the grid
	 */
	public Grid(int h, int w, char id) {
		this.id = id;

		this.cells = new Cell[h][w];

		// Populate table
		for(int i = 0; i < h; i ++) {
			for(int j = 0; j < w; j ++) {
				this.cells[i][j] = new Cell();
			}
		}
		
		
		if(h>w) {
			double num = (double)h / w;
			this.doubleH = 1.0 / h;
			this.doubleW = 1.0 / h * num;
			StdDraw.setCanvasSize((int)(1000.0/h*w), 1000);
		}
		else {
			double num = (double)w / h;
			this.doubleH = 1.0 / w * num;
			this.doubleW = 1.0 / w;
			StdDraw.setCanvasSize(1000, (int)(1000.0/w*h));
		}
	}

	/**
	 * Getter for the double height of each cell used for drawing.
	 * @return the doubleH
	 */
	public double getDoubleH() {
		return doubleH;
	}

	/**
	 * Getter for the double width of each cell used for drawing.
	 * @return the doubleW
	 */
	public double getDoubleW() {
		return doubleW;
	}

	/**
	 * Getter for the height of the grid.
	 * @return the height
	 */
	public int getHeight() {
		return (int)this.cells.length;
	}

	/**
	 * Getter for the width of the grid.
	 * @return the width
	 */
	public int getWidth() {
		return (int)this.cells[0].length;
	}

	/**
	 * Getter for the area of the grid.
	 * @return the area
	 */
	public int getArea() {
		return (int)this.getWidth() * this.getHeight();
	}

	/**
	 * Getter for the grid id.
	 * @return the grid id
	 */
	public char getId() {
		return this.id;
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
	
	public boolean isCellAirport(int x, int y) {
		return this.cells[y][x].isAirport();
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
	
	public void setAirport(int x, int y) {
		this.cells[y][x].setAirport();
	}

	/**
	 * Update all cells.
	 * @param time the current time
	 */
	public void updateCells(int time) {
		for(int i = 0; i < this.getHeight(); i ++) {
			for(int j = 0; j < this.getWidth(); j ++) {
				this.cells[i][j].updateInfected(false, time);
			}
		}
	}

	/**
	 * Draw the grid.
	 */
	public void drawGrid() {
		for(int i = 0; i < this.getHeight(); i ++) {
			for(int j = 0; j < this.getWidth(); j ++) {
				this.cells[i][j].draw(j, i, doubleW, doubleH);
			}
		}
		// Draws horizontal lines
		for (double j = 0; j < 1; j += doubleH)
			StdDraw.line(0, j, 1, j);

		// Draws vertical lines
		for (double i = 0; i < 1; i += doubleW)
			StdDraw.line(i, 0, i, 1);
	}
}
