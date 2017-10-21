package tests;

import org.junit.*;

import uber.Driver;
import uber.Location;
import uber.Title;

import static org.junit.Assert.*;

public class DriverTester {

	@Test
	public void receiveRatingTest() {
		Driver driver = new Driver(
				"Heathcliff",
				new Location(0,0),
				0.00,
				new Title("Mazda", "Mazda3", 2013, "4PEP216")
		);
		
		assertEquals(0, driver.getAvgRating(), 0.001);
		
		driver.receiveRating(5);
		assertEquals(5, driver.getAvgRating(), 0.001);

		driver.receiveRating(3);
		assertEquals(4, driver.getAvgRating(), 0.001);

		driver.receiveRating(4);
		assertEquals(4, driver.getAvgRating(), 0.001);

		driver.receiveRating(1);
		assertEquals(3.25, driver.getAvgRating(), 0.001);
		
	}
}
