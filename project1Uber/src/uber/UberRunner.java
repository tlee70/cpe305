package uber;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.TimerTask;

public class UberRunner {
	private static final int GRIDSIZE = 300;
	private static final double PRICE = 10.00; // Dollars
	private static final long TIME = 1000; // milliseconds per square
	
	
	public static void main(String[] args) {
		User[][] grid = new User[GRIDSIZE][GRIDSIZE];
		LinkedList<Driver> drivers = new LinkedList<Driver>();
		
		/**
		 * Runnable r1 = new TripTask();
		 * Thread t1 = new Thread(r1);
		 * t1.start();
		 */
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
		Location dest = passenger.selectRandomDestination(GRIDSIZE);
		return createTrip(passenger, dest, drivers);
	}
	
	public static boolean executeTransaction(Trip trip) {
		double fare = trip.getFare();
		Passenger passenger = trip.getPassenger();
		Driver driver = trip.getDriver();
		if (fare > passenger.getBalance()) {
			System.out.println(passenger.getName() + ", your request was canceled " +
					"because you lack sufficient funds ($" + String.format( "%.2f", fare)
					+ " required, possess $" + String.format( "%.2f", passenger.getBalance())
					+ ")");
			
			driver.setAvailable(true);
			return false;
		}
		else {
			passenger.changeBalance(fare * -1);
			driver.changeBalance(fare * 3/4);
			System.out.println(passenger.getName() + ", you have been charged $" 
					+ String.format( "%.2f", fare));
			
			return true;
		}
	}
	
	/**
	class TripTask extends TimerTask {
		Trip trip;
		
		public TripTask(Trip trip) {
			this.trip = trip;
		}
		
		public void run() {
			try {
				Passenger passenger = trip.getPassenger();
				Driver driver = trip.getDriver();
					
				long wait = (long)(TIME * trip.getInitDist());					
				System.out.println(passenger.getName() + ", driver " + driver.getName() +
						" is on the way. \n\tEstimated wait time: " + wait + " ms");
				Thread.sleep(wait);
					
				driver.setLoc(passenger.getLoc());
				wait = (long)(TIME * trip.getDestDist());
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
			}
		}
	}
	*/
	
}
