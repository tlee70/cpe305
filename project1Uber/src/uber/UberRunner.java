package uber;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.Random;

public class UberRunner {
	private static final int GRIDSIZE = 300;
	private static final double PRICE = 1.00; // Dollars
	private static final long TIME = 100;
	private static Random random = new Random();
	
	public static void main(String[] args) {
		String initialFile = "in/initializationJSON.txt";
		String tripLog = "out/tripLog.json";
		String outputFile = "out/finalOutput.txt";
		
		UsersList users = UberIO.initialize(initialFile);
		randomizeLocations(users.getAllUsers());
		
		List<Trip> trips = runTrips(users);
		
		UberIO.tripLog(trips, tripLog);
		UberIO.finalOutput(trips, users, outputFile);
	}
	
	public static List<Trip> runTrips(UsersList users) {
		List<Trip> trips = new LinkedList<Trip>();
		List<Thread> threads = new LinkedList<Thread>();
		List<Passenger> passengers = users.getPassengers();
		List<Driver> drivers = users.getDrivers();
		
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
	 * Creates a Trip for a given Passenger based on the list of given Drivers
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
	 
	public static void randomizeLocations(List<User> users) {
		User user;
		
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			user = iterator.next();
			user.setLoc(selectRandomLocation());
		}
	}
	
	public static Location selectRandomLocation() {
		int x = random.nextInt(GRIDSIZE);
		int y = random.nextInt(GRIDSIZE);
		
		return new Location(x,y);
	}
	
	static class TripTask extends TimerTask {
		Trip trip;
		
		public TripTask(Trip trip) {
			this.trip = trip;
		}
		
		public void run() {
			leg1();
			leg2();
		}
			
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
			}
		}
		
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
			}
		}
	}
}
