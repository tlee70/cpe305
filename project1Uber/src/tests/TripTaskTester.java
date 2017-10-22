package tests;

import org.junit.*;
import static org.junit.Assert.*;

import uber.*;

public class TripTaskTester {

	@Test
	public void tripTaskTester() {
		Passenger passenger = new Passenger("Clyde", new Location(3,4), 9999.00);
		Driver driver = new Driver("Bonnie", new Location(4,3), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(5,5));
		
		TripTask task = new TripTask(trip);
		task.run();
	}
	
	@Test
	public void multithreadTester() {
		Passenger passenger = new Passenger("Clyde", new Location(3,4), 9999.00);
		Driver driver = new Driver("Bonnie", new Location(4,3), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(5,5));
		TripTask task1 = new TripTask(trip);
		Thread t1 = new Thread(task1);
		
		passenger = new Passenger("Jerry", new Location(16, 32), 9999.00);
		driver = new Driver("Tom", new Location(24,21), 0.0, null);
		trip = new Trip(passenger, driver, new Location(19, 39));
		TripTask task2 = new TripTask(trip);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
	}
}
