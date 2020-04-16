package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class which is used to run the program
 * 
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 *
 */
public class Simulation {
	private static int numOfWallsTouching(double x, double y, Grid grid) {
		int walls = 0;

		if(x == 0 || x == grid.getWidth() - 1) walls ++;
		if(y == 0 || y == grid.getHeight() - 1) walls ++;

		if(grid.getWidth() == 1) walls ++;
		if(grid.getHeight() == 1) walls ++;

		return walls;
	}

	private static boolean isOutsideGrid(double x, double y, Grid grid) {
		if(x < 0 || x > grid.getWidth() - 1) return true;
		if(y < 0 || y > grid.getHeight() - 1) return true;

		return false;
	}
	

	private static Person[] getNeighbours(double x, double y, Person people[]) {
		Person[] neighbours = new Person[people.length];
		int counter = 0;

		for(int i = 0; i < people.length; i++) {
			double absDiffX = Math.abs(people[i].getX() - x);
			double absDiffY = Math.abs(people[i].getY() - y);
			if(absDiffX >= -1 && absDiffX <= 1 && absDiffY >= -1 && absDiffY <= 1 && absDiffX + absDiffY != 0) {
				neighbours[counter ++] = people[i];
			}
		}

		Person[] results = new Person[counter];
		for(int i = 0; i < counter; i ++) {
			results[i] = neighbours[i];
		}

		return results;
	}

	private static boolean isCellEmpty(double x, double y, Person people[]) {
		for(int i = 0; i < people.length; i++)
			if(people[i].getX() == x && people[i].getY() == y)
				return false;

		return true;
	}

	/**
	 * A function that handles movement of people.
	 * @param people an array of Person objects
	 */
	public static void movePeople(Person people[], Grid grid) {
		for(int i = 0; i < people.length; i ++) {
			boolean shouldMove = people[i].shouldMove();

			// Go to next person if this person shouldn't move or if surrounded.
			double x = people[i].getX();
			double y = people[i].getY();

			Person[] neighbours = getNeighbours(x, y, people);
			int maxNeighbours = 8;
			
			switch(numOfWallsTouching(x, y, grid)) {
				case 0:
					maxNeighbours = 8;
					break;
				case 1:
					maxNeighbours = 5;
					break;
				case 2:
					if(grid.getWidth() == 1 || grid.getHeight() == 1) maxNeighbours = 2;
					else maxNeighbours = 3;
					break;
				case 3:
					maxNeighbours = 1;
					break;
				case 4:
					maxNeighbours = 0;
					break;
			}

			if(!shouldMove || neighbours.length == maxNeighbours) continue;

			double newX;
			double newY;
			double xOffset;
			double yOffset;
			double absOffsetSum;
			do {
				// Offset of -1 , 0 or 1
				xOffset = Math.floor(Math.random() * 3) - 1;
				yOffset = Math.floor(Math.random() * 3) - 1;
				absOffsetSum = Math.abs(xOffset) + Math.abs(yOffset);

				newX = people[i].getX() + xOffset;
				newY = people[i].getY() + yOffset;
			} while (!isCellEmpty(newX, newY, people) || isOutsideGrid(newX, newY, grid) || absOffsetSum == 0);

			people[i].move(newX, newY);
		}
	}

	private static void draw(Grid grid, Person people[]) {
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();
		grid.drawGrid();

		for(int i = 0; i < people.length; i ++) {
			people[i].draw(grid.getDoubleH(), grid.getDoubleW());
		}

		StdDraw.show();
		StdDraw.pause(0);
	}

	/**
	 * The main loop of the simulation.
	 * @param grid the Grid of cells
	 * @param people an array of Person objects
	 * @param simulationDuration the int representing the duration of the simulation
	 */
	public static void mainLoop(Grid grid, Person people[], int simulationDuration) {
		for(int time = 0; time < simulationDuration; time ++) {
			System.out.println(time);
			movePeople(people, grid);
			draw(grid, people);
		}
	}

	private static Grid initializeGrid() {
		Scanner scan = new Scanner(System.in);
		double doubleH, doubleW;
		int h = 0, w = 0;
		boolean input;
		
		System.out.println("Enter height and width of grid, between 1 and 15: ");
		input = false;
		while(!input) {
			try {
				h = scan.nextInt();
				if(h > 15)
					throw new ArithmeticException("Height must be below 15. Enter again: ");
				if(h < 1)
					throw new ArithmeticException("Height must be above 0. Enter again: ");
				w = scan.nextInt();
				if(w > 15)
					throw new ArithmeticException("Width must be below 15. Enter again: ");
				if(w < 1)
					throw new ArithmeticException("Width must be above 0. Enter again: ");
				input = true;
			}
			catch(ArithmeticException e) {
				scan.nextLine();
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				scan.nextLine();
				System.out.println("Height and width must be numbers. Enter again:");
			}
		}

		return new Grid(h, w);
	}

	private static Person[] initializePeople(double height, double width) {
		int size = 0, area = (int)(height * width);
		boolean input;
		Scanner scan = new Scanner(System.in);

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
		}
		
		Person[] p = new Person[size];
		double random;
		int x = -1, y = -1;
		boolean isCellEmpty;

		for (int i = 0; i < size; i++) {
			isCellEmpty = false;
			random = Math.random();
			while(!isCellEmpty) {
				isCellEmpty = true;
				x = (int) Math.floor(Math.random() * (width));
				y = (int) Math.floor(Math.random() * (height));
				for(int j = 0; j < i; j++)
					if(p[j].getX() == x && p[j].getY() == y)
						isCellEmpty = false;
			}
			
			if(random <= 0.3)
				p[i] = new BoomerPerson(x, y);
			else if(random <= 0.6)
				p[i] = new NormalPerson(x, y);
			else if(random <= 0.9)
				p[i] = new CarefulPerson(x, y);
			else
				p[i] = new ImmunePerson(x, y);
		}

		return p;
	}

	public static void main(String args[]) {
		Grid grid = initializeGrid();
		Person[] people = initializePeople(grid.getHeight(), grid.getWidth());

		// Draw once after initialization
		draw(grid, people);
		
		mainLoop(grid, people, 1200000);
	}

}
