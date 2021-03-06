import java.util.ArrayList;
import java.util.Arrays;

/**
 * Uebungsrunde 7
 *
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class AsciiImage {
	
	private char[][] image;							// array for the image
	private int imageWidth;							// image height attribute
	private int imageHeight;						// image width attribute
	private String charset;							// valid charset of the image
	
	/**
	 * Create a new AsciiImage with specified width and height
	 * 
	 * @param width width of the image
	 * @param height height of the image
	 * @param charset charset of the image
	 */
	public AsciiImage(int width, int height, String charset) {
		
		if(width < 0) {
			throw new IllegalArgumentException("width must be > 0: " + width);
		}
		if(height < 0) {
			throw new IllegalArgumentException("height must be > 0: " + height);
		}
		if(!isCharsetValid(charset)) {
			throw new IllegalArgumentException("charset is empty or contains values multiple times: " + charset);
		}
		
		image = new char[height][width];
		imageWidth = width;
		imageHeight = height;
		this.charset = charset;
		
		// clear image
		char brightestChar = charset.charAt(charset.length()-1);

		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {
				setPixel(x, y, brightestChar);
			}
		}
	}
	
	/**
	 * Creates a copy of the specified AsciiImage
	 * 
	 * @param img AsciiImage base
	 */
	public AsciiImage(AsciiImage img) {
		imageWidth = img.imageWidth;
		imageHeight = img.imageHeight;
		charset = img.charset;
		image = new char[imageHeight][imageWidth];
		
		// copy image array
		for(int y = 0; y < imageHeight; y++) {
			for(int x = 0; x < imageWidth; x++) {
				image[y][x] = img.image[y][x];
			}
		}
	}
	
	/**
	 * Returns width of the image
	 * 
	 * @return width of the image
	 */
	public int getWidth() {
		return imageWidth;
	}
	
	/**
	 * Returns height of the image
	 * 
	 * @return height of the image
	 */
	public int getHeight() {
		return imageHeight;
	}
	
	/**
	 * Returns the charset of the image
	 * 
	 * @return charset of the image
	 */
	public String getCharset() {
		return charset;
	}
	
	/**
	 * Returns image representation as String
	 * 
	 * @return string reprepresentation of the image
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int y = 0; y < imageHeight; y++) {
//			if(y != 0) {
//				builder.append("\n");
//			}
			
			for(int x = 0; x < imageWidth; x++) {
				builder.append(image[y][x]);
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	/**
	 * Returns character at x, y position of the image
	 * 
	 * @param x x position
	 * @param y y position
	 * @return character at position
	 */
	public char getPixel(int x, int y) throws IndexOutOfBoundsException {
		if(!isXValid(x)) {
			throw new IndexOutOfBoundsException("x: " + x);
		}
		if(!isYValid(y)) {
			throw new IndexOutOfBoundsException("y: " + y);
		}
		
		return image[y][x];
	}
	
	/**
	 * Returns character at AsciiPoint position of the image
	 * 
	 * @param p AsciiPoint position
	 * @return character at position
	 */
	public char getPixel(AsciiPoint p) throws IndexOutOfBoundsException {
		return getPixel(p.getX(), p.getY());
	}

	/**
	 * Sets character at x, y position of the image
	 * 
	 * @param x x position
	 * @param y y position
	 * @param c new character
	 */
	public void setPixel(int x, int y, char c) throws IndexOutOfBoundsException {
		if(!isXValid(x)) {
			throw new IndexOutOfBoundsException("x: " + x);
		}
		if(!isYValid(y)) {
			throw new IndexOutOfBoundsException("y: " + y);
		}
		
		image[y][x] = c;
	}
	
	/**
	 * Sets character at AsciiPoint position of the image
	 * 
	 * @param p AsciiPoint position
	 * @param c new character
	 */
	public void setPixel(AsciiPoint p, char c) throws IndexOutOfBoundsException {
		setPixel(p.getX(), p.getY(), c);
	}
	
	/**
	 * Returns a list of AsciiPoint where the character c is placed
	 * 
	 * @param c character to search for
	 * @return List of AsciiPoints
	 */
	public ArrayList<AsciiPoint> getPointList(char c) {
		ArrayList<AsciiPoint> pList = new ArrayList<AsciiPoint>();
		
		// iterate through all pixels and search for the caracter
		// if found, add it to the list
		for(int y = 0; y < imageHeight; y++) {
			for(int x = 0; x < imageWidth; x++) {
				if(image[y][x] == c) {
					pList.add(new AsciiPoint(x,y));
				}
			}
		}
		
		return pList;
	}
	
	/**
	 * Checks if x value is valid
	 * 
	 * @param x x value
	 * @return if the x value is valid
	 */
	private boolean isXValid(int x) {
		return (x >= 0 && x < imageWidth);
	}
	
	/**
	 * Checks if y value is valid
	 * 
	 * @param y y value
	 * @return if the y value is valid
	 */
	private boolean isYValid(int y) {
		return (y >= 0 && y < imageHeight);
	}
	
	/**
	 * Checks if the charset is valid
	 * 
	 * @param charset charset
	 * @return if the charset is valid
	 */
	private boolean isCharsetValid(String charset) {
		// check if the charset is not empty
		if(charset.isEmpty()) {
			return false;
		}
		
		// checks if the charset contains a value twice
		for(char ch : charset.toCharArray()) {
			if(charset.indexOf(ch) != charset.lastIndexOf(ch)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Vergleicht das angegebene Bild mit diesem Bild und liefert true wenn Höhe und Breite übereinstimmen
	 * und der Pixelwert an jeder Position des angegebenen Bildes mit dem in diesem Bild übereinstimmt. 
	 * Ansonsten wird false geliefert.
	 * 
	 * @param o other object to compare with this
	 */
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(o.getClass() != this.getClass()) {
			return false;
		}
		
		AsciiImage other = (AsciiImage) o;
		
		if(!other.charset.equals(this.charset)) {
			return false;
		} else if(other.imageHeight != this.imageHeight) {
			return false;
		} else if(other.imageWidth != this.imageWidth) {
			return false;
		}

		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
				if(other.getPixel(x, y) != this.getPixel(x, y)) {
					return false;
				}
			}
		}
			
		return true;
	}
	
	
	/**
	 * liefert den Hashcode diese Bildes, der der Summe der ASCII Codes aller Zeichen im Bild entspricht.
	 * 
	 * @return hashcode
	 */
	public int hashCode() {
		int sum = 0;
		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
				sum += getPixel(x, y);
			}
		}
		return sum;
	}
	
	/**
	 * Returns the number of different characters within the image.
	 * 
	 * @return number of different characters
	 */
	public int getUniqueChars() {
		
		String chars = "";
		
		for(int y=0; y < imageHeight; y++) {
		
			for(int x=0; x < imageWidth; x++) {
				
				// get character at position
				String c = "" + getPixel(x, y);
			
				// if it is not in the chars string, add it
				if(!chars.contains(c)) {
					chars += c;
				}
			}
		}
		
		return chars.length();
	}
	
	
}