import java.util.Scanner;

/**
 * AsciiShop<br />
 * Runde#1
 *
 * @author: Manuel Geier (1126137)
 */
public class AsciiShop {

	/**
	 * Main method
	 * @param args arguments
	 */
	public static void main(String[] args) {
		
		// Initialize variables
		Scanner scanner = new Scanner(System.in);
		int colLength = -1;
		int rowLength = 0;
		
		// check if a next line exists
		while(scanner.hasNextLine()) {
		
			// read the next line
			String line = scanner.nextLine();
			
			if(colLength == -1) {
			// if the column length was not set
			
				// Set the column length
				colLength = line.length();
				
				// increase the row length
				rowLength++;
				
			} else {
			// if the column length was set
			
				// check if the current column length matches with the inital column length
				if(line.length() == colLength) {
				// length is ok
					
					// increase the row length
					rowLength++;
					
				} else {
				// length is not ok
				
					// print message end exit the program
					System.out.println("INPUT MISMATCH");
					return;
					
				}
			}
		}
		
		// Output
		System.out.println(colLength + " " + rowLength);
		
	}

}