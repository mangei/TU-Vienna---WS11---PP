import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Uebungsrunde 6
 *
 * @author Manuel Geier (1126137)
 */
public class AsciiImage {
	
	private char[][] image;							// array for the image
	private int imageWidth;							// image height attribute
	private int imageHeight;						// image width attribute
	private static Character EMPTY_PIXEL = '.';		// empty pixel character
	
	/**
	 * Create a new AsciiImage with specified width and height
	 * 
	 * @param width width of the image
	 * @param height height of the image
	 */
	public AsciiImage(int width, int height) {
		image = new char[height][width];
		imageWidth = width;
		imageHeight = height;
		clear();
	}
	
	/**
	 * Creates a copy of the specified AsciiImage
	 * 
	 * @param img AsciiImage base
	 */
	public AsciiImage(AsciiImage img) {
		imageWidth = img.imageWidth;
		imageHeight = img.imageHeight;
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
	 * Returns image representation as String
	 * 
	 * @return string reprepresentation of the image
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int y = 0; y < imageHeight; y++) {
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
	public char getPixel(int x, int y) {
		return image[y][x];
	}
	
	/**
	 * Returns character at AsciiPoint position of the image
	 * 
	 * @param p AsciiPoint position
	 * @return character at position
	 */
	public char getPixel(AsciiPoint p) {
		return getPixel(p.getX(), p.getY());
	}

	/**
	 * Sets character at x, y position of the image
	 * 
	 * @param x x position
	 * @param y y position
	 * @param c new character
	 */
	public void setPixel(int x, int y, char c) {
		image[y][x] = c;
	}
	
	/**
	 * Sets character at AsciiPoint position of the image
	 * 
	 * @param p AsciiPoint position
	 * @param c new character
	 */
	public void setPixel(AsciiPoint p, char c) {
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
	 * Fill method to replace the current position and all connected 
	 * neighbors which have the same character with a new characer c.
	 * 
	 * @param x image location x
	 * @param y image location y
	 * @param c new character to set at the location
	 */
	public void fill(int x, int y, char c) {
	
		Character searchChar = getPixel(x, y);
		setPixel(x, y, c);
		
		
		// search at the four neighbors for the searchChar
		// also check, if the new coordination is valid
		
		// search left
		if(x-1 >= 0 && searchChar.equals(getPixel(x-1, y))) {
			fill(x-1, y, c);
		}
		
		// search right
		if(x+1 < imageWidth && searchChar.equals(getPixel(x+1, y))) {
			fill(x+1, y, c);
		}
		
		// search above
		if(y-1 >= 0 && searchChar.equals(getPixel(x, y-1))) {
			fill(x, y-1, c);
		}
		
		// search below
		if(y+1 < imageHeight && searchChar.equals(getPixel(x, y+1))) {
			fill(x, y+1, c);
		}
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
				String c = "" + getPixel(x,y);
			
				// if it is not in the chars string, add it
				if(!chars.contains(c)) {
					chars += c;
				}
			}
		}
		
		return chars.length();
	}
	
	/**
	 * Flips the image vertically.
	 */
	public void flipV() {
		char tmp;
		
		// iterate through all rows
		for(int y = 0; y < imageHeight; y++) {
		
			// iterate through all columns till the center of the image
			for(int x = 0; x < imageWidth / 2; x++) {
			
				// swap pixels
				tmp = getPixel(x,y);
				setPixel(x,y,getPixel(imageWidth - x - 1,y));
				setPixel(imageWidth - x - 1,y,tmp);
			}
		}
	}
	
