import java.util.List;


/**
 * Grow Region Operation
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class GrowRegionOperation implements Operation {

	private char c;
	
	/**
	 * Creates a new GrowRegionOperation.
	 */
	public GrowRegionOperation(char c) {
		this.c = c;
	}
	
	/**
	 * Executes this GrowRegionOperation and returns as new AsciiImage.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		AsciiImage result = new AsciiImage(img);
		
		String charset = img.getCharset();
		Character brightestChar = charset.charAt(charset.length()-1);

		// grow Region
		// get the pixel list for the specified character
		List<AsciiPoint> pList = img.getPointList(c);
		
		int imageWidth = img.getWidth();
		int imageHeight = img.getHeight();
		
		int x,y;
		for(AsciiPoint p: pList) {
			
			x = p.getX();
			y = p.getY();
		
			// search left
			if(x-1 >= 0 && brightestChar.equals(img.getPixel(x-1, y))) {
				img.setPixel(x-1, y, c);
			}
			
			// search right
			if(x+1 < imageWidth && brightestChar.equals(img.getPixel(x+1, y))) {
				img.setPixel(x+1, y, c);
			}
			
			// search above
			if(y-1 >= 0 && brightestChar.equals(img.getPixel(x, y-1))) {
				img.setPixel(x, y-1, c);
			}
			
			// search below
			if(y+1 < imageHeight && brightestChar.equals(img.getPixel(x, y+1))) {
				img.setPixel(x, y+1, c);
			}
		}

		return result;

	}

}
