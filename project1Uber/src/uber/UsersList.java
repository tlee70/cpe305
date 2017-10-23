package uber;

import java.util.List;
import java.util.LinkedList;

public class UsersList {
	private List<Passenger> passengers;
	private List<Driver> drivers;
	private List<User> allUsers;
	
	public UsersList(List<Passenger> passengers, List<Driver> drivers) {
		this.passengers = passengers;
		this.drivers = drivers;
		
		allUsers = new LinkedList<User>();
		for (Passenger passenger: passengers)
			allUsers.add(passenger);
		for (Driver driver: drivers)
			allUsers.add(driver);
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}
	
	public List<User> getAllUsers() {
		return allUsers;
	}
}
