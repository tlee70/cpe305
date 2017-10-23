package uber;

import java.lang.StringBuilder;

/**
 * Location contains an X and Y coordinate 
 * Classes keep track of their Location instead of a Grid keeping track of them
 * 
 * @author Tim
 *
 */

public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * Calculates the distance to another Location
	 * @param other the other Location
	 * @return the distance between this Location and the given one
	 */
	public double getDistance(Location other) {
		double x2 = Math.pow((this.x - other.getX()),2);
		double y2 = Math.pow((this.y - other.getY()),2);
		
		return Math.sqrt(x2+y2);
	}
	
	public boolean equals(Object other) {
		if (other instanceof Location) {
			Location loc = (Location) other;
			if (loc.getX() == this.x && loc.getY() == this.y)
				return true;
		}
		
		return false;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("(");
		builder.append(x);
		builder.append(", ");
		builder.append(y);
		builder.append(")");
		
		return builder.toString();
	}
}
