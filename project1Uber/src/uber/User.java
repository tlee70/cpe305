package uber;

/**
 * User of Uber is an abstract parent of Passenger and Driver
 * @author tlee70
 *
 */
public abstract class User {
	protected String name;
	protected double balance;
	protected Location loc;
	
	public User(String name, Location loc, double balance) {
		this.name = name;
		this.loc = loc;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public void changeLoc(Location loc) {
		this.loc = loc;
	}

	public double getBalance() {
		return balance;
	}
	
	public void changeBalance(double change) {
		balance += change;
	}
 }
