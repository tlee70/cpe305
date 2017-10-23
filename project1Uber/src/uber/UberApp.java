package uber;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Random;

/**
 * UberApp connects the various methods and classes of the package into one cohesive program 
 * Represents the overall app connecting the Drivers and Passengers
 * 
 * @author Tim Lee
 *
 */

public class UberApp {
	private static final int GRIDSIZE = 300;
	private static final double PRICE = 1.00; // Dollars per distance unit
	private static final long TIME = 100; // Milliseconds per distance unit
	private static Random random = new Random();
	
	/**
	 * Creates and runs trips for all Passengers based on the given set of users
	 * Utilizes various helper methods in this class
	 * Multithreading allows Trips to run in parallel
	 * 
	 * @param users the set of all Passengers and Drivers to monitor
	 * @return the List of all Trips, completed or cancelled
	 */
	public static List<Trip> runTrips(UsersList users) {
		List<Passenger> passengers = users.getPassengers();
		List<Driver> drivers = users.getDrivers();
		
		List<Trip> trips = new LinkedList<Trip>();
		List<Thread> threads = new LinkedList<Thread>();
		
		Passenger passenger;
		Trip trip;
		Thread thread;
		Iterator<Passenger> iterator = passengers.iterator();
		while (iterator.hasNext()) {
			passenger = iterator.next();
			trip = createRandomTrip(passenger, drivers);
			trips.add(trip);
			
			executeTransaction(trip); 
			if (!trip.isCanceled()){
				thread = new Thread(new TripTask(trip));
				threads.add(thread);
				thread.start();
			}
		}
		
		// Waits for all threads(trips) to complete before moving on
		try {
			for (Thread wait: threads) {
				wait.join();
			}
		}
		catch (InterruptedException e) {
			System.out.println(e.getStackTrace());
		}
		
		return trips;
	}
	
	/** 
	 * Creates a Trip for a given Passenger based on the given List of Drivers
	 * Sorts Drivers by proximity and has them accept/decline in priority order
	 * Informs Passenger when a Driver accepts request or when all Drivers have declined
	 * 
	 * @param passenger the Passenger who will be riding
	 * @param dest the chosen Location to take the Passenger
	 * @param drivers the list of all possible Drivers 
	 * @return a Trip object containing the passenger, selected location, and chosen driver
	 */
	public static Trip createTrip (Passenger passenger, Location dest, List<Driver> drivers) {
		Location pickup = passenger.getLoc();
		Driver driver;
		Double dist;
		
		// Creates TreeMap to sort Drivers from nearest to farthest from Passenger
		TreeMap<Double, Driver> treemap = new TreeMap<Double, Driver>();
		Iterator<Driver> iterator = drivers.iterator();
		while (iterator.hasNext()) {
			driver = iterator.next();
			if (driver.isAvailable()) {
				dist = pickup.getDistance(driver.getLoc());
				treemap.put(dist, driver);
			}
		}
		
		// Drivers given option to accept/decline request in order of decreasing priority
		for (Map.Entry<Double, Driver> entry : treemap.entrySet()) {
			driver = entry.getValue();
			if (driver.acceptsRequest()) {
				System.out.println(passenger.getName() + ", driver " + driver.getName() + 
						" has accepted your request");
				return (new Trip(passenger, driver, dest, PRICE));
			}
		}
		
		System.out.println(passenger.getName() + ", no driver in this area accepted your request");
		return null;
	}
	
	/**
	 * Creates a trip with a randomly selected destination within the grid
	 * @param passenger the passenger who will be riding
	 * @param drivers the list of all possible driver 
	 * @return a Trip object containing the passenger, selected location, and chosen driver
	 */
	public static Trip createRandomTrip(Passenger passenger, List<Driver> drivers) {
		Location dest = selectRandomLocation();
		return createTrip(passenger, dest, drivers);
	}
	
	/**
	 * Alters the balance of a Trip's Driver and Passenger
	 * Cancels the Trip if the Passenger has insufficient balance
	 * Passenger pays fare, Driver receives 3/4 of fare
	 * 
	 * Structurally may have fit better in Trip class, but informing Passengers makes it fit better organizationally in this class
	 * 
	 * @param trip the trip to oversee the transaction for
	 */
	public static void executeTransaction(Trip trip) {
		double fare = trip.getFare();
		Passenger passenger = trip.getPassenger();
		Driver driver = trip.getDriver();
		if (fare > passenger.getBalance()) {
			System.out.println(passenger.getName() + ", your request was canceled " +
					"because you lack sufficient funds (" + trip.getFareFormatted()
					+ " required, possess " + passenger.getBalanceFormatted() + ")");
			
			driver.setAvailable(true);
			trip.setCanceled(true);
		}
		else {
			passenger.changeBalance(fare * -1);
			driver.changeBalance(fare * 3/4);
			System.out.println(passenger.getName() + ", you have been charged " 
					+ trip.getFareFormatted());
			trip.setCanceled(false);
		}
	}
	
	/**
	 * Assigns a random Location to all Users in the List
	 * Initialize() method in UberIO lacks information about grid size, so cannot randomly assign locations
	 * @param users the List of Users to assign to random Locations in the grid
	 */
	public static void randomizeLocations(List<User> users) {
		User user;
		
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			user = iterator.next();
			user.setLoc(selectRandomLocation());
		}
	}
	
	/**
	 * Helper method creates a random Location within grid boundaries
	 * @return a random Location
	 */
	public static Location selectRandomLocation() {
		int x = random.nextInt(GRIDSIZE);
		int y = random.nextInt(GRIDSIZE);
		
		return new Location(x,y);
	}
	
	/**
	 * Nested inner class for multithreading purposes
	 * @author Tim Lee
	 *
	 */
	static class TripTask extends TimerTask {
		Trip trip;
		
		public TripTask(Trip trip) {
			this.trip = trip;
		}
		
		/**
		 * Run implemented as per Runnable interface
		 * Calls on 2 helper methods to split code into more manageable sections
		 */
		public void run() {
			leg1();
			leg2();
		}
		
		/**
		 * Represents the first leg of the trip, Driver going to pick up Passenger
		 */
		public void leg1() {	
			try {
				Passenger passenger = trip.getPassenger();
				Driver driver = trip.getDriver();
					
				long wait = (long)(TIME * trip.getInitDist());					
				System.out.println(passenger.getName() + ", driver " + driver.getName() +
						" is on the way. \n\tEstimated wait time: " + wait + " ms");
				Thread.sleep(wait);
					
				driver.setLoc(passenger.getLoc());
			}
			catch (InterruptedException e) {
				System.out.println(e);
				System.out.println(e.getStackTrace());
			}
		}
		
		/**
		 * Represents the second leg of the trip, Driver taking Passenger to destination
		 */
		public void leg2() {
			try {
				Passenger passenger = trip.getPassenger();
				Driver driver = trip.getDriver();
					
				long wait = (long)(TIME * trip.getDestDist());
				System.out.println(passenger.getName() + ", driver " + driver.getName() +	
						" has arrived\n\tEstimated time to destination: " + wait + " ms");
				Thread.sleep(wait);
					
				driver.setLoc(trip.getDestination());
				passenger.setLoc(trip.getDestination());
				System.out.println(passenger.getName() + ", you have arrived at your destination."
						+ "\n\tPlease rate your driver");
				driver.receiveRating(passenger.giveRating());
				driver.setAvailable(true);
			}
			catch (InterruptedException e) {
				System.out.println(e);
				System.out.println(e.getStackTrace());
			}
		}
	}
}
