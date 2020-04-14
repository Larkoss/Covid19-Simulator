package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A class which is used to run the program
 * 
 * @author Konstantinos Larkou
 *
 */
public class Simulation {

	public static void main(String args[]) {
		double doubleH, doubleW;
		Grid d = new Grid(5, 5);
		d.drawGrid();
		doubleH = d.getDoubleH();
		doubleW = d.getDoubleW();

		Person p[] = new Person[4];
		p[0] = new BoomerPerson(0, 0);
		p[1] = new NormalPerson(0, 1);
		p[2] = new CarefulPerson(0, 2);
		p[3] = new ImmunePerson(0, 3);
		

		for (int i = 0; i < 4; i++) {
			p[i].draw(doubleH, doubleW);
		}
		
		d.drawGrid();
		StdDraw.show(2000);
		
		for (int i = 0; i < 4; i++) {
			p[i].infected = true;
			p[i].draw(doubleH, doubleW);
			
		}
		d.drawGrid();
		StdDraw.show();
		

	}

}
