/*
Neil Patel
COE 1501; Algorithms
Spring 2018 Semester
GitHub: neilpatel
*/

//My Implementation of Priority Queue

//Import Statements
import java.util.Scanner;
import java.util.Random;
import java.lang.System;
import java.lang.String;
import java.lang.Integer;
import java.util.Stack;
import java.util.ArrayList;
import java.util.*;

public class PriorityQueue {

	//Simplified Method for System.out.println(s)
	private static void SOP(String s){
		System.out.println(s);
	}
	
	
	public static int size;												// Create a variable for size
	public static ArrayList<CarInfo> listOfCars = new ArrayList<>();	// Back PQ with an ArrayList to hold the list of cars
	public static MinPQ pricePriorityQueue =  new MinPQ();				// Create a price PQ with MinPQ Class
	public static MinPQ milagePriorityQueue =  new MinPQ();				// Create a milage PQ with MinPQ Class File
	
	//Create a Constructor and initialize size 
	public PriorityQueue() {
		int size = 0;
	}
	
	//Create enumerables for the car attributes
	public enum Attributes {
		VIN,
		MAKE,
		MODEL,
		COLOR,
		PRICE,
		MILAGE
	}
	
	//Main Method to addACar
	public void addACar(CarInfo car) {																	
		addACar_helper(car, true);
	}
	
	//Method to addACar(helper) into the PQ with the given information
	//Added this method to assist handle Error Checking (VIN Numbers) for the cars in 'cars.txt'
	public void addACar_helper(CarInfo car, boolean verbose) {															// Create a flag verbose to display error check statements other than the inital load
		String VIN = car.getCarVin();																					// Accept the VIN number from the user as a String and set it to the carVIN number
		VIN = VIN.toLowerCase();																						// Convert the input to lowercase
		//Conditional to check for proper requirements for VIN number
		if ( !(VIN.length() == 17) || VIN.contains("i") || VIN.contains("o"))  {
			if (verbose) {
				SOP("ERROR: The car could not be added with the given information.");									// Inform the user that their is an error with their entry
				SOP("\t Remember: The VIN number be 17 characters long, none of which can contain an 'i' or 'o'. ");	// Inform the user the requirements for the VIN number
			}
			
			return;
		}
		
		//If the user attempts to enter a VIN number they entered in the past 
		//Put into a try-catch block (debug purposes)
		try {
			//Conditional to check if the VIN number is inUse
			if (inUse(VIN)) {
				if (verbose) {				
				SOP("ERROR: This VIN number is in use. Please enter another VIN number. |"); 	// Inform the user that the VIN is in use. Must enter a new number
				SOP("-------------------------------------------------------------------");
				}
				
				return;
			}
			
			listOfCars.add(car);																// Add the car to the list of cars with given information
			CarSort carMilage = new CarSort(size, car.getCarMilage());							// Store the entered car milage into CarSort File
			milagePriorityQueue.insert(carMilage);												// Insert the car milage into the milage PQ
			CarSort carPrice = new CarSort(size, car.getCarPrice());							// Store the entered car price into the CarSort File
			pricePriorityQueue.insert(carPrice);												// Insert the car price into the price PQ
			size = size + 1;  																	// Increment the size of the database by 1
		} catch (Exception NullPointerException){
			SOP("You have attempted to enter the same VIN Number as before. ");
																								// If the same VIN number is entered, attempt to add it to the PQ
			try {
				SOP("Attempting to add this car to the database...");							// Inform the user that the car is being added to the PQ
				listOfCars.add(car);															// Add the car with given information to the list of cars
				CarSort carMilage2 = new CarSort(size, car.getCarMilage());						// Store the entered car milage into the CarSort File
				milagePriorityQueue.insert(carMilage2);											// Insert the car milage into the milage PQ
				CarSort carPrice2 = new CarSort(size, car.getCarPrice());						// Store the entered car price into the CarSort file
				pricePriorityQueue.insert(carPrice2);											// Insert the car price into the price PQ
				size = size + 1; 																// Increment the size of the database by 1
				SOP("Dont worry! Your Car has been added!");									// Inform the user that the car has successfully been added to the list of cars
			}
			catch (Exception e) {																// Safety check in case the car could not be added for some reason. (debug purposes)
				SOP("Could not process request. Please try again.");
			}
		}
	}
	
