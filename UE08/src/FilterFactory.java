import java.util.Scanner;

/**
 * Diese Factory erzeugt MedianOperations.
 * 
 * @version 8
 * @author Manuel Geier (1126137)
 * 
 */
public class FilterFactory implements Factory {

	/**
	 * erzeugt eine neue FilterFactory.
	 */
	public FilterFactory() {
	}

	/**
	 * liest den n�chsten String ein und gibt, falls dieser �median� ist, eine 
	 * neue MedianOperation zur�ck. Tritt beim Einlesen des Strings ein Fehler 
	 * auf, oder ist der String nicht �median�, so wird eine FactoryException 
	 * geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new MedianOperation
	 * @throws FactoryException
	 *             Thrown if there are too few parameters or parameters with a wrong type
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		// read newChar attribute
		if(scanner.hasNext()) {
			String filter = scanner.next();
			
			if("median".equals(filter)) {

				// everything ok
				
			} else {
				throw new FactoryException("filter parameter is not 'median'");
			}
		} else {
			throw new FactoryException("more parameters required.");
		}
		
		return new MedianOperation();

	}

}
