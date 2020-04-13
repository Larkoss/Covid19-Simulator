package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Simulation {
	
	
	public static void main(String args[]) {
		double doubleH, doubleW;
		Grid d = new Grid(10, 5);
		d.drawGrid();
		doubleH = d.getDoubleH();
		doubleW = d.getDoubleW();
		
		Person b = new BoomerPerson(1 , 2);
		b.draw(doubleH, doubleW);
	}

}
