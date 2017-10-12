package uber;

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
	
	public double getDistance(Location other) {
		return Math.sqrt( (this.x-other.getX())^2 + (this.y-other.getY())^2 );
	}
}
