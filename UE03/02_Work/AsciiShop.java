import java.util.Scanner;

/**
 * Uebungsrunde 3<br />
 * The program reads a specified number of rows of an ASCII image. It is 
 * checked whether all lines are of equal length. Subsequently, a fill command 
 * is entered, the parameters of the position to be filled area describes. 
 * Finally, the modified image and its width and height is printed.
 * 
 * @author Manuel Geier (1126137)
 */
public class AsciiShop {
	
	/**
	 * Reads data and operations and prints resolutions. Only this method
	 * reads directly from System.in and prints on System.out.
	 *
	 * @param args program arguments (not needed)
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);		// Scanner to read the input
		boolean readOperationPerformed = false;			// read operation performed flag
		boolean errorOperationFailed = false;			// operation failed error flag
		boolean errorInputMismatch = false;				// input mismatch flag
		String[] imageArr = null;						// array for the image
		int imageRowLength = -1;						// image height attribute
		int imageColLength = -1;						// image width attribute
		
		// loop operations, as long as no error occoured
		while(!errorOperationFailed && !errorInputMismatch && scanner.hasNext()) {
			String operation = scanner.next();
			
			if("read".equals(operation)) {
			// read operation
				
				// read row count attribute
				if(scanner.hasNextInt()) {
					imageRowLength = scanner.nextInt();
					
					// create an array for the image to read
					imageArr = new String[imageRowLength];
					
					// read the given number of rows as long as no error occoured
					errorInputMismatch = false;
					for(int i = 0; i < imageRowLength && !errorInputMismatch; i++) {
						
						// read the next line
						if(scanner.hasNext()) {
							imageArr[i] = scanner.next();
							
							if(imageColLength == -1) {
							// initialize the column length if not initialized
								imageColLength = imageArr[i].length();
								
							} else {
							// otherwise check if the column lengthes matches
								if(imageColLength != imageArr[i].length()) {
									errorInputMismatch = true;
								}
							}
						} else {
						// if there are no more lines, the image is corrupt
							errorInputMismatch = true;
						}
					}
					
					if(!errorInputMismatch) {
					// if no error occoured, set the read flag
						readOperationPerformed = true;
						
					} else {
					// else reset image attributes
						imageArr = null;
						imageRowLength = -1;
						imageColLength = -1;
					}
				} else {
					errorInputMismatch = true;
				}
				
			} else if(readOperationPerformed) {
			// all operations where the read operation is mandantory
				
				if("fill".equals(operation)) {
				// fill operation
					
					// read x attribute
					if(scanner.hasNextInt()) {
						int x = scanner.nextInt();
						
						// read y attribute
						if(scanner.hasNextInt()) {
							int y = scanner.nextInt();
							
							// read c attribute
							if(scanner.hasNext()) {
								String cStr = scanner.next();
								
								if(cStr.length() == 1) {
									char c = cStr.charAt(0);
									
									// check attributes
									if(x >= 0 && x < imageColLength && y >= 0 && y < imageRowLength) {
										
										// perform fill operation
										fill(imageArr, x, y, c);
										
									} else {
										errorOperationFailed = true;
									}
								} else {
									errorInputMismatch = true;
								}
							} else {
								errorInputMismatch = true;
							}
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
				} else {
					errorInputMismatch = true;
				}
			} else {
				errorInputMismatch = true;
			}
		}
		
		// Check errors and print message
		if(errorOperationFailed) {
			System.out.println("OPERATION FAILED");
		}
		if(errorInputMismatch) {
			System.out.println("INPUT MISMATCH");
		}
		
		// If no error occoured and an image was read, print the 
		// manipulated image and row and column length
		if(!errorOperationFailed && !errorInputMismatch && imageArr != null) {
			
			for(int i = 0; i < imageRowLength; i++) {
				System.out.println(imageArr[i]);
			}
			System.out.println(imageColLength + " " + imageRowLength);
		}
	}
	
	/**
	 * Fill method to replace the current position and all connected 
	 * neighbors which have the same character with a new characer c.
	 * 
	 * @param image the ascii image
	 * @param x image location x
	 * @param y image location y
	 * @param c new character to set at the location
	 */
	public static void fill(String[] image, int x, int y, char c) {
		
		
		int imgRowLen = image.length;			// height of the image
		int imgColLen = image[0].length();		// width of the image
		
		char[] imageRow = image[y].toCharArray();	// current image row
		Character searchChar = imageRow[x];			// character we want to replace
		
		// replace c and set the new row
		imageRow[x] = c;
		image[y] = new String(imageRow);
		
		
		// search at the four neighbors for the searchChar
		// also check, if the new coordination is valid
		
		// search left
		if(x-1 >= 0 && searchChar.equals(image[y].charAt(x-1))) {
			fill(image, x-1, y, c);
		}
		
		// search right
		if(x+1 < imgColLen && searchChar.equals(image[y].charAt(x+1))) {
			fill(image, x+1, y, c);
		}
		
		// search above
		if(y-1 >= 0 && searchChar.equals(image[y-1].charAt(x))) {
			fill(image, x, y-1, c);
		}
		
		// search below
		if(y+1 < imgRowLen && searchChar.equals(image[y+1].charAt(x))) {
			fill(image, x, y+1, c);
		}
	}
}