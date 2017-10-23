package tests;

import uber.*;
import org.json.simple.*;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.LinkedList;

public class UberIOTester {

	@Test
	public void encodeTripTester() {
		Location driverStart = new Location(16,299);
		Location passengerStart = new Location(175, 106);
		Location dest = new Location(159,36);
		Driver driver = new Driver("Phyllis", driverStart, 63.19, null);
		Passenger passenger = new Passenger("Dilbert", passengerStart, 548);
		
		Trip trip = new Trip(passenger, driver, dest);
		JSONObject obj = UberIO.encodeTrip(trip);
		
		assertEquals(driver.getName(),obj.get("driver"));
		assertEquals(passenger.getName(), obj.get("passenger"));
		assertEquals("$3218.65", obj.get("fare"));
	    assertEquals(driverStart, obj.get("driverStart"));
	    assertEquals(passengerStart, obj.get("passengerStart"));
	    assertEquals(dest, obj.get("destination"));
	    assertEquals("$548.00", obj.get("passengerBalance"));
		assertEquals("$63.19", obj.get("driverBalance"));
		assertFalse((Boolean)obj.get("canceled"));
	}
	
	@Test
	public void outputTester() {
		LinkedList<Passenger> passengers = new LinkedList<Passenger>();
		Passenger Virgil = new Passenger("Virgil", new Location(16, 32), 9999.00);
		passengers.add(Virgil);
		Passenger Dante = new Passenger("Dante", new Location(3,4), 9999.00);
		passengers.add(Dante);
		Passenger Beatrice = new Passenger("Beatrice", new Location(254, 102), 105.62);
		passengers.add(Beatrice);
		
		LinkedList<Driver> drivers = new LinkedList<Driver>();
		Driver Michael = new Driver("Michael", new Location(10,29), 0, null);
		drivers.add(Michael);
		Driver Gabriel = new Driver("Gabriel", new Location(6, 83), 0, null);
		drivers.add(Gabriel);
		Driver Raphael = new Driver("Raphael", new Location(49, 16), 0, null);
		drivers.add(Raphael);
		Driver Uriel = new Driver("Uriel", new Location(55, 8), 0, null);
		drivers.add(Uriel);
		
		UsersList users = new UsersList(passengers, drivers);
		
		List<Trip> trips = UberRunner.runTrips(users);
		
		UberIO.tripLog(trips, "out/tripLog.json");
		UberIO.finalOutput(trips, users, "out/finalOutput.txt");
		assertTrue(true);
	}
}
