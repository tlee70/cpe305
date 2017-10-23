package uber;

/**
 * Driver for Uber
 * @author tlee70
 *
 */

public class Driver extends User{
	private Title title;
	private boolean available;
	private double avgRating;
	private int numRatings; // necessary to keep running average
	
	public Driver(String name, double balance, Title title) {
		super(name, balance);
		this.title = title;
		available = true;
		avgRating = 0;
		numRatings = 0;
	}
	
	public Driver(String name, Location loc, double balance, Title title) {
		super(name, loc, balance);
		this.title = title;
		available = true;
		avgRating = 0;
		numRatings = 0;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Title getTitle() {
		return title;
	}
	
	public double getAvgRating() {
		return avgRating;
	}
	
	public void receiveRating(int rating) {
		numRatings++;
		avgRating = avgRating + ((double)rating - avgRating)/numRatings;
	}
	
	public boolean acceptsRequest() {
		return true;
	}
}
