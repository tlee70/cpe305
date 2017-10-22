package uber;

public class Trip {
	public static final double DEFAULT_PRICE = 10.00;
	
	
	private Passenger passenger;
	private Driver driver;
	private Location destination;
	private double initDist;
	private double destDist;
	private double fare;

	public Trip(Passenger passenger, Driver driver, Location destination) {
		driver.setAvailable(false);
		
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		initDist = this.driver.getLoc().getDistance(this.passenger.getLoc());
		destDist = this.passenger.getLoc().getDistance(this.destination);
		
		fare = getTotalDist() * DEFAULT_PRICE;
	}
	
	public Trip(Passenger passenger, Driver driver, Location destination, double price) {
		driver.setAvailable(false);
		
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		initDist = this.driver.getLoc().getDistance(this.passenger.getLoc());
		destDist = this.passenger.getLoc().getDistance(this.destination);
		
		fare = getTotalDist() * price;
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
	
	public double getInitDist() {
		return initDist;
	}
	
	public double getDestDist() {
		return destDist;
	}

	public double getTotalDist() {
		return initDist + destDist;
	}
	
	public void setPrice(double price) {
		fare = getTotalDist() * price;
	}
	
	public double getFare() {
		return fare;
	}

}
