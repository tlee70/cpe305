package uber;

public class Trip {
	
	private Passenger passenger;
	private Driver driver;
	private Location destination;
	private double initDist;
	private double totalDist;

	public Trip(Passenger passenger, Driver driver, Location destination) {
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		initDist = this.driver.getLoc().getDistance(this.passenger.getLoc());
		totalDist = initDist + this.passenger.getLoc().getDistance(this.destination);
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

	public double getTotalDist() {
		return totalDist;
	}
	
}
