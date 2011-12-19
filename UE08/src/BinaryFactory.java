import java.util.Scanner;

/**
 * Diese Factory erzeugt BinaryOperations.
 * 
 * @version 8
 * @author Manuel Geier (1126137)
 * 
 */
public class BinaryFactory implements Factory {

	/**
	 * erzeugt eine neue BinaryFactory.
	 */
	public BinaryFactory() {
	}

	/**
	 * liest mit dem Scanner das Schwellwert Zeichen ein, erzeugt damit eine neue 
	 * BinaryOperation und gibt diese zurück. Tritt beim Einlesen des Zeichens ein 
	 * Fehler auf, so wird eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new BinaryOperation
	 * @throws FactoryException
	 *             Thrown if there are too few parameters or parameters with a wrong type
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		char[] params = new char[1];

		for (int i = 0; i < params.length; i++) {
			if (!scanner.hasNext()) {
				throw new FactoryException("Insufficient parameter");
			}
			String s = scanner.next();
			if (s.length() > 1) {
				throw new FactoryException("Insufficient parameter");
			}
			params[i] = s.charAt(0);
		}

		
		return new BinaryOperation(params[0]);
	}
}
