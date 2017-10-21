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
		double x2 = Math.pow((this.x - other.getX()),2);
		double y2 = Math.pow((this.y - other.getY()),2);
		
		return Math.sqrt(x2+y2);
	}
}
