package uber;

/**
 * Trip contains all relevant information about a single Uber trip
 * Allows for information to be easily passed 
 * Different Uber trips can extend this and overwrite distance calculation methods
 * 
 * @author Tim Lee
 *
 */

public class Trip {
	public static final double DEFAULT_PRICE = 10.00;
	
	private Passenger passenger;
	private Driver driver;
	private Location destination;
	private Location driverStart;
	private Location passengerStart;
	private double fare;
	private boolean canceled;

	/** 
	 * Constructor without given price uses default price
	 * Calls getTotalDist() method because extending classes may implement differently
	 * 
	 * @param passenger the Passenger for the Trip
	 * @param driver the Driver for the Trip
	 * @param destination the Location the Driver will take the Passenger
	 */
	public Trip(Passenger passenger, Driver driver, Location destination) {
		driver.setAvailable(false);
		
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		driverStart = driver.getLoc();
		passengerStart = passenger.getLoc();
		
		fare = getTotalDist() * DEFAULT_PRICE;
		canceled = false;
	}
	
	public Trip(Passenger passenger, Driver driver, Location destination, double price) {
		driver.setAvailable(false);
		
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		driverStart = driver.getLoc();
		passengerStart = passenger.getLoc();
		
		fare = getTotalDist() * price;
		canceled = false;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}

	public Driver getDriver() {
		return driver;
	}

	public Location getDestination() {
		return destination;
	}
	
	public Location getDriverStart() {
		return driverStart;
	}
	
	public Location getPassengerStart() {
		return passengerStart;
	}
	
	/**
	 * Calculates the distance for the first leg of the trip (driver to passenger)
	 * @return the distance from driver to passenger
	 */
	public double getInitDist() {
		return this.driver.getLoc().getDistance(this.passenger.getLoc());
	}
	
	/**
	 * Calculates the distance for the second leg of the trip (passenger to destination)
	 * @return the distance from passenger to destination
	 */
	public double getDestDist() {
		return this.passenger.getLoc().getDistance(this.destination);
	}

	/**
	 * Calculates the total distance of the trip
	 * @return total trip distance
	 */
	public double getTotalDist() {
		return getInitDist() + getDestDist();
	}
	
	public void setPrice(double price) {
		fare = getTotalDist() * price;
	}
	
	public double getFare() {
		return fare;
	}
	
	/**
	 * Returns the trip's fare as a String formateed as $XX.XX
	 * @return the fare as a formatted price
	 */
	public String getFareFormatted() {
		return String.format( "$%.2f", fare);
	}

	public void setCanceled(boolean bool) {
		canceled = bool;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
}
