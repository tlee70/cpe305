package uber;

/**
 * Passenger class for Uber holds a name and balance
 * @author tlee70
 *
 */

import java.util.Random;

public class Passenger extends User{
	
	private Random random;
	
	public Passenger(String name, double balance) {
		super(name, balance);
		random = new Random();
	}
	
	public Passenger(String name, Location loc, double balance) {
		super(name, loc, balance);
		random = new Random();
	}
	
	/**
	 * Gives a 1-5 int rating for a Driver
	 * Currently random, could implement other method of rating
	 * @return an integer 1-5 (inclusive)
	 */
	public int giveRating() {
		return (random.nextInt(5) + 1);
	}
}
