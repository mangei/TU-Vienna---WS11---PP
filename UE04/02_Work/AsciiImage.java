import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Uebungsrunde 4
 * 
 * @author Manuel Geier (1126137)
 */
public class AsciiImage {
	
	private String image;						// string for the image
	private int imageWidth;						// image height attribute
	private int imageHeight;					// image width attribute
	
	/**
	 * Create a new AsciiImage with specified width and height
	 * 
	 * @param width width of the image
	 * @param height height of the image
	 */
	public AsciiImage() {
		image = "";
		imageWidth = 0;
		imageHeight = 0;
	}
	
	/**
	 * Adds a new image line to the image
	 * 
	 * @param newLine new image line to add
	 * @return true, if it was successful, otherwise false
	 */
	public boolean addLine(String newLine) {
		if(imageWidth == 0) {
		// initialize the column length if not initialized
		
			// Check the length of the first line; must be at least one char
			if(newLine.length() > 0) {
				imageWidth = newLine.length();
			} else {
				return false;
			}
		} else {
		// otherwise check if the column lengthes matches
			if(imageWidth != newLine.length()) {
			
				// image width does not match
				return false;
			}
		}
		
		// Add the line and increase the image height
		image += newLine;
		imageHeight++;
		
		// addLine was sucessfull
		return true;
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
		
		for(int i = 0; i < imageHeight; i++) {
			if(i != 0) {
				builder.append("\n");
			}
			builder.append(image.substring(i*imageWidth, i*imageWidth + imageWidth));
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
	private char getPixel(int x, int y) {
		return image.charAt(y*imageWidth + x);
	}

	/**
	 * Sets character at x, y position of the image
	 * 
	 * @param x x position
	 * @param y y position
	 * @param c new character
	 */
	private void setPixel(int x, int y, char c) {
	
		String before = image.substring(0, y*imageWidth + x);
		
		String after = "";
		if(y*imageWidth + x+1 < imageHeight * imageWidth) {
			after = image.substring(y*imageWidth + x+1, imageHeight * imageWidth);
		}
		
		image = before + c + after;
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
		
		for(int i=0; i < image.length(); i++) {
		
			// get character at position
			String c = "" + image.charAt(i);
		
			// if it is not in the chars string, add it
			if(!chars.contains(c)) {
				chars += c;
			}
		}
		
		return chars.length();
	}
	
	/**
	 * Flips the image vertically.
	 */
	public void flipV() {
		// create a new image
		StringBuilder newImageBuilder = new StringBuilder();
	
		// iterate through all rows and build it up backwards
		for(int i = imageHeight-1; i >=0; i--) {
			newImageBuilder.append(image.substring(i*imageWidth, i*imageWidth + imageWidth));
		}
		
		// set the new image
		image = newImageBuilder.toString();
	}
	
	/**
	 * Transposes the image.
	 */
	public void transpose() {
		StringBuilder newImage = new StringBuilder();
	
		// Transpose
		for(int x=0; x < imageWidth; x++) {
			for(int y=0; y < imageHeight; y++) {
				newImage.append(image.charAt(y*imageWidth + x));
			}
		}
		
		// set new image
		image = newImage.toString();
		
		// swap width and height
		int tmp = imageWidth;
		imageWidth = imageHeight;
		imageHeight = tmp;
	}
	
	/**
	 * Bonus<br />
	 * Returns true if the image is horizontal symmetric, otherwise false
	 * 
	 * @return true if horizontal symmetric, otherwise false
	 */
	public boolean isSymmetricH() {
	
		for(int y = 0; y < imageHeight; y++) {
			for(int x = 0; x < imageWidth/2; x++) {
			
				// Checks if the pixels in the row (x from left and x from right) are the same, otherwise return false
				if(!((Character)getPixel(x,y)).equals(getPixel(imageWidth-1-x,y))) {
					return false;
				}
			}
		}
	
		return true;
	}
}