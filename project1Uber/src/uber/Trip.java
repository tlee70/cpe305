package uber;

public class Trip {
	private final double RATE = 10.00;
	
	private Passenger passenger;
	private Driver driver;
	private Location destination;
	private double initDist;
	private double totalDist;
	private double fare;

	public Trip(Passenger passenger, Driver driver, Location destination) {
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
		
		initDist = this.driver.getLoc().getDistance(this.passenger.getLoc());
		totalDist = initDist + this.passenger.getLoc().getDistance(this.destination);
		fare = totalDist * RATE;
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

	public double getFare() {
		return fare;
	}

	public void charge() {		
		driver.changeBalance(fare*-1);
		passenger.changeBalance(fare * 0.75);
	}
	
}
