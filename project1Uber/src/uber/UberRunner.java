package uber;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class UberRunner {
	private static final int SIZE = 300;
	private static final double RATE = 10.00; // Dollars
	private static final double SPEED = 25;
	
	public static void main(String[] args) {
		
		User[][] grid = new User[SIZE][SIZE];
		LinkedList<Driver> drivers = new LinkedList<Driver>();
		
		
	}
	
	/** 
	 * Creates a Trip for a given Passenger based on the list of given Drivers
	 * 
	 * @param passenger the passenger who will be riding
	 * @param drivers the list of all possible driver 
	 * @return a Trip object containing the passenger, selected location, and chosen driver
	 */
	public static Trip createTrip (Passenger passenger, LinkedList<Driver> drivers) {
		Location pickup = passenger.getLoc();
		Location dest = passenger.requestRide(SIZE);
		Driver driver;
		Double dist;
		
		TreeMap<Double, Driver> treemap = new TreeMap<Double, Driver>();
		Iterator<Driver> iterator = drivers.iterator();
		while (iterator.hasNext()) {
			driver = iterator.next();
			if (driver.isAvailable()) {
				dist = pickup.getDistance(driver.getLoc());
				treemap.put(dist, driver);
			}
		}
		
		for (Map.Entry<Double, Driver> entry : treemap.entrySet()) {
			driver = entry.getValue();
			if (driver.acceptsRequest()) {
				System.out.println(passenger.getName() + ", " + driver.getName() + " has accepted your request");
				return (new Trip(passenger, driver, dest));
			}
		}
		
		System.out.println(passenger.getName() + ", no driver in this area accepted your request");
		return null;
	}
	
	public static void executeTrip(Trip trip) {
		double fare = trip.getTotalDist() * RATE;
		Passenger passenger = trip.getPassenger();
		Driver driver = trip.getDriver();
		if (fare > passenger.getBalance()) {
			System.out.println(passenger.getName() + ", your request was canceled " +
					"because you lack sufficient funds ($" + String.format( "%.2f", fare));
			// Add functionality for adding canceled trip to log
		}
		else {
			passenger.changeBalance(fare * -1);
			driver.changeBalance(fare * 3/4);
			System.out.println(passenger.getName() + ", you have been charged $" + String.format( "%.2f", fare));;
			
			// Add timer functionality for travel times
			// Possibly implement multithreading for simultaneous trips for EC
			
		}
	}
	
}
