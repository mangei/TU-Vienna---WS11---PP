import java.util.Scanner;

/**
 * This Class provides a method to create a new LoadOperation. It implements the Factory
 * interface.
 * 
 * @version 8
 * @author Manuel Geier (1126137)
 * 
 */
public class LoadFactory implements Factory {

	/**
	 * Default constructor creates a new LoadFactory.
	 */
	public LoadFactory() {
	}

	/**
	 * liest den eof-String ein und übergibt in einem String alle Zeilen bis zum 
	 * abschließenden eof-String durch Zeilenumbrüche getrennt an den Konstruktor 
	 * der LoadOperation. Tritt beim Einlesen ein Fehler auf (eof fehlt), so wird 
	 * eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new LoadOperation
	 * @throws FactoryException
	 *             Thrown if there are too few parameters or parameters with a wrong type
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		String loadImage = "";
		
		if(scanner.hasNext()) {
			
			String endStr = scanner.next();
			
			boolean eof = false;
			while(!eof) {
				if(scanner.hasNext()) {
					
					String line = scanner.next();
					
					// Check if it is the end string
					if(line.equals(endStr)) {
						eof = true;
						
					} else {
						// add the line
						loadImage += line + '\n';
							
					}
				} else {
					eof = true;
				}
			}
		} else {
			throw new FactoryException("too few parameters");
		}
		
		return new LoadOperation(loadImage);
	}
}
