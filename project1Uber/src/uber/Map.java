package uber;

/**
 * Map containing the location of Uber users
 * @author tlee70
 *
 */
public class Map {
	private User[][] map;
	
	public Map() {
		map = new User[300][300];
	}
	
	public void addUser(User user) {
		int x = user.getLoc().getX();
		int y = user.getLoc().getY();
		map[x][y] = user;
	}
	
	/**
	 * Finds the distance between two points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return straight line distance between given points
	 */
	public static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt( (x2-x1)^2 + (y2-y1)^2 );
	}
}