	/**
	 * Transposes the image.
	 */
	public void transpose() {
	
		// swap width and height for the new image
		int newImageHeight = imageWidth;
		int newImageWidth = imageHeight;
		
		// create the new image
		char[][] newImage = new char[newImageHeight][newImageWidth];
	
		// Transpose
		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
				newImage[x][y] = image[y][x];
			}
		}
		
		// set new image
		image = newImage;
		
		// set new width and height
		imageHeight = newImageHeight;
		imageWidth = newImageWidth;
	}
	
	/**
	 * Clear the image.
	 */
	public void clear() {
		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
				setPixel(x, y, EMPTY_PIXEL);
			}
		}
	}
	
	/**
	 * Draw a line of character from startpoint to endpoint.
	 * 
	 * @param x0 x position of the startpoint
	 * @param y0 y position of the startpoint
	 * @param x1 x position of the endpoint
	 * @param y1 y position of the endpoint
	 * @param c character to draw
	 */
	public void drawLine(int x0, int y0, int x1, int y1, char c) {
	
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
				setPixel((int) Math.round(y), (int) Math.round(x), c);
			} else {
				setPixel((int) Math.round(x), (int) Math.round(y), c);
			}
		}
	}
	
	/**
	 * Replaces a character with another one.
	 * 
	 * @param oldChar old character
	 * @param newChar new character which replaces the old one
	 */
	public void replace(char oldChar, char newChar) {
		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
				if(getPixel(x,y) == oldChar) {
					setPixel(x, y, newChar);
				}
			}
		}
	}

	/**
	 * Returns the AsciiPoint of the center by the specified character.
	 * 
	 * @param c character to search for
	 * @return AsciiPoint of the center by the specified characters
	 */
	public AsciiPoint getCentroid(char c) {
		
		// get list of all pixels with specified character
		List<AsciiPoint> pList = getPointList(c);
		
		// if there are no points, retrun null
		if(pList.size() == 0) {
			return null;
		}
		
		int x =0, y = 0;
		int sumX = 0, sumY = 0;
		AsciiPoint p;
		
		// iterate through the pointlist
		for(int i=0; i<pList.size(); i++) {
			
			p = pList.get(i);
			x = p.getX();
			y = p.getY();
			
			// sum all x and y
			sumX += x;
			sumY += y;
		}
	
		// calculate the middle value of the x and y and divide it throuth the element count
		return new AsciiPoint((int)Math.round((double)sumX/pList.size()), (int)Math.round((double)sumY/pList.size()));
	}
	
	/**
	 * Grows empty pixels around a specified character.
	 * 
	 * @param c character to search for
	 */
	public void growRegion(char c) {
		// get the pixel list for the specified character
		List<AsciiPoint> pList = getPointList(c);
		
		int x,y;
		for(AsciiPoint p: pList) {
			
			x = p.getX();
			y = p.getY();
		
			// search left
			if(x-1 >= 0 && EMPTY_PIXEL.equals(getPixel(x-1, y))) {
				setPixel(x-1, y, c);
			}
			
			// search right
			if(x+1 < imageWidth && EMPTY_PIXEL.equals(getPixel(x+1, y))) {
				setPixel(x+1, y, c);
			}
			
			// search above
			if(y-1 >= 0 && EMPTY_PIXEL.equals(getPixel(x, y-1))) {
				setPixel(x, y-1, c);
			}
			
			// search below
			if(y+1 < imageHeight && EMPTY_PIXEL.equals(getPixel(x, y+1))) {
				setPixel(x, y+1, c);
			}
		}
	}
	
	/**
	 * Streightens a region of a specified character.
	 * 
	 * @param straighten character
	 */
	public void straightenRegion(char c) {
	
		int neighbourCount = 0;
		Character cObj = c;
	
		for(int y=0; y < imageHeight; y++) {
			for(int x=0; x < imageWidth; x++) {
			
				// check if the neighbors are similar
				if(getPixel(x,y) == c) {
				
					neighbourCount = 0;
				
					// search left
					if(x-1 >= 0 && cObj.equals(getPixel(x-1, y))) {
						neighbourCount++;
					}
					
					// search right
					if(x+1 < imageWidth && cObj.equals(getPixel(x+1, y))) {
						neighbourCount++;
					}
					
					// search above
					if(y-1 >= 0 && cObj.equals(getPixel(x, y-1))) {
						neighbourCount++;
					}
					
					// search below
					if(y+1 < imageHeight && cObj.equals(getPixel(x, y+1))) {
						neighbourCount++;
					}
					
					// if less then two neighbours are similar, empty the pixel
					if(neighbourCount < 2) {
						setPixel(x, y, EMPTY_PIXEL);
					}
					
				}
			}
		}
	}
}