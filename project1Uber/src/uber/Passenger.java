package uber;

/**
 * Passenger class for Uber holds a name and balance
 * @author tlee70
 *
 */
public class Passenger extends User{
	
	public Passenger(String name, Location loc, double balance) {
		super(name, loc, balance);
	}
	
	public void pay(double fare) {
		balance-=fare;
	}
}
