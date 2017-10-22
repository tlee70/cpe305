package tests;

import org.junit.*;
import static org.junit.Assert.*;

import uber.*;
import java.util.LinkedList;

public class UberRunnerTester {

	@Test
	public void createTripTester() {
		LinkedList<Driver> drivers = new LinkedList<Driver>();
		Driver Michael = new Driver("Michael", new Location(10,29), 0, null);
		drivers.add(Michael);
		Driver Gabriel = new Driver("Gabriel", new Location(6, 83), 0, null);
		drivers.add(Gabriel);
		Driver Raphael = new Driver("Raphael", new Location(49, 16), 0, null);
		drivers.add(Raphael);
		Driver Uriel = new Driver("Uriel", new Location(55, 8), 0, null);
		drivers.add(Uriel);
		
		Passenger passenger = new Passenger("Metatron", new Location(38, 22), 777);
		
		Trip trip = UberRunner.createTrip(passenger, new Location(5,6), drivers);
		
		assertEquals(Raphael, trip.getDriver());
		assertEquals(Math.sqrt(157), trip.getInitDist(), 0.001);
	}
	
	@Test
	public void executeTransactionCancelledTester() {
		Passenger passenger = new Passenger("Mal", new Location(6,18), 10.00);
		Driver driver = new Driver("Inara", new Location(22,3), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(15,16));
		
		assertFalse(UberRunner.executeTransaction(trip));
	}
	
	@Test
	public void executeTransactionValidTester() {
		Passenger passenger = new Passenger("Clyde", new Location(3,4), 9999.00);
		Driver driver = new Driver("Bonnie", new Location(4,3), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(5,5));
		
		assertTrue(UberRunner.executeTransaction(trip));
	}
	

}