	//Method to remove a car with specific VIN number
	public static void remove(String VIN) {
		VIN = VIN.toLowerCase();												// Convert the user's input into all lowercase characters
		if (size < 1) {															// Conditional to check for no cars in list of cars
			SOP("I'm afraid no cars can be located with this information.");	// Inform the user that no cars could be located 
			return;
		}
		int i = 0;																// Create a variable to hold the index in ArrayList 
		
		Stack<CarSort> carMileStack = new Stack<>();							// Create a car mile stack
		Stack<CarSort> carPriceStack = new Stack<>();							// Create a car price stack
	
		boolean located = false;												// Create a temporary boolean variable
	
		for (CarInfo car : listOfCars) {										// Enhanced loop to iterate through all the cars and remove the car with matched VIN number 
			if (VIN.equals(car.getCarVin())) {									// Conditional to check if the entered VIN nymber matches any of the list of car VIN numbers 
				listOfCars.remove(i);											// Remove the value at index i. (use remove method in ArrayList class)
				located = true;													// Trigger the boolean flag to true 
				break;															// Break the for loop. 
			}
			i++;
		}
		
		//Conditional to check if the car cannot be located
		if (!located) {
			SOP("Your entry of the VIN number is not correct.");				// Inform the user that the entered VIN number does not match any results 
			return;
		}
		
		//Loop through the PQ and find the minimum priority car price
		while (true) {
			CarSort temp = (CarSort)pricePriorityQueue.delMin();
			if (temp.index == i){		
				break;
			}
			carPriceStack.add(temp);											// Add object temp of CarSort to the stack
		}
		
		//Loop through the price stack while its not empty
		while (!carPriceStack.isEmpty()) {
			pricePriorityQueue.insert(carPriceStack.pop());						// Insert into the price PQ the value popped on stack
		}
		
		//Loop through the PQ and find the minimum priority car milage
		while (true) {
			CarSort temp = (CarSort)milagePriorityQueue.delMin();
			if (temp.index == i){
				break;
			}
			carMileStack.add(temp);												// Add object temp of CarSort to the stack
		}
		
		//Loop through the mile stack while its not empty
		while (!carMileStack.isEmpty()) {
			milagePriorityQueue.insert(carMileStack.pop());
		}
		SOP("-------------------------------");
		SOP(">> Your Car has been removed");										// Inform the user that the car has been removed
		SOP("-------------------------------");
		size--;																	// Once removed, decrement the size of the database by 1
	}
	
	//Method to find the lowest priced car 
	public CarInfo lowestPrice() {
		if (size < 1){ 															// Conditional to check for no cars in list of cars
			SOP("I'm afraid no cars can be located with this information.");	// Inform the user that no cars could be located
			return null;
		}
		
		int count = ((CarSort) pricePriorityQueue.min()).index;					// Create a indexable variable to find look through the PQ
		return listOfCars.get(count);											// Return the lowest priced car in the list of cars
	}
	
	//Method to find the lowest milage car
	public CarInfo lowestMilage() {
		if (size < 1){															// Conditional to check for no cars in list of cars
			SOP("I'm afraid no cars can be located with this information.");	// Inform the user that no cars could be located
			return null;
		}
		int count = ((CarSort) milagePriorityQueue.min()).index;				// Create a indexable variable to find look through the PQ
		return listOfCars.get(count);											// Return the lowest milage car in the list of cars
	}
	
	//Method used to iterate through all the cars and print them out
	//Used for Printing ALL (GRADER!)
	//Also used for finding the lowest priced car in the list of cars
	public static void carSOP() {
		if (size < 1) {															// Conditional to check for no cars in list of cars
			SOP("I'm afraid no cars can be located with this information.");	// Inform the user that no cars could be located
			return;
		}
		for (CarInfo car : listOfCars){											// Enhanced loop to iterate through all the cars
			if (car != null) {													// Conditional to check if the car object in CarInfo is null
				car.carSOP();													// If true, iterate through list and print out the one with the lowest price
				SOP("");
			}
		}
	}
	
	//Method used to check of the VIN number is currently being used 
	public static boolean inUse(String VIN) {
		for (CarInfo car : listOfCars) {										// Enhanced loop to iterate through all the cars
			if ((VIN.equals(car.getCarVin()))) {								// Conditional to check if the entered VIN nymber matches any of the list of car VIN numbers 
				return true;													// If true, return true. Inform the user that the VIN number is in use currently
			}
		}
		return false;															// If VIN number can not be located, return false. Continue to add it to the list of cars 
	}
	
	
	
	// Method for retrieving the Car with the Lowest Price by Make and Model
	public CarInfo lowestPriceMakeAndModel(String MAKE, String MODEL) {
		if (size < 1) {														// Conditional to check for no cars in list of cars
			return null;
		}
		CarInfo car = new CarInfo();										// Create object named car in CarInfo class
		Stack<CarSort> temp = new Stack<>();								// Create a temp variable in the stack.
		boolean located = false;											// Set located boolean variable to false
		
		int counter = 0;													// Create counter variable and set it to 0
		
		//Iterate though the list of cars and return the car that matches make and model
		//Then look for the minimum priced car in the pricePriorityQueue
		while (counter < size) {
			CarSort cs = (CarSort)pricePriorityQueue.delMin();
			CarInfo indexOfCar = listOfCars.get(cs.index);
			if (indexOfCar.getCarMake().equals(MAKE) && indexOfCar.getCarModel().equals(MODEL)) {
				car = indexOfCar;
				temp.add(cs);					// Add CarSort object to the price PQ (quick operation)
				located = true;					// Trigger the boolean located flag (debug purposes)
				break;
			}
			temp.add(cs);
			
			counter = counter + 1;
		}
		
		//Conditional to check if the car is located. 
		if (!located) {
			return null;
		}
		//Loop through the price stack while its not empty
		while (!temp.isEmpty()) {
			pricePriorityQueue.insert(temp.pop());							// Insert into the price PQ the value popped on stack
		}
		return car;
		
	}
	
