import java.util.Scanner;

/**
 * Uebungsrunde 4>
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
		
		AsciiImage image = new AsciiImage();
		
		// loop operations, as long as no error occoured
		while(!errorOperationFailed && !errorInputMismatch && scanner.hasNext()) {
			String operation = scanner.next();
			
			if("read".equals(operation)) {
			// read operation
				
				// read row count attribute
				if(scanner.hasNextInt()) {
					int imageRowLength = scanner.nextInt();
					
					// read the given number of rows as long as no error occoured
					errorInputMismatch = false;
					for(int i = 0; i < imageRowLength && !errorInputMismatch; i++) {
						
						// read the next line
						if(scanner.hasNext()) {
						
							String imageLine = scanner.next();
						
							// add next image line and check and set an error if is was not valid
							if(!image.addLine(imageLine)) {
								errorInputMismatch = true;
							}
							
						} else {
						// if there are no more lines, the image is corrupt
							errorInputMismatch = true;
						}
					}
					
					if(!errorInputMismatch) {
					// if no error occoured, set the read flag
						readOperationPerformed = true;
						
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
									if(x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
										
										// perform fill operation
										image.fill(x, y, c);
										
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
				} else if ("uniqueChars".equals(operation)) {
				// uniqueChars operation
					
					System.out.println(image.getUniqueChars());
					
				} else if ("flip-v".equals(operation)) {
				// flip-v operation
				
					image.flipV();
				
				} else if ("transpose".equals(operation)) {
				// transpose operation
				
					image.transpose();
				
				} else if ("symmetric-h".equals(operation)) {
				// symmetric-h operation
				
					System.out.println(image.isSymmetricH());
				
				} else if ("print".equals(operation)) {
				// print operation
				
					System.out.println(image.toString());
				
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
		if(!errorOperationFailed && !errorInputMismatch) {
			
			System.out.println(image.toString());
			System.out.println(image.getWidth() + " " + image.getHeight());
		}
	}
	
}