
/**
 * Fill Operation
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class FillOperation implements Operation {

	private int x;
	private int y;
	private char c;
	
	/**
	 * Creates a new FillOperation.
	 */
	public FillOperation(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	/**
	 * Executes this FillOperation and returns as new AsciiImage.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		AsciiImage result = new AsciiImage(img);

		fill(result, x, y, c);

		return result;

	}
	
	/**
	 * Fill method to replace the current position and all connected 
	 * neighbors which have the same character with a new characer c.
	 * 
	 * @param x image location x
	 * @param y image location y
	 * @param c new character to set at the location
	 */
	public void fill(AsciiImage img, int x, int y, char c) {
	
		Character searchChar = img.getPixel(x, y);
		img.setPixel(x, y, c);
		
		int imageWidth = img.getWidth();
		int imageHeight = img.getHeight();
		
		// search at the four neighbors for the searchChar
		// also check, if the new coordination is valid
		
		// search left
		if(x-1 >= 0 && searchChar.equals(img.getPixel(x-1, y))) {
			fill(img, x-1, y, c);
		}
		
		// search right
		if(x+1 < imageWidth && searchChar.equals(img.getPixel(x+1, y))) {
			fill(img, x+1, y, c);
		}
		
		// search above
		if(y-1 >= 0 && searchChar.equals(img.getPixel(x, y-1))) {
			fill(img, x, y-1, c);
		}
		
		// search below
		if(y+1 < imageHeight && searchChar.equals(img.getPixel(x, y+1))) {
			fill(img, x, y+1, c);
		}
	}

}
