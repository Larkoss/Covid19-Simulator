package NazirisLarkou.hw5;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Initialization {
	private static final double PERSON_TO_PERSON_INFECTION_PROBABILITY = .75;
	private static final double CELL_TO_PERSON_INFECTION_PROBABILITY = .10;
	private static final double PERSON_TO_CELL_INFECTION_PROBABILITY = .10;
	private static final double IMMUNE_PERSON_PROBABILITY = .10;
	private static final int DISINFECTION_PERIOD = 9;
	private static final int INFECTED_PEOPLE_ON_STARTUP = 1;
	private static final int SIMULATION_DURATION = 10;

	public double personToPersonInfectionProbability;
	public double cellToPersonInfectionProbability;
	public double personToCellInfectionProbability;
	public double immunePersonProbability;
	public int disinfectionPeriod;
	public int infectedPeopleOnStartup;
	public int simulationDuration;

	public Grid initializeGrid() {
		Scanner scan = new Scanner(System.in);
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

	public Person[] initializePeople(double height, double width) {
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

    Person.setPersonPossibility(probability);
    this.personToPersonInfectionProbability = probability;
	};

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

		Person.setGroundPossibility(probability);
    this.cellToPersonInfectionProbability = probability;
	};

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

    Cell.setInfectionPropability(probability);
    this.personToCellInfectionProbability = probability;
	};

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

    this.immunePersonProbability = probability;
    return probability;
	};

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

    Cell.setDisinfectionPeriod(period);
    this.disinfectionPeriod = period;
	};

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

    this.infectedPeopleOnStartup = num;
		return num;
	};

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

    this.simulationDuration = num;
		return num;
	};
}
