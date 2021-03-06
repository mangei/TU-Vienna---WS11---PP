import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * This Class provides a method to replace all occurrences of a specified char by another specified
 * char. It implements the Operation interface.
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class MedianOperation implements Operation {

	/**
	 * Creates a new MedianOperation.
	 */
	public MedianOperation() {
	}

	/**
	 * F�hrt auf einer Kopie des Bildes den Medianfilter aus. Dabei werden immer 3 mal 3 Gr��e 
	 * Bl�cke des Bildes betrachtet, die Pixel nach ihrem `Helligkeitswert' sortiert und dann der 
	 * Median (also das in der sortierten Liste in der Mitte stehende Zeichen) als neues Pixel im 
	 * Mittelpunkt des Blocks gesetzt.
	
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) {

		AsciiImage result = new AsciiImage(img);

		// calculate and set the median pixel for the whole image
		for(int y = 0, h = img.getHeight(); y < h; y++) {
			for(int x = 0, w = img.getWidth(); x < w; x++) {
				result.setPixel(x, y, getMedianPixel(img, x, y));
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the calculated median pixel color out of the image for the position.
	 * 
	 * @param img
	 * 			original image
	 * @param x
	 * 			x-position
	 * @param y
	 * 			y-position
	 * @return Returns the calculated median pixel color out of the image for the position.
	 */
	private char getMedianPixel(AsciiImage img, int x, int y) {
		
		// get all 9 pixel
		List<Character> pixelList = new ArrayList<Character>(9);
		for(int imgY = -1; imgY <= 1; imgY++) {
			for(int imgX = -1; imgX <= 1; imgX++) {
				pixelList.add(getPixel(img,x+imgX,y+imgY));
			}
		}
		
		// order list
		Collections.sort(pixelList, new MedianPixelComparator(img.getCharset()));
		
		// return center pixel
		return pixelList.get(4);
	}
	
	/**
	 * Returns the pixel at the x- and y-position of the image. If the positions are
	 * outside of the image, the brightest color of the image charset will be returned.
	 * 
	 * @param img 
	 * 			original image
	 * @param x
	 * 			x position
	 * @param y
	 * 			y position
	 * @return pixel at the position or the brightest color
	 */
	private char getPixel(AsciiImage img, int x, int y) {
		if(x >= 0 && x < img.getWidth() && y >= 0 && y < img.getHeight()) {
			return img.getPixel(x, y);
		} else {
			return img.getCharset().charAt(img.getCharset().length()-1);
		}
	}
	
	/**
	 * Comparator to sort the median pixel list.
	 * 
	 * @version 7
	 * @author Manuel Geier (1126137)
	 *
	 */
	private class MedianPixelComparator implements Comparator<Character> {
		
		private String charset;
		
		/**
		 * Creates an MedianPixelComparator
		 * 
		 * @param charset
		 * 			original charset
		 */
		public MedianPixelComparator(String charset) {
			this.charset = charset;
		}
		
		@Override
		public int compare(Character ch1, Character ch2) {
			int idx1 = charset.indexOf(ch1);
			int idx2 = charset.indexOf(ch2);
			
			if(idx1 < idx2) {
				return -1;
			} else if(idx1 > idx2) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
}
