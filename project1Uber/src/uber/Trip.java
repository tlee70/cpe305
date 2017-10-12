package uber;

public class Trip {
	private Passenger passenger;
	private Driver driver;
	private Location destination;

	public Trip(Passenger passenger, Driver driver, Location destination) {
		this.passenger = passenger;
		this.driver = driver;
		this.destination = destination;
	}
	
	public double calculateCost(double rate) {
		double cost = 0;
		
		cost += driver.getLoc().getDistance(passenger.getLoc()) * rate;
		cost += passenger.getLoc().getDistance(destination) * rate;
		
		return cost;
	}
	
	public void charge(double rate) {
		double fare = calculateCost(rate);
		
		driver.payment(fare);
		passenger.pay(fare);
	}
}
