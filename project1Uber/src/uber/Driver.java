package uber;

/**
 * Driver for Uber
 * @author tlee70
 *
 */
public class Driver extends User{
	private Title title;
	private boolean available;
	private double rating;
	
	public Driver(String name, Location loc, double balance, Title title) {
		super(name, loc, balance);
		this.title = title;
		available = true;
		rating = 0;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void payment(double fare) {
		balance+=fare;
	}

	public Title getTitle() {
		return title;
	}
	
	public double getRating() {
		return rating;
	}
}
