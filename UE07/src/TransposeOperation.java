
/**
 * Transpose Operation
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class TransposeOperation implements Operation {

	
	/**
	 * Creates a new TransposeOperation.
	 */
	public TransposeOperation() {
	}
	
	/**
	 * Executes this TransposeOperation and returns as new AsciiImage.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		// create the new image
		AsciiImage result = new AsciiImage(img.getHeight(), img.getWidth(), img.getCharset());

		// Transpose
		for(int y=0, h = img.getHeight(); y < h; y++) {
			for(int x=0, w = img.getWidth(); x < w; x++) {
				result.setPixel(x,y,img.getPixel(y,x));
			}
		}
		
		return result;

	}

}
