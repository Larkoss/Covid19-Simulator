package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.platform.launcher.PostDiscoveryFilter;

/**
 * A class which is used to run the program
 * 
 * @author Konstantinos Larkou
 * @author Andreas Naziris
 *
 */
public class Simulation {
	private static final double PERSON_TO_PERSON_INFECTION_PROBABILITY = .75;
	private static final double CELL_TO_PERSON_INFECTION_PROBABILITY = .10;
	private static final double PERSON_TO_CELL_INFECTION_PROBABILITY = .10;
	private static final double IMMUNE_PERSON_PROBABILITY = .10;
	private static final int DISINFECTION_PERIOD = 9;
	private static final int INFECTED_PEOPLE_ON_STARTUP = 1;
	private static final int SIMULATION_DURATION = 10;

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
	public static void movePeople(Person people[], Grid grid, int time) {
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

			int infectedNeighbours = 0;

			for(int j = 0; j < neighbours.length; j ++) {
				if(neighbours[j].getIsInfected())
					infectedNeighbours ++;
			}

			// Infect person
			people[i].infect(grid.isCellInfected((int)x, (int)y, time), infectedNeighbours);
			

			if(!shouldMove || neighbours.length == maxNeighbours) {
				if(people[i].getIsInfected()) {
					grid.infectCell((int)people[i].getX(), (int)people[i].getY(), time);
				}

				continue;
			}

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

	private static void draw(Grid grid, Person people[], int time) {
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();
		grid.drawGrid();

		for(int i = 0; i < people.length; i ++) {
			boolean isCurCellInfected = grid.isCellInfected((int)people[i].getX(), (int)people[i].getY(), time);
			people[i].draw(grid.getDoubleH(), grid.getDoubleW(), isCurCellInfected);
		}

		StdDraw.show();
		StdDraw.pause(1000);
	}

	/**
	 * The main loop of the simulation.
	 * @param grid the Grid of cells
	 * @param people an array of Person objects
	 * @param simulationDuration the int representing the duration of the simulation
	 */
	public static void mainLoop(Grid grid, Person people[], int simulationDuration) {
		for(int time = 0; time < simulationDuration; time ++) {
			movePeople(people, grid, time);
			grid.updateCells(time);
			draw(grid, people, time);
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

		double immuneChance = getImmunePeopleProbability();
		double otherChance = (1 - immuneChance) / 3.0;
		
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
			
			if(random <= otherChance)
				p[i] = new BoomerPerson(x, y);
			else if(random <= 2 * otherChance)
				p[i] = new NormalPerson(x, y);
			else if(random <= 3 * otherChance)
				p[i] = new CarefulPerson(x, y);
			else
				p[i] = new ImmunePerson(x, y);
		}

		return p;
	}

	private static void initializePersonToPersonInfectionProbability() {
		boolean input;
		double probability = PERSON_TO_PERSON_INFECTION_PROBABILITY;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter person to person infection probability, number from 0.0 to 1.0:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				probability = scan.nextDouble();

				// Use default values
				if(probability == -1) break;

				if(probability > 1 || probability < 0)
					throw new ArithmeticException("Probability can't be below 0 or above 1. Enter again: ");

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

		Person.setPersonPossibility(probability);
	};

	private static void initializeCellToPersonInfectionProbability() {
		boolean input;
		double probability = CELL_TO_PERSON_INFECTION_PROBABILITY;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter cell to person infection probability, number from 0.0 to 1.0:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				probability = scan.nextDouble();

				// Use default values
				if(probability == -1) break;

				if(probability > 1 || probability < 0)
					throw new ArithmeticException("Probability can't be below 0 or above 1. Enter again: ");

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

		Person.setGroundPossibility(probability);
	};

	private static void initializePersonToCellInfectionProbability() {
		boolean input;
		double probability = PERSON_TO_CELL_INFECTION_PROBABILITY;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter person to cell infection probability, number from 0.0 to 1.0:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				probability = scan.nextDouble();

				// Use default values
				if(probability == -1) break;

				if(probability > 1 || probability < 0)
					throw new ArithmeticException("Probability can't be below 0 or above 1. Enter again: ");

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

		Cell.setInfectionPropability(probability);
	};

	private static double getImmunePeopleProbability() {
		boolean input;
		double probability = IMMUNE_PERSON_PROBABILITY;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter immune person probability, number from 0.0 to 1.0:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				probability = scan.nextDouble();

				// Use default values
				if(probability == -1) break;

				if(probability > 1 || probability < 0)
					throw new ArithmeticException("Probability can't be below 0 or above 1. Enter again: ");

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

		return probability;
	};

	private static void initializeCellDisinfectionPeriod() {
		boolean input;
		int period = DISINFECTION_PERIOD;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter cell disinfection period in minutes, above 0:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				period = scan.nextInt();

				// Use default values
				if(period == -1) break;

				if(period <= 0)
					throw new ArithmeticException("Period can't be below 0. Enter again: ");

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

		Cell.setDisinfectionPeriod(period);
	};

	private static int getNumOfInfectedPeopleOnStartUp(int people) {
		// If no people return 0
		if(people == 0) {
			return 0;
		}

		boolean input;
		int num = INFECTED_PEOPLE_ON_STARTUP;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter number of infected people on startup, above 1 and below " + people + ":");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				num = scan.nextInt();

				// Use default values
				if(num == -1) break;

				if(num <= 1 || num > people)
					throw new ArithmeticException("Ifected people can't be below 1 or above " + people + ". Enter again: ");

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

		return num;
	};

	private static int getSimulationDuration() {
		boolean input;
		int num = SIMULATION_DURATION;
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter number steps/minutes the simulation should run for, above 1:");
		System.out.println("If you want to use default values enter -1:");

		input = false;

		while(!input) {
			try {
				num = scan.nextInt();

				// Use default values
				if(num == -1) break;

				if(num <= 1)
					throw new ArithmeticException("Number of steps/minutes can't be below 1. Enter again: ");

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

		return num;
	};


	private static int numOfImmunePeopleInArr(Person[] people) {
		int c = 0;

		for(int i = 0; i < people.length; i ++) {
			if(people[i] instanceof ImmunePerson) {
				c ++;
			}
		}

		return c;
	}

	public static void infectNumOfPeople(Person[] people, int num) {
		for(int j = 0; j < num; j ++) {
			for(int i = j; i < people.length; i ++) {
				if(people[i] instanceof ImmunePerson) continue;
				if(people[i].getIsInfected()) continue;

				people[i].setInfected(true);
				break;
			}
		}
	}

	public static void main(String args[]) {
		Cell.setInfectionPropability(.1);
		Cell.setDisinfectionPeriod(9);

		Grid grid = initializeGrid();
		Person[] people = initializePeople(grid.getHeight(), grid.getWidth());

		// Initialize all variables
		initializePersonToPersonInfectionProbability();
		initializeCellToPersonInfectionProbability();
		initializePersonToCellInfectionProbability();
		initializeCellDisinfectionPeriod();

		// Infect a number of people on start up
		int numOfNonImmunePeople = people.length - numOfImmunePeopleInArr(people);
		int numOfInfected = getNumOfInfectedPeopleOnStartUp(numOfNonImmunePeople);

		infectNumOfPeople(people, numOfInfected);

		int steps = getSimulationDuration();

		// Draw once after initialization
		draw(grid, people, 0);
		
		mainLoop(grid, people, steps);
	}

}
