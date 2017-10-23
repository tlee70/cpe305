package uber;

public class Trip {
	public static final double DEFAULT_PRICE = 10.00;
	
	private Passenger passenger;
	private Driver driver;
	private Location destination;
	private Location driverStart;
	private Location passengerStart;
	private double fare;
	private boolean canceled;

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
	
	public double getInitDist() {
		return this.driver.getLoc().getDistance(this.passenger.getLoc());
	}
	
	public double getDestDist() {
		return this.passenger.getLoc().getDistance(this.destination);
	}

	public double getTotalDist() {
		return getInitDist() + getDestDist();
	}
	
	public void setPrice(double price) {
		fare = getTotalDist() * price;
	}
	
	public double getFare() {
		return fare;
	}
	
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
