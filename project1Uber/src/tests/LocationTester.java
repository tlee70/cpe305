package tests;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import uber.Location;

public class LocationTester {

	@Test
	public void getDistanceYTest() {
		Location a = new Location (0,0);
		Location b = new Location (0,5);
		assertEquals(5.0, a.getDistance(b), 0.001);
	}
	
	@Test
	public void getDistanceXTest() {
		Location a = new Location(0,0);
		Location b = new Location(-7, 0);
		assertEquals(7.0, a.getDistance(b), 0.001);
	}
	
	@Test
	public void getDistanceXYTest() {
		Location a = new Location(0,0);
		Location b = new Location(3,4);
		assertEquals(5.0, a.getDistance(b), 0.001);
	}
	
	@Test
	public void getDistanceMixedTest() {
		Location a = new Location(13, -92);
		Location b = new Location(18, -80);
		assertEquals(13.0, a.getDistance(b), 0.001);
	}
}
