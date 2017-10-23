package uber;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class UberIO {
	
	public static UsersList initialize(String fileName) {
		try {
			File file = new File(fileName);
			FileReader reader = new FileReader(file);
			
			JSONParser parser = new JSONParser();
		    Object obj = parser.parse(reader);
			JSONObject jobj = (JSONObject)obj;
			
			JSONArray pArray = (JSONArray)jobj.get("passengers");
			JSONArray dArray = (JSONArray)jobj.get("drivers");
			
			reader.close();
			
			//FILL IN HERE
			List<Passenger> passengers = parsePassengers(pArray);
			List<Driver> drivers = parseDrivers(dArray);
			
			return new UsersList(passengers, drivers);
		}
		catch(IOException e) {
			System.out.println("Initialize caught IOException: " + e);
			System.out.println(e.getStackTrace());
		}
		
		catch(ParseException e) {
			System.out.println("Initialize caught ParseException: " + e);
			System.out.println(e.getStackTrace());
		}	
		
		return null;
	}
	
	public static List<Passenger> parsePassengers(JSONArray pArray) {
		List<Passenger> passengers = new LinkedList<Passenger>();
		
		String name;
		double balance;
		JSONObject obj;
		Iterator iterator = pArray.iterator();
		while (iterator.hasNext()) {
			obj = (JSONObject)iterator.next();
			name = (String)obj.get("name");
			balance = (Double)obj.get("balance");
			passengers.add(new Passenger(name, balance));
		}
		return passengers;
	}
	
	public static List<Driver> parseDrivers(JSONArray dArray) {
		List<Driver> drivers = new LinkedList<Driver>();
		
		String name;
		double balance;
		String make;
		String model;
		int year;
		String plate;
		JSONObject obj;
		Iterator iterator = dArray.iterator();
		while (iterator.hasNext()) {
			obj = (JSONObject)iterator.next();
			name = (String)obj.get("name");
			balance = (Double)obj.get("balance");
			make = (String)obj.get("make");
			model = (String)obj.get("model");
			year = Long.valueOf((Long)obj.get("year")).intValue();
			plate = (String)obj.get("plate");
			drivers.add(new Driver(name, balance, new Title(make, model, year, plate)));
		}
		return drivers;
	}
	
	@SuppressWarnings("unchecked")
	public static void tripLog(List<Trip> trips, String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists())
				file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			JSONArray array = new JSONArray();
			for (Trip trip: trips) {
				array.add( encodeTrip(trip) );
			}
			
			array.writeJSONString(writer);
			writer.flush();
			writer.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject encodeTrip(Trip trip) {
		JSONObject obj = new JSONObject();
		obj.put("driver", trip.getDriver().getName());
		obj.put("passenger", trip.getPassenger().getName());
		obj.put("fare", trip.getFareFormatted());
	    obj.put("driverStart", trip.getDriverStart());
	    obj.put("passengerStart", trip.getPassengerStart());
	    obj.put("destination", trip.getDestination());
	    obj.put("driverBalance", trip.getDriver().getBalanceFormatted());
	    obj.put("passengerBalance", trip.getPassenger().getBalanceFormatted());
	    obj.put("canceled", trip.isCanceled());

	    return obj;
	}
	
	public static void finalOutput(List<Trip> trips, UsersList users, String fileName) {
		List<Passenger> passengers = users.getPassengers();
		List<Driver> drivers = users.getDrivers();
		int numTrips = trips.size();
		int numTripsSuccess = 0;
		double totalFare = 0;
		for (Trip trip : trips) {
			if (!trip.isCanceled()) {
				numTripsSuccess++;
				totalFare += trip.getFare();
			}
		}
		
		try {
			File file = new File(fileName);
			if (!file.exists())
				file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write("Total number of trips: ");
			writer.write(String.valueOf(numTrips));
			writer.newLine();
			writer.write("Number of successful trips: ");
			writer.write(String.valueOf(numTripsSuccess));
			writer.newLine();
			writer.write("Number of canceled trips: ");
			writer.write(String.valueOf(numTrips - numTripsSuccess));
			writer.flush();
			
			writer.newLine();
			writer.newLine();
			writer.write("Passengers:");
			for (Passenger pass: passengers) {
				writer.newLine();
				writer.write("\t");
				writer.write(pass.getName());
				writer.write(": ");
				writer.write(pass.getLoc().toString());
				writer.write(", ");
				writer.write(pass.getBalanceFormatted());
			}
			writer.flush();
			
			writer.newLine();
			writer.newLine();
			writer.write("Drivers:");
			for (Driver driver: drivers) {
				writer.newLine();
				writer.write("\t");
				writer.write(driver.getName());
				writer.write(": ");
				writer.write(driver.getLoc().toString());
				writer.write(", ");
				writer.write( String.format("%.2f", driver.getAvgRating()));
				writer.write(", ");
				writer.write(driver.getBalanceFormatted());
			}
			writer.flush();
			
			writer.newLine();
			writer.newLine();
			writer.write("Total amount of transactions: $");
			writer.write(String.format("%.2f", totalFare));
			writer.flush();
			
			writer.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
