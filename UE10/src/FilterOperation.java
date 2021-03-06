

/**
 * Diese abstrakte Klasse beinhaltet die Funktionalit�t, um das Bild zu durchlaufen und f�r 
 * jeden Pixel den ben�tigten Block an Nachbarpixeln zu bestimmen. Sie bietet mit der Methode 
 * filter eine Schablone (Template) f�r die konkreten Filter Operationen.
 * 
 * @version 9
 * @author Manuel Geier (1126137)
 * 
 */
public abstract class FilterOperation implements Operation {
	
	/**
	 * Konstruktor der FilterOperation.
	 */
	public FilterOperation() {
	}

	/**
	 * f�hrt den Blockfilter aus. Diese Methode muss von abgeleiteten Klassen nicht �berschrieben 
	 * werden. Die Methode durchl�uft das Bild, bestimmt f�r jeden Pixel den Block an Nachbarpixeln 
	 * und ruft damit dann die Methode filter auf. Das Ergebnis dieser Methode wird dann als neuer 
	 * Pixel an der aktuellen Stelle gesetzt.
	
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
				result.setPixel(x, y, img.getCharset().charAt(filter(getNeighborPixels(img,x,y))));
			}
		}
		
		return result;
	}
	
	/**
	 * muss von den abgeleiteten Klassen implementiert werden. Sie f�hrt die eigentliche 
	 * Logik des Filters durch. Das �bergebene Array umfasst die Helligkeitswerte der Pixel 
	 * im Block Zeile f�r Zeile. Diese Methode gibt den berechneten Helligkeitswert f�r den 
	 * neuen Pixel zur�ck.
	 * 
	 * @param values block
	 * @return filter value
	 */
	public abstract int filter(int[] values);
	
	/**
	 * Returns the neighbor pixels out of the image for the position.
	 * 
	 * @param img
	 * 			original image
	 * @param x
	 * 			x-position
	 * @param y
	 * 			y-position
	 * @return the neighbor pixels out of the image for the position.
	 */
	private int[] getNeighborPixels(AsciiImage img, int x, int y) {
		
		// get all 9 pixel
		int[] pixelList = new int[9];
		int i = 0;
		for(int imgY = -1; imgY <= 1; imgY++) {
			for(int imgX = -1; imgX <= 1; imgX++) {
				pixelList[i++] = img.getCharset().indexOf(getPixel(img,x+imgX,y+imgY));
			}
		}
		
		return pixelList;
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
}
