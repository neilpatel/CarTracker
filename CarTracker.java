/*
Neil Patel
COE 1501; Algorithms
Spring 2018 Semester
GitHub: neilpatel
*/

//My Implementation of Car Tracker

//Import Statements
import java.util.Scanner;
import java.lang.System;
import java.io.BufferedReader;
import java.io.FileReader;


//Start of CarTracker class
public class CarTracker {
	public static PriorityQueue db = new PriorityQueue();
	
	//Main Method
	public static void main(String[] args){
		loadCars();											// Load cars txt file into listOfCars
		SOP("\t -------------------------------------");
		SOP("\t| Welcome to Neil Patel's Car Tracker |");
		SOP("\t -------------------------------------");
		Scanner sc = new Scanner(System.in);				// Scanner class for user input
		boolean tracker = true;								// Boolean variable for while loop execution/termination
		while (tracker) {									// Begin While loop with tracker equal to true
			displayMainMenu();								// Display the Main Menu for the user
			SOP("\nPlease select an option.  ");			// Prompt the user to select an option
			SOP("Note: Only integers will be accepted");	// Note to the user that only integers will be accepted
			SOPrint("\tWhat is your selection? --> ");		// Ask the user for their selection
			int input = ensureUserIntegerInput();			// Accept user input
		
		
			//Conditional to check if the user entered a '1'
			if (input == 1) {
				SOP("-------------------------------------");
				SOP("You have selected Add A Car!");
				SOP("-------------------------------------");
				addACar();			//Call AddACar() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '2'
			else if (input == 2) {
				SOP("-------------------------------------");
				SOP("You have selected Update A Car!");
				SOP("-------------------------------------");
				updateCar();		//Call updateCar() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '3'
			else if (input == 3) {
				SOP("-------------------------------------");
				SOP("You have selected Remove A Car!");
				SOP("-------------------------------------");
				remove(); 			//Call AddACar() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '4'
			else if (input == 4) {
				SOP("-------------------------------------");
				SOP("You have selected Retrieve the Lowest Priced Car");
				SOP("-------------------------------------");
				lowestPricedCar(); //Call lowestPricedCar() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '5'
			else if (input == 5) {
				SOP("-------------------------------------");
				SOP("You have selected Retrieve the Lowest Milage Car");
				SOP("-------------------------------------");
				lowestMilageCar(); 	//Call lowestMilageCar() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '6'
			else if (input == 6) {
				SOP("-------------------------------------");
				SOP("You have selected Retrieve the Lowest Priced Car by Make and Model");
				SOP("-------------------------------------");
				lowestPriceByMakeAndModel(); 	//Call lowestPriceByMakeAndModel() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '7'
			else if (input == 7) {
				SOP("-------------------------------------");
				SOP("You have selected Retrieve the Lowest Milage Car by Make and Model");
				SOP("-------------------------------------");
				lowestMilageByMakeAndModel(); 	//Call lowestMilageByMakeAndModel() Method Below
				continue;
			}
			
			//Conditional to check if the user entered a '8'
			//Option given to user to exit the program at will
			else if (input == 8) {
				SOP("-------------------------------------");
				SOP("\n\nYou have exited the program. \n\tProgram terminated.\n\n");
				SOP("-------------------------------------");
				tracker = false;
			}
			
			
			//Conditional to check if the user entered a '9'
			//NOTE FOR THE GRADER!!! UNCOMMENT THIS TO DISPLAY ALL CARS IN DATABASE!
			else if (input == 9) {
				SOP("-----------------------------------------------------");
				SOP("-----------------------------------------------------");
				SOP("\t------ALL CARS WILL BE DISPLAYED BELOW------\n");
				PriorityQueue.carSOP();
				SOP("-----------------------------------------------------");
				SOP("-----------------------------------------------------");				
			}
			
			//Conditional for all other cases
			else{
				SOP("\n\n\n\t\t*********************");
				SOP("\t\t*   INVALID INPUT   *");
				SOP("\t\t*********************");
				SOP("\nPlease try again below! ");
				tracker = true; 	//Set the boolean variable for while loop to true. Allow the user to re enter a valid option.  
				SOP("");
				SOP("");
				SOP("");

			}
		}
	}
	
	//Simplified Method for System.out.println(s)
	private static void SOP(String s){
		System.out.println(s);
	}
	
	//Simplified Method for System.out.print(s)
	private static void SOPrint(String s){
		System.out.print(s);
	}

	//Display Main Menu for the user
	//Displays all the options for the user
	public static void displayMainMenu() {
		SOP("Main Menu: ");
		SOP("\t1 - Add A Car");
		SOP("\t2 - Update A Car");
		SOP("\t3 - Remove A Car");
		SOP("\t4 - Retrieve the Lowest Priced Car");
		SOP("\t5 - Retrieve the Lowest Milage Car");
		SOP("\t6 - Retrieve the Lowest Priced Car by Make and Model");
		SOP("\t7 - Retrieve the Lowest Milage Car by Make and Model");
		SOP("\t8 - Exit");
		SOP("\t9 - Print ALL Cars");
		
	}
	
	
	//Method to Add A Car to the database. 
	public static void addACar() {
		Scanner sc = new Scanner(System.in);						// Scanner class for user input
		CarInfo car = new CarInfo();								// Create an object car of class CarInfo
		SOP("\nPlease provide the following information below: ");	// Prompt the user to input the necessary information
		
		SOPrint("VIN: ");											// Prompt to enter in the VIN
		car.setCarVin(sc.nextLine().toLowerCase());					// Call the setCarVin Method in CarInfo Class
		SOPrint("Make: ");											// Prompt to enter in the Make of the Car
		car.setCarMake(sc.nextLine());								// Call the setCarMake Method in CarInfo Class
		SOPrint("Model: ");											// Prompt to enter in the Model of the Car
		car.setCarModel(sc.nextLine());								// Call the setCarModel Method in CarInfo Class
		SOPrint("Color: ");											// Prompt to enter in the Color of the Car
		car.setCarColor(sc.nextLine());								// Call the setCarColor Method in CarInfo Class
		SOPrint("Milage: ");										// Prompt to enter in the Milage
		car.setCarMilage(ensureUserIntegerInput());					// Call the setCarMilage Method in CarInfo Class
		SOPrint("Price: ");											// Prompt to enter in the Price
		car.setCarPrice(ensureUserIntegerInput());					// Call the setCarPrice Method in CarInfo Class
		SOP("-------------------------------------------------------------------");
		db.addACar(car);											// Add the car attributes into the DB
		SOP("");													// Print a new line for the next entry
	}
	
	//Method to update Car Attributes 
	public static void updateCar() {
		SOPrint("Please enter the VIN: ");							// Prompt the user to enter the VIN number first. 
		Scanner sc = new Scanner(System.in);						// Scanner class for user input
		String VIN = sc.nextLine();									// Accept user input for the VIN number
		
		//Menu for the user
		SOP("\nPlease select one from the following menu to update. ");
		SOP("\t1 - VIN");
		SOP("\t2 - Make");
		SOP("\t3 - Model");
		SOP("\t4 - Color");
		SOP("\t5 - Price");
		SOP("\t6 - Milage");
		SOP("");
		SOPrint("What is your selection: ");						// Prompt the user to make their selection.
		int userinput = ensureUserIntegerInput();					// Store the user input for their menu selection.
		
		
		// Error checking case to make sure user makes a VALID entry. 
		while(userinput > 6 || userinput < 1){						// Error echeck for user input. 
			SOP("Please select a valid option from the menu. ");
			SOP("\tWhat is your selection:  ");						// Ask user what their selection is. 
			userinput = ensureUserIntegerInput();					// Call ensureUserIntegerInput method to wait for integer input
		}
		SOPrint("Please enter a new value: ");						// Prompt the user to enter a new value for their selection. 
		String value = sc.nextLine();								// Store user input  

		
		// Conditional to check if the user enters a '1'
		if (userinput == 1) {
			db.updateCar(VIN, PriorityQueue.Attributes.VIN, value);		// Update the data base with new VIN Attribute
		}
		// Conditional to check if the user enters a '2'
		else if (userinput == 2) {
			db.updateCar(VIN, PriorityQueue.Attributes.MAKE, value);	// Update the data base with new MAKE Attribute
		}
		// Conditional to check if the user enters a '3'
		else if (userinput == 3) {
			db.updateCar(VIN, PriorityQueue.Attributes.MODEL, value);	// Update the data base with new MODEL Attribute
		}
		// Conditional to check if the user enters a '4'
		else if (userinput == 4) {
			db.updateCar(VIN, PriorityQueue.Attributes.COLOR, value);	// Update the data base with new COLOR Attribute
		}
		// Conditional to check if the user enters a '5'
		else if (userinput == 5) {
			db.updateCar(VIN, PriorityQueue.Attributes.PRICE, value);	// Update the data base with new PRICE Attribute
		}
		// Conditional to check if the user enters a '6'
		else if (userinput == 6) {
			db.updateCar(VIN, PriorityQueue.Attributes.MILAGE, value);	// Update the data base with new MILAGE Attribute
		}
		// Conditional to check for all else
		else {
			SOP("ERROR: Something went wrong. ");						// If something else occurs, display. (debug purposes)
			SOP("");
		}
	}
	// Method for Removing a Car from Priority Queue
	public static void remove() {
		SOPrint("Please enter the VIN Number of the car to remove: ");
		Scanner sc = new Scanner(System.in);				// Scanner class for user input

		String VIN = sc.nextLine();							// Accept user input for the VIN number
		PriorityQueue.remove(VIN);							// Remove the car from the priority queue using VIN number
		SOP("");
	}
	
	// Method for retrieving the Car with the Lowest Price
	public static void lowestPricedCar() {
		CarInfo car = db.lowestPrice();						// Make an object 'car' in CarInfo class and call the lowestPrice method
		if (car == null ) {									// If database empty, return a blank string. 
			SOP("");
			return;
		}
		car.carSOP();										// If not empty, return the lowest priced car. 
		SOP("");
	}
	
	// Method for retrieving the Car with the Lowest Milage	
	public static void lowestMilageCar() {
		CarInfo car = db.lowestMilage();					// Make an object 'car' in CarInfo class and call the lowestMilage method
		if (car == null) {									// If database empty, return a blank string. 
			SOP("");
			return;
		}
		car.carSOP();										// If not empty, return the car with the lowest miles
		SOP("");
	}
	
	// Method for retrieving the Car with the Lowest Price by Make and Model
	public static void lowestPriceByMakeAndModel() {
		Scanner sc = new Scanner(System.in);								// Scanner class for user input
		SOPrint("Please enter the Make: ");									// Prompt the user to enter the Car Make.
		String MAKE = sc.nextLine();										// Store the user's entry into MAKE.
		
		SOPrint("Please enter the Model: ");								// Prompt the user to enter the Car Model.
		String MODEL = sc.nextLine();										// Store the user's entry into MODEL. 
		
		CarInfo car = db.lowestPriceMakeAndModel(MAKE, MODEL);				// Search the DB for the lowest price with given MAKE and MODEL. 
		if (car == null) {
			SOP("I'm afraid no car can be found with this information.");	// If no cars can be displayed, inform the user. 
			SOP("");
			return;
		}
		SOP("");
		car.carSOP();														// If there are valid cars in the DB, display the one with highest priority 
		SOP("");
	}
	
	// Method for retrieving the Car with the Lowest Milage by Make and Model
	public static void lowestMilageByMakeAndModel() {
		Scanner sc = new Scanner(System.in);								// Scanner class for user input
		SOPrint("Please enter the Make: ");									// Prompt the user to enter the Car Make 
		String MAKE = sc.nextLine();										// Store the user's entry into MAKE
			
		SOPrint("Please enter the Model: ");								// Prompt the user to enter the Car Model
		String MODEL = sc.nextLine();										// Store the user's entry into MODEL 
		
		CarInfo car = db.lowestMilageMakeAndModel(MAKE, MODEL);				// Search the DB for the lowest milage with given MAKE and MODEL 
		if (car == null) {
			SOP("I'm afraid no car can be found with this information.");	// If no cars can be displayed, inform the user 
			SOP("");
			return;
		}
		SOP("");	
		car.carSOP();														// If there are valid cars in the DB, display the one with the highest priority 
		SOP("");
	}
	
	//Method to ensure user inputs an integer.
	//Saves time to insert while loop everytime (refactoring)
	private static int ensureUserIntegerInput() {
		Scanner sc = new Scanner(System.in);				// Scanner class for user input
		while (sc.hasNext()) {								// Error Checking while loop
			if (sc.hasNextInt()) {							// Check to see if Scanner Class read in an integer 
				return sc.nextInt();						// If the user entered an integer, return it to the callee 
			}
			else {
				SOPrint("\nPlease enter a valid int: ");	// Prompt them to enter an integer
				sc.next();									// Accept the next value they enter
			}
		}
		return 0;											// Should never actually return 0 (unless actually entered)
	}
	
	//Method to load the cars.txt file data in
	private static void loadCars() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("cars.txt")); // Create a new BufferedReader to load in the data
			String sCurrentLine;												// Create a temp string variable to hold the current string being read in
			br.readLine();														// Read in the lines of the file
			String [] carArray;													// Create a temp array to hold the cars.txt attributes
			while ((sCurrentLine = br.readLine()) != null) {	
				carArray = sCurrentLine.split(":");								// Split the text file with the ":" delimiter
				CarInfo newCar = new CarInfo(carArray[0].toLowerCase(), carArray[1], carArray[2], Integer.parseInt(carArray[3]), Integer.parseInt(carArray[4]), carArray[5]);
				db.addACar_helper(newCar, false);								// Add the cars to the listofCars ArrayList			
			}
		} catch (Exception e) {													// Throw an exception if cars txt file could not be loaded for some reason
			e.printStackTrace();												// Print the stack trace out to the user
		}
	}
}

// End of Class
// Neil Patel
