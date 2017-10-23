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
	
	/**
	 * Receives a rating and uses it to update the average rating
	 * Does not keep track of all ratings, uses a formula to update
	 * Formula does require number of ratings, but still uses less memory than array of all ratings
	 * 
	 * @param rating the received rating
	 */
	public void receiveRating(int rating) {
		numRatings++;
		avgRating = avgRating + ((double)rating - avgRating)/numRatings;
	}
	
	/**
	 * Decides whether or not to accept a request
	 * Currently always true for ease of testing 
	 * Can easily implement a Random if desired
	 * @return boolean representing whether or not the request is accepted
	 */
	public boolean acceptsRequest() {
		return true;
	}
}
