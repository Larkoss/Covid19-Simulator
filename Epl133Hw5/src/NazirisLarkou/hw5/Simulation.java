package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class which is used to run the program
 * 
 * @author Konstantinos Larkou
 *
 */
public class Simulation {
	
	

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		double doubleH, doubleW;
		int h = 0, w = 0;
		boolean input;
		
		System.out.println("Enter height of grid, between 1 and 15: ");
		input = false;
		while(!input) {
			try {
				h = scan.nextInt();
				if(h > 15)
					throw new ArithmeticException("Height must be below 15. Enter again: ");
				if(h < 1)
					throw new ArithmeticException("Height must be above 0. Enter again: ");
				input = true;
			}
			catch(ArithmeticException e) {
				scan.nextLine();
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				scan.nextLine();
				System.out.println("Height must be a number. Enter again:");
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("Error. Enter again: ");
			}
		}
		
		System.out.println("Enter width of grid, between 1 and 15: ");
		input = false;
		while(!input) {
			try {
				w = scan.nextInt();
				if(w > 15)
					throw new ArithmeticException("Width must be below 15. Enter again: ");
				if(h < 1)
					throw new ArithmeticException("Width must be above 0. Enter again: ");
				input = true;
			}
			catch(ArithmeticException e) {
				scan.nextLine();
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				scan.nextLine();
				System.out.println("Width must be a number. Enter again:");
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("Error. Enter again: ");
			}
		}		
		
		int size = 0, area = h * w;
		System.out.println("Enter number of people, between 1 and " + area + ": ");
		input = false;
		while(!input) {
			try {
				size = scan.nextInt();
				if(size > area)
					throw new ArithmeticException("People are more than area of grid. Enter again: ");
				if(size < 1)
					throw new ArithmeticException("There should be at least one person. Enter again: ");
				input = true;
			}
			catch(ArithmeticException e) {
				scan.nextLine();
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				scan.nextLine();
				System.out.println("Wrong Input. Enter again:");
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("Error. Enter again: ");
			}
		}
		
		Grid grid = new Grid(h, w);
		grid.drawGrid();
		doubleH = grid.getDoubleH();
		doubleW = grid.getDoubleW();
		
		Person p[] = new Person[size];
		double random;
		int x = -1, y = -1;

		for (int i = 0; i < size; i++) {
			input = false;
			random = Math.random();
			while(!input) {
				input = true;
				x = (int) Math.floor(Math.random() * (h));
				y = (int) Math.floor(Math.random() * (w));
				for(int j = 0; j < i; j++)
					if(p[j].getX() == x && p[j].getY() == y)
						input = false;
			}
			
			if(random <= 0.3)
				p[i] = new BoomerPerson(x, y);
			else if(random <= 0.6)
				p[i] = new NormalPerson(x, y);
			else if(random <= 0.9)
				p[i] = new CarefulPerson(x, y);
			else
				p[i] = new ImmunePerson(x, y);
	
			p[i].draw(doubleH, doubleW);
			StdDraw.show();
		}
		
		grid.drawGrid();
		StdDraw.show();
		
	}

}
