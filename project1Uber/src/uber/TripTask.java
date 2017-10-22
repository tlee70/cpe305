package uber;

import java.util.TimerTask;

public class TripTask extends TimerTask {
	private static final long TIME = 1000;
	
	private Trip trip;
	
	public TripTask(Trip trip) {
		this.trip = trip;
	}
	
	public void run() {
		try {
			Passenger passenger = trip.getPassenger();
			Driver driver = trip.getDriver();
				
			long wait = (long)(TIME * trip.getInitDist());					
			System.out.println(passenger.getName() + ", driver " + driver.getName() +
					" is on the way. \n\tEstimated wait time: " + wait + " ms");
			Thread.sleep(wait);
				
			driver.setLoc(passenger.getLoc());
			wait = (long)(TIME * trip.getDestDist());
			System.out.println(passenger.getName() + ", driver " + driver.getName() +	
					" has arrived\n\tEstimated time to destination: " + wait + " ms");
			Thread.sleep(wait);
				
			driver.setLoc(trip.getDestination());
			passenger.setLoc(trip.getDestination());
			System.out.println(passenger.getName() + ", you have arrived at your destination."
					+ "\n\tPlease rate your driver");
			driver.receiveRating(passenger.giveRating());
			driver.setAvailable(true);
		}
		catch (InterruptedException e) {
		}
	}
}
