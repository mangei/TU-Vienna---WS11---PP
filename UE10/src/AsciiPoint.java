
/**
 * Uebungsrunde 7
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 *
 */
public class AsciiPoint {

	private int x;		// x position
	private int y;		// y position

	/**
	 * Creates a new point
	 * @param x x position
	 * @param y y position
	 */
	public AsciiPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns x position
	 * 
	 * @return x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns y position
	 * 
	 * @return y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns string representation of point
	 * 
	 * @return string representation of point
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}