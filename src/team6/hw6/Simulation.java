package team6.hw6;

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
	

	private static Person[] getNeighbours(double x, double y, char grid, Person people[]) {
		Person[] neighbours = new Person[people.length];
		int counter = 0;

		for(int i = 0; i < people.length; i++) {
			// Go to next person if not on the same grid
			if(people[i].getGrid() != grid) {
				continue;
			}

			double absDiffX = Math.abs(people[i].getX() - x);
			double absDiffY = Math.abs(people[i].getY() - y);
			if(
				absDiffX >= -1 &&
				absDiffX <= 1 &&
				absDiffY >= -1 &&
				absDiffY <= 1 &&
				absDiffX + absDiffY != 0
			) {
				neighbours[counter ++] = people[i];
			}
		}

		Person[] results = new Person[counter];
		for(int i = 0; i < counter; i ++) {
			results[i] = neighbours[i];
		}

		return results;
	}

	/**
	 * A function that handles movement of people.
	 * @param people an array of Person objects
	 * @param grid the grid
	 * @param time the current time
	 */
	public static void movePeople(Person people[], Grid[] gridArr, int time) {
		for(int i = 0; i < people.length; i ++) {
			boolean shouldMove = people[i].shouldMove();

			// Go to next person if this person shouldn't move or if surrounded.
			double x = people[i].getX();
			double y = people[i].getY();
			char gridId = people[i].getGrid();
			Grid grid = null;

			// Get grid the person belongs to
			for(int j = 0; j < gridArr.length; j ++) {
				if(gridArr[j].getId() == gridId) {
					grid = gridArr[j];
					break;
				}
			}

			Person[] neighbours = getNeighbours(x, y, gridId, people);
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

			boolean isAirport = grid.isCellAirport((int)x, (int)y);

			// If shouldn't move or if surrounded and not on an airport
			if(!shouldMove || (neighbours.length == maxNeighbours && !isAirport)) {
				if(people[i].getIsInfected()) {
					grid.infectCell((int)people[i].getX(), (int)people[i].getY(), time);
				}

				continue;
			}

			double newX = x;
			double newY = y;
			double xOffset = 0;
			double yOffset = 0;
			double absOffsetSum = 0;

			if(isAirport) {
				int nextGridPopulation = 0;
				int nextGridArea = 0;
				char nextGridId = ' ';

				// Get next grid
				for(int k = 0; k < gridArr.length; k ++) {
					if(gridArr[k].getId() == gridId) {
						Grid nextGrid = gridArr[(k + 1) % gridArr.length];
						nextGridArea = nextGrid.getArea();
						nextGridId = nextGrid.getId();
						break;
					}
				}

				// Calculate next grid population and area
				for(int j = 0; j < people.length; j ++) {
					if(people[j].getGrid() == nextGridId) {
						nextGridPopulation ++;
					}
				}

				// If next grid is not full transport perosn to next
				if(nextGridArea > nextGridPopulation) {
					do {
						// Offset of -1 , 0 or 1
						xOffset = Math.floor(Math.random() * 3) - 1;
						yOffset = Math.floor(Math.random() * 3) - 1;
						absOffsetSum = Math.abs(xOffset) + Math.abs(yOffset);
		
						newX = people[i].getX() + xOffset;
						newY = people[i].getY() + yOffset;

						if(isOutsideGrid(newX, newY, grid)) {
							if(grid.getId() == 'a') {
								people[i].setGrid('b');
								grid = gridArr[1];
							}
							else if(grid.getId() == 'b') {
								people[i].setGrid('c');
								grid = gridArr[2];
							}
							else {
								people[i].setGrid('a');
								grid = gridArr[0];
							}
							gridId = people[i].getGrid();

							do {
								newX = (int) Math.floor(Math.random() * (grid.getWidth()));
								newY = (int) Math.floor(Math.random() * (grid.getHeight()));
							}while(Person.isPositionOccupied((int)newX, (int)newY, gridId, people));
						}
					} while (Person.isPositionOccupied((int)newX, (int)newY, gridId, people)  || absOffsetSum == 0 || isOutsideGrid(newX, newY, grid));
				}
			} else {
				do {
					// Offset of -1 , 0 or 1
					xOffset = Math.floor(Math.random() * 3) - 1;
					yOffset = Math.floor(Math.random() * 3) - 1;
					absOffsetSum = Math.abs(xOffset) + Math.abs(yOffset);
	
					newX = people[i].getX() + xOffset;
					newY = people[i].getY() + yOffset;
				} while (Person.isPositionOccupied((int)newX, (int)newY, gridId, people) || isOutsideGrid(newX, newY, grid) || absOffsetSum == 0);
			}

			people[i].move(newX, newY);
		}
	}

	private static void draw(Grid[] gridArr, Person people[], int time, int arrPos) {
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();

		gridArr[arrPos].drawGrid();
		double doubleH = gridArr[arrPos].getDoubleH();
		double doubleW = gridArr[arrPos].getDoubleW();
		boolean isCurCellInfected = false, isAirport = false;
		for(int i = 0; i < people.length; i++) {
			if(people[i].getGrid() == gridArr[arrPos].getId()) {
				isCurCellInfected = gridArr[arrPos].isCellInfected((int)people[i].getX(), (int)people[i].getY(), time);
				isAirport = gridArr[arrPos].isCellAirport((int)people[i].getX(), (int)people[i].getY());
				people[i].draw(doubleH, doubleW, isCurCellInfected, isAirport);
			}
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
	public static void mainLoop(Grid[] gridArr, Person people[], int simulationDuration) {
		for(int time = 0; time < simulationDuration; time ++) {
			System.out.println("Step:\t" + time);
			movePeople(people, gridArr, time);

			for(int i = 0; i < gridArr.length; i ++) {
				gridArr[i].updateCells(time);
			}
			if(time % 15 >=0 && time % 15 <= 4)
				draw(gridArr, people, time, 0);
			else if(time % 15 >= 5 && time % 15 <= 9)
				draw(gridArr, people, time, 1);
			else
				draw(gridArr, people, time, 2);
				
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

	private static void printInformation(Initialization init, Grid[] gridArr, Person[] people) {
		// Print grid information
		for(int i = 0; i < gridArr.length; i ++) {
			Grid curGrid = gridArr[i];

			System.out.print("Grid dimensions for grid with id " + curGrid.getId() + ": ");
			System.out.println(curGrid.getWidth() + "x" + curGrid.getWidth());
		}

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
		// Initialize 3 grids
		Grid gridA = new Grid(15, 10, 'a');
		gridA.setAirport(6, 14);
		gridA.setAirport(5, 14);
		gridA.setAirport(4, 14);
		Grid gridB = new Grid(12, 12, 'b');
		gridB.setAirport(6, 0);
		gridB.setAirport(5, 0);
		gridB.setAirport(4, 0);
		gridB.setAirport(3, 0);
		Grid gridC = new Grid(10, 15, 'c');
		gridC.setAirport(14,7);
		gridC.setAirport(14,6);
		gridC.setAirport(14,5);
		gridC.setAirport(14,4);
		gridC.setAirport(14,3);
		Grid[] gridArr = { gridA, gridB, gridC };

		Initialization init = new Initialization();
		Person[] people = init.initializePeople(gridArr);

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
		draw(gridArr, people, 0, 0);
		System.out.println("<---START OF SIMULATION--->");
		
		mainLoop(gridArr, people, steps);

		System.out.println("<---END OF SIMULATION--->");

		printInformation(init, gridArr, people);
	}
}
