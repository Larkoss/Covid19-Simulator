package NazirisLarkou.hw5;

import edu.princeton.cs.introcs.StdDraw;

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
	 * @param grid the grid
	 * @param time the current time
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

	private static int numOfImmunePeopleInArr(Person[] people) {
		int c = 0;

		for(int i = 0; i < people.length; i ++) {
			if(people[i] instanceof ImmunePerson) {
				c ++;
			}
		}

		return c;
	}

	/**
	 * Infect a number of people on startup.
	 * @param people an array of people that will be in the simulation
	 * @param num number of people to infect
	 */
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

	private static void printInformation(Initialization init, Grid grid, Person[] people) {
		System.out.print("Grid dimensions: ");
		System.out.println(grid.getWidth() + "x" + grid.getWidth());

		System.out.print("Number of people: ");
		System.out.println(people.length);
		
		System.out.print("Person to person infection probability: ");
		System.out.println(init.personToPersonInfectionProbability);

		System.out.print("Person to cell infection probability: ");
		System.out.println(init.personToCellInfectionProbability);

		System.out.print("Cell to person infection probability: ");
		System.out.println(init.cellToPersonInfectionProbability);

		System.out.print("Cell disinfection period: ");
		System.out.println(init.disinfectionPeriod);

		System.out.print("Number of infected people on startup: ");
		System.out.println(init.infectedPeopleOnStartup);
		
		System.out.print("Immune people probability: ");
		System.out.println(init.immunePersonProbability);
		
		System.out.print("Duration of simulation: ");
		System.out.println(init.simulationDuration);

		System.out.print("Number of infected people in the end of the simulation: ");

		int infectedPeople = 0;
		for(int j = 0; j < people.length; j ++) {
			if(people[j].getIsInfected())
				infectedPeople ++;
		}

		System.out.println(infectedPeople);
	}

	public static void main(String args[]) {
		Initialization init = new Initialization();

		Grid grid = init.initializeGrid();
		Person[] people = init.initializePeople(grid.getHeight(), grid.getWidth());

		// Initialize all variables
		init.initializePersonToPersonInfectionProbability();
		init.initializeCellToPersonInfectionProbability();
		init.initializePersonToCellInfectionProbability();
		init.initializeCellDisinfectionPeriod();

		// Infect a number of people on start up
		int numOfNonImmunePeople = people.length - numOfImmunePeopleInArr(people);
		int numOfInfected = init.getNumOfInfectedPeopleOnStartUp(numOfNonImmunePeople);

		infectNumOfPeople(people, numOfInfected);

		int steps = init.getSimulationDuration();

		// Draw once after initialization
		draw(grid, people, 0);
		System.out.println("<---START OF SIMULATION--->");
		
		mainLoop(grid, people, steps);

		System.out.println("<---END OF SIMULATION--->");

		printInformation(init, grid, people);
	}

}
