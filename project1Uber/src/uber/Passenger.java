package uber;

/**
 * Passenger class for Uber holds a name and balance
 * @author tlee70
 *
 */

import java.util.Random;

public class Passenger extends User{
	
	private Random random;
	
	public Passenger(String name, Location loc, double balance) {
		super(name, loc, balance);
		random = new Random();
	}
	
	public Location requestRide(int gridSize) {
		int x = random.nextInt(gridSize);
		int y = random.nextInt(gridSize);
		
		return new Location(x,y);
	}
	
	public int giveRating() {
		return (random.nextInt(5) + 1);
	}
}
