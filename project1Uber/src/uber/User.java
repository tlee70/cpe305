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
	
	/**
	 * Constructor without Location inputs defaults to (0,0)
	 * Initializer lacks knowledge of Locations and of GRIDSIZE, can set later
	 * 
	 * @param name the name of the User
	 * @param balance the initial balance
	 */
	public User(String name, double balance) {
		this.name = name;
		this.balance = balance;
		loc = new Location(0,0);
	}
	
	/**
	 * Constructor with Location for testing purposes
	 * 
	 * @param name the name of the User
	 * @param loc the initial Location of the User
	 * @param balance the initial balance
	 */
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
	
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public double getBalance() {
		return balance;
	}
	
	/**
	 * Formats the balance as a string to the cent
	 * @return the formatted balance
	 */
	public String getBalanceFormatted() {
		return String.format( "$%.2f", balance);
	}
	
	/**
	 * Adds the given amount to the current balance
	 * @param change the amount to change the balance by
	 */
	public void changeBalance(double change) {
		balance += change;
	}
 }
