
/**
 * This Class provides a method to clear all pixels. It implements the Operation interface.
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class ClearOperation implements Operation {

	
	/**
	 * Creates a new ClearOperation.
	 */
	public ClearOperation() {
	}
	
	/**
	 * Executes this ClearOperation and returns as new AsciiImage where all pixels are set to the 
	 * brightest char of the charset of the image.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		String charset = img.getCharset();
		char brightestChar = charset.charAt(charset.length()-1);

		AsciiImage result = new AsciiImage(img);

		// clear all pixels
		for (int y = 0; y < result.getHeight(); y++) {
			for (int x = 0; x < result.getWidth(); x++) {
				result.setPixel(x, y, brightestChar);
			}
		}

		return result;

	}

}
