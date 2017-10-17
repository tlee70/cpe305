package uber;

/**
 * Comparator for sorting Drivers by their distance from a given Location
 * If 2 Drivers are the same distance (within specified bounds), sorts by avgRating
 * Used to order Drivers in a PriorityQueue for uber Passenger pickup
 */

import java.util.Comparator;

public class DriverDistanceComparator implements Comparator<Driver> {
	
	private Location origin;
	
	public DriverDistanceComparator(Location origin) {
		this.origin = origin;
	}
	
	public int compare(Driver A, Driver B) {
		double a = origin.getDistance(A.getLoc());
		double b = origin.getDistance(B.getLoc());
		
		/**
		 *  error of +- 0.001 
		 *  Drivers at same distance (within bounds) sorted by avgRating
		 */
		if (Math.abs(a-b) < 0.001) {
			a = A.getAvgRating();
			b = B.getAvgRating();
			
			if (Math.abs(a-b) < 0.001)
				return 0;
			else if (a > b)
				return -1;
			else
				return 1;
		}
		else if (a < b)
			return -1;
		else
			return 1;
			
	}
}
