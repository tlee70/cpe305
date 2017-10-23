package tests;

import org.junit.*;
import static org.junit.Assert.*;

import uber.*;
import java.util.LinkedList;

public class UberAppTester {

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
		
		Trip trip = UberApp.createTrip(passenger, new Location(5,6), drivers);
		
		assertEquals(Raphael, trip.getDriver());
		assertEquals(Math.sqrt(157), trip.getInitDist(), 0.001);
	}
	
	@Test
	public void executeTransactionCancelledTester() {
		Passenger passenger = new Passenger("Mal", new Location(6,18), 10.00);
		Driver driver = new Driver("Inara", new Location(22,3), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(15,16));
		
		UberApp.executeTransaction(trip);
		assertTrue(trip.isCanceled());
		assertEquals(10.0, passenger.getBalance(), 0.01);
		assertEquals(0.0, driver.getBalance(), 0.01);
	}
	
	@Test
	public void executeTransactionValidTester() {
		Passenger passenger = new Passenger("Clyde", new Location(3,4), 9999.00);
		Driver driver = new Driver("Bonnie", new Location(6,8), 0.0, null);
		Trip trip = new Trip(passenger, driver, new Location(0,0), 1.0);
		
		UberApp.executeTransaction(trip);
		assertFalse(trip.isCanceled());
		assertEquals(10.00, trip.getFare(), 0.01);
		assertEquals(9989.00, passenger.getBalance(), 0.01);
		assertEquals(7.50, driver.getBalance(), 0.01);
	}
	
	@Test
	public void runnerTester() {
		LinkedList<Passenger> passengers = new LinkedList<Passenger>();
		Passenger Jerry = new Passenger("Jerry", new Location(16, 32), 9999.00);
		passengers.add(Jerry);
		Passenger Li = new Passenger("Li", new Location(3,4), 9999.00);
		passengers.add(Li);
		Passenger Artemis = new Passenger("Artemis", new Location(254, 102), 999999.99);
		passengers.add(Artemis);
		
		LinkedList<Driver> drivers = new LinkedList<Driver>();
		Driver Michael = new Driver("Michael", new Location(10,29), 0, null);
		drivers.add(Michael);
		Driver Gabriel = new Driver("Gabriel", new Location(6, 83), 0, null);
		drivers.add(Gabriel);
		Driver Raphael = new Driver("Raphael", new Location(49, 16), 0, null);
		drivers.add(Raphael);
		Driver Uriel = new Driver("Uriel", new Location(55, 8), 0, null);
		drivers.add(Uriel);
		
		UberApp.runTrips(new UsersList(passengers, drivers));
		System.out.println("Michael's Rating: " + Michael.getAvgRating());
	}
}
