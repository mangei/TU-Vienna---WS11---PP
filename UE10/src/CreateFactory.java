import java.util.Scanner;

/**
 * Diese Factory erzeugt CreateOperation.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class CreateFactory implements Factory {

	/**
	 * erzeugt eine neue CreateFactory.
	 */
	public CreateFactory() {
	}

	/**
	 * liest mit Hilfe des Scanners Breite und Höhe und einen String ein und gibt 
	 * eine damit initialisierte neue CreateOperation zurück. Tritt beim Einlesen 
	 * ein Fehler (zu wenig Parameter, falsche Parameter), so wird eine FactoryException 
	 * geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new CreateOperation
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		// width attribute
		if(scanner.hasNextInt()) {
			int imageWidth = scanner.nextInt();
			
			// check width
			if(imageWidth > 0) {
			
				// height attribute
				if(scanner.hasNextInt()) {
					int imageHeight = scanner.nextInt();
				
					// check height
					if(imageHeight > 0) {
					
						// height attribute
						if(scanner.hasNext()) {
							String imgCharset = scanner.next();
							
							return new CreateOperation(imageWidth, imageHeight, imgCharset);
								
						}  else {
							throw new FactoryException();
						}
					} else {
						throw new FactoryException();
					}
				}  else {
					throw new FactoryException();
				}
			} else {
				throw new FactoryException();
			}
		} else {
			throw new FactoryException();
		}
		
	}

}
