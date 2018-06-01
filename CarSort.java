/*
Neil Patel
COE 1501; Algorithms
Spring 2018 Semester
GitHub: neilpatel
*/

// My version of Comparable interface for CarSort

public class CarSort implements Comparable<CarSort> {
	int index;
	int sortCarValue;

	// Override the compareTo method in the Comparable Interface with necessary values
	@Override
	public int compareTo(CarSort car) {
		//Conditional to check if the car object value is less than the value in the compareTo method
			//If so, return -1 (indicates its less than)
		if(car.sortCarValue > this.sortCarValue) {
			return -1;
		}
		//Conditional to check if the car object value is equal to the value in the compareTo method
			//If so, return 0 (indicates its equal to)
		else if (car.sortCarValue == this.sortCarValue) {
			return 0;
		}
		//Conditional to check if the car object value is greater than the value in the compareTo method
			//If so, return 1 (indicates its greater than)
		else {
			return 1;
		}
	}
	
	// Create constructor CarSort and pass in two values
		// Index j and sortCarValue
	public CarSort(int j, int sortCarV){
		this.index = j;
		this.sortCarValue = sortCarV;
	}
}

// End of Class
// Neil Patel
