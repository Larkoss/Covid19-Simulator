package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class representing a cell of the grid
 *
 * @author Andreas Naziris
 *
 */
public class Cell {
  private boolean isInfected;

  /**
   * Class contructor.
   */
  public Cell() {
    this.isInfected = false;
  }

  /**
   * Getter for isInfected
   * @return isInfected
   */
  public boolean getIsInfected() {
    return this.isInfected;
  }
}
