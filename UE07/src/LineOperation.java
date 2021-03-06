
/**
 * Line Operation
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class LineOperation implements Operation {

	private int x0;
	private int y0;
	private int x1;
	private int y1;
	private char c;
	
	/**
	 * Creates a new LineOperation.
	 */
	public LineOperation(int x0, int y0, int x1, int y1, char c) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.c = c;
	}
	
	/**
	 * Executes this LineOperation and returns as new AsciiImage.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		AsciiImage result = new AsciiImage(img);

		// calculate delta x and delta y
		double deltaX = x1 - x0;
		double deltaY = y1 - y0;
		
		// axis swaped flag
		boolean axisSwaped = false;
		
		// check if the axis are flipped
		// if yes, soap x0 with y0, x1 with y1 and delta x with delta y and set the swaped flag
		if(Math.abs(deltaY) > Math.abs(deltaX)) {
			 int tmpInt = x0;
			 x0 = y0;
			 y0 = tmpInt;
			 
			 tmpInt = x1;
			 x1 = y1;
			 y1 = tmpInt;
			 
			 double tmpDouble = deltaX;
			 deltaX = deltaY;
			 deltaY = tmpDouble;
			 
			 axisSwaped = true;
		}

		// check if x1 is smaller than x0, then swap both points
		if(x1 < x0) {
			int tmpInt = x0;
			 x0 = x1;
			 x1 = tmpInt;
			 
			 tmpInt = y0;
			 y0 = y1;
			 y1 = tmpInt;
		}
		
		// calculate the y increase value
		double yIncrease = deltaY/deltaX;
		
		// draw the line
		for(double x = x0, y = y0; x <= x1; x++, y += yIncrease) {
			if(axisSwaped) {
				result.setPixel((int) Math.round(y), (int) Math.round(x), c);
			} else {
				result.setPixel((int) Math.round(x), (int) Math.round(y), c);
			}
		}

		return result;

	}

}
