package tests;

import org.junit.*;

import uber.Driver;
import uber.Location;
import uber.Passenger;
import uber.Trip;

import static org.junit.Assert.*;

public class TripTester {

	@Test
	public void tripFareTest() {
		Passenger passenger = new Passenger("Rosencrantz", new Location(5,12), 200);
		Driver driver = new Driver("Guildenstern", new Location(0,0), 50, null);
		Location dest = new Location(2, 16);
		
		Trip trip = new Trip(passenger, driver, dest);
		
		assertEquals(13.0, trip.getInitDist(), 0.001);
		assertEquals(18.0, trip.getTotalDist(), 0.001);
		assertEquals(197.10, trip.getFare(10.95), 0.01);
	}
}
