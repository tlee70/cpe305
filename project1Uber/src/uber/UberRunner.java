package uber;

import java.util.List;

/**
 * Main method that runs entire program
 * @author Tim Lee
 *
 */

public class UberRunner {
	public static void main(String[] args) {
		String initialFile = "in/initializationJSON.txt";
		String tripLog = "out/tripLog.json";
		String outputFile = "out/finalOutput.txt";
		
		UsersList users = UberIO.initialize(initialFile);
		UberApp.randomizeLocations(users.getAllUsers());
		
		List<Trip> trips = UberApp.runTrips(users);
		
		UberIO.tripLog(trips, tripLog);
		UberIO.finalOutput(trips, users, outputFile);
	}
}
