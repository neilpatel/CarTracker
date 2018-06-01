/*
Neil Patel
COE 1501; Algorithms
Spring 2018 Semester
GitHub: neilpatel
*/

// Basic File with all the Getters/Setters/Print Statements/Car Detail Stuff

// Import Statements
import java.util.Scanner;
import java.util.Random;
import java.lang.System;
import java.lang.String;
import java.lang.Integer;

public class CarInfo {
	private String VIN;					// Create private String variable for VIN
	private String MAKE;				// Create private String variable for MAKE
	private String MODEL;				// Create private String variable for MODEL
	private String COLOR;				// Create private String variable for COLOR
	private int PRICE;					// Create private int variable for PRICE
	private int MILAGE;					// Create private int variable for MILAGE
	
	// List all the enums
	public CarInfo (String VIN, String make, String model, int price, int milage, String color) {
		this.VIN = VIN;					// Use constructor for VIN
		this.MAKE = make;				// Use constructor for make
		this.MODEL = model;				// Use constructor for model
		this.COLOR = color;				// Use constructor for color
		this.PRICE = price;				// Use constructor for price
		this.MILAGE = milage;			// Use constructor for milage
	
	}

	// Simplified Method for System.out.println(s)
	private static void SOP(String s){
		System.out.println(s);
	}
	
	public CarInfo() {
		this.VIN = "";					// Set contructor VIN to an empty string
		this.MAKE = "";					// Set contructor MAKE to an empty string
		this.MODEL = "";				// Set contructor MODEL to an empty string
		this.COLOR = "";				// Set contructor COLOR to an empty string
		this.PRICE = 0;					// Set contructor PRICE to zero
		this.MILAGE = 0;				// Set contructor MILAGE to zero
		
	}
	
	//  Print the Car Details when called
	public void carSOP() {
		SOP("VIN:  " + this.VIN);							// Print the VIN
		SOP("Make: " + this.MAKE);							// Print the Make
		SOP("Model: " + this.MODEL);						// Print the Model
		SOP("Color: " + this.COLOR);						// Print the Color
		SOP("Milage: " + Integer.toString(this.MILAGE));	// Print the Milage
		SOP("Price: $" + Integer.toString(this.PRICE));		// Print the Price
	}
		
	// Get Methods
	public String getCarVin() {
		return this.VIN;									// Return the VIN
	}
	
	public String getCarMake() {
		return this.MAKE;									// Return the Make
	}
	
	public String getCarModel() {
		return this.MODEL;									// Return the Model
	}
	
	public String getCarColor() {
		return this.COLOR;									// Return the Color
	}
	
	public int getCarPrice() {
		return this.PRICE;									// Return the Price
	}
	
	public int getCarMilage() {
		return this.MILAGE;									// Return the Milage
	}

	
	// Set Methods
	public void setCarVin(String vin) {
		this.VIN = vin;										// Set the VIN
	}
	
	public void setCarMake(String make) {
		this.MAKE = make;									// Set the Make
	}
	
	public void setCarModel (String model) {
		this.MODEL = model;									// Set the Model
	}

	public void setCarColor(String color) {
		this.COLOR = color;									// Set the Color
	}
	
	public void setCarPrice (int price) {
		this.PRICE = price;									// Set the Price
	}
	
	public void setCarMilage (int milage) {
		this.MILAGE = milage;								// Set the Milage
	}	
}

// End of Class
// Neil Patel
