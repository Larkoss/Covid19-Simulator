package NazirisLarkou.hw6;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A class used for initializing a simulation's variables.
 * 
 * @author Andreas Naziris
 */
public class Initialization {
	private static final double PERSON_TO_PERSON_INFECTION_PROBABILITY = .75;
	private static final double CELL_TO_PERSON_INFECTION_PROBABILITY = .10;
	private static final double PERSON_TO_CELL_INFECTION_PROBABILITY = .10;
	private static final double IMMUNE_PERSON_PROBABILITY = .10;
	private static final int DISINFECTION_PERIOD = 9;
	private static final int INFECTED_PEOPLE_ON_STARTUP = 1;
	private static final int SIMULATION_DURATION = 50;

	public double personToPersonInfectionProbability;
	public double cellToPersonInfectionProbability;
	public double personToCellInfectionProbability;
	public double immunePersonProbability;
	public int disinfectionPeriod;
	public int infectedPeopleOnStartup;
	public int simulationDuration;

	/**
	 * Initialize people.
	 * @param gridArr an grid array with all the created grids
	 * @return returns the initialized grid.
	 */
	public Person[] initializePeople(Grid[] gridArr) {
		int size = 0, area = 0;
		boolean input;

		// Get overall area
		for(int i = 0; i < gridArr.length; i ++) {
			area += gridArr[i].getArea();
		}

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
		double personTypeChance;
		int x, y;
		char gridId;

		for (int i = 0; i < size; i++) {
			personTypeChance = Math.random();

			do {
				Grid selectedGrid = gridArr[(int) Math.floor(Math.random() * (gridArr.length))];

				gridId = selectedGrid.getId();
				x = (int) Math.floor(Math.random() * (selectedGrid.getWidth()));
				y = (int) Math.floor(Math.random() * (selectedGrid.getHeight()));
			} while(Person.isPositionOccupied(x, y, gridId, p));
			
			if(personTypeChance <= otherChance)
				p[i] = new BoomerPerson(x, y, gridId);
			else if(personTypeChance <= 2 * otherChance)
				p[i] = new NormalPerson(x, y, gridId);
			else if(personTypeChance <= 3 * otherChance)
				p[i] = new CarefulPerson(x, y, gridId);
			else
				p[i] = new ImmunePerson(x, y, gridId);
		}

		return p;
	}

	/**
	 * Initialize person to person infection probability.
	 */
	public void initializePersonToPersonInfectionProbability() {
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

		if(probability == -1) probability = PERSON_TO_PERSON_INFECTION_PROBABILITY;
		Person.setPersonPossibility(probability);
		this.personToPersonInfectionProbability = probability;
	};

	/**
	 * Initialize cell to person infection probability.
	 */
	public void initializeCellToPersonInfectionProbability() {
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

		if(probability == -1) probability = CELL_TO_PERSON_INFECTION_PROBABILITY;
		Person.setGroundPossibility(probability);
		this.cellToPersonInfectionProbability = probability;
	};

	/**
	 * Initialize person to cell infection probability.
	 */
	public void initializePersonToCellInfectionProbability() {
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

		if(probability == -1) probability = PERSON_TO_CELL_INFECTION_PROBABILITY;
		Cell.setInfectionPropability(probability);
		this.personToCellInfectionProbability = probability;
	};

	/**
	 * Get the probability of a person being immune from user.
	 * @return the probability of a person being immune.
	 */
	public double getImmunePeopleProbability() {
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

		if(probability == -1) probability = IMMUNE_PERSON_PROBABILITY;
		this.immunePersonProbability = probability;
		return probability;
	};

	/**
	 * Initialize cell disinfection period.
	 */
	public void initializeCellDisinfectionPeriod() {
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

		if(period == -1) period = DISINFECTION_PERIOD;
		Cell.setDisinfectionPeriod(period);
		this.disinfectionPeriod = period;
	};

	/**
	 * Get nubmer of infected people on start up from user.
	 * @param people number of non immune people
	 * @return number of infected people on start up
	 */
	public int getNumOfInfectedPeopleOnStartUp(int people) {
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

		if(num == -1) num = INFECTED_PEOPLE_ON_STARTUP;
		this.infectedPeopleOnStartup = num;
		return num;
	};

	/**
	 * Get duration for simulation from user.
	 * @return number of steps/minutes the simulation should last for
	 */
	public int getSimulationDuration() {
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

		if(num == -1) num = SIMULATION_DURATION;
		this.simulationDuration = num;
		return num;
	};
}