	// Method for retrieving the Car with the Lowest Milage by Make and Model
	public CarInfo lowestMilageMakeAndModel(String MAKE, String MODEL) {
		if (size < 1){														// Conditional to check for no cars in list of cars
			return null;
		}
		
		CarInfo car = new CarInfo();										// Create object named car in CarInfo class
		Stack<CarSort> temp = new Stack<>();								// Create a temp variable in the stack.
		boolean located = false;											// Set located boolean variable to false
		
		int counter = 0;													// Create counter variable and set it to 0
		
		//Iterate though the list of cars and return the car that matches make and model
		//Then look for the minimum milage car in the pricePriorityQueue
		while (counter < size) {
			CarSort cs = (CarSort)milagePriorityQueue.delMin();
			CarInfo indexOfCar = listOfCars.get(cs.index);
			if (indexOfCar.getCarMake().equals(MAKE) && indexOfCar.getCarModel().equals(MODEL)) {
				car = indexOfCar;
				temp.add(cs);
				located = true;
				break;
			}
			temp.add(cs);
			counter = counter + 1;
		}
		
		//Conditional to check if the car is located
		if (!located) {
			return null;
		}
		//Loop through the milage stack while its not empty
		while (!temp.isEmpty()) {
			milagePriorityQueue.insert(temp.pop());								// Insert into the milage PQ the value popped on stack
		}
		return car;
		
		
	}
	
	//Method used to update the car enumerables
	public void updateCar(String VIN, Attributes attribute, String value){
		if (size < 1) {															// Conditional to check for no cars in list of cars
			SOP("I'm afraid no cars can be located with this information.");	// Inform the user that no cars could be located
			return;
		}
		boolean located = false;												// Create a temporary boolean variable
		CarInfo car = null;
		VIN = VIN.toLowerCase();												// Convert the user's input into all lowercase characters
		int i = 0;																// Set the indexable variable to zero, reset it. (refactoring)
	
		for (CarInfo car2 : listOfCars) {										// Enhanced loop to iterate through all the cars
			if (VIN.equals(car2.getCarVin())) {									// Conditional to check if the entered VIN nymber matches any of the list of car VIN numbers 
				located = true;													// Trigger the boolean flag to true
				car = car2;														// Set the temp car2 variable to the car object in CarInfo
				break;
			}
			i++;																// Increment the index variable
		}
		
		//Conditional to check if the car cannot be located
		if (!located) {
			SOP("I'm afraid no cars can be located with this VIN number. ");	// Inform the user that the entered VIN number does not match any results 
			return;
		}
		Stack<CarSort> temp = null;
		
		//Update the attribute that the user wishes to update
		switch (attribute) {
			case VIN:
				car.setCarVin(value);
				break;
			case MAKE:
				car.setCarMake(value);
				break;
			case MODEL:
				car.setCarModel(value);
				break;
			case COLOR:
				car.setCarColor(value);
				break;
			case PRICE:
				car.setCarPrice(Integer.parseInt(value));
				temp = new Stack<>();
				while (true) {
					CarSort cs = (CarSort)pricePriorityQueue.delMin();
					if (cs.index == i) {
						cs.sortCarValue = Integer.parseInt(value);
						temp.add(cs);
						break;
					}
					temp.add(cs);
				}
				while (!temp.isEmpty()) {
					pricePriorityQueue.insert(temp.pop());
				}
				break;
			case MILAGE:
				car.setCarMilage(Integer.parseInt(value));
				temp = new Stack<>();
				while (true) {
					CarSort cs = (CarSort)milagePriorityQueue.delMin();
					if (cs.index == i) {
						cs.sortCarValue = Integer.parseInt(value);
						temp.add(cs);
						break;
					}
					temp.add(cs);
				}

				
				//Loop through the milage stack while its not empty
				while (!temp.isEmpty()) {
					milagePriorityQueue.insert(temp.pop());					// Insert into the milage PQ the value popped on stack
				}
				break;
			default:
				break;		
		}
	}
	
	//Method to ensure user inputs an integer
	//Saves time to insert while loop everytime (refactoring)
	private static int defUserInputInt() {
		Scanner sc = new Scanner(System.in);				// Scanner class for user input
		while (sc.hasNext()) {								// Error Checking while loop
			if (sc.hasNextInt()) {							// Check to see if Scanner Class read in an integer 
				return sc.nextInt();						// If the user entered an integer, return it to the callee 
			}
			else {											
				SOP("\nPlease enter a valid int: ");		// Prompt them to enter an integer
				sc.next();									// Accept the next value they enter
			}
		}
		return 0;											// Should never actually return 0
	}

	
}

// End of Class
// Neil Patel
