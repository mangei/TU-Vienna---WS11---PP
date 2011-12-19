import java.util.Scanner;

/**
 * Diese Factory erzeugt ClearOperations.
 * 
 * @version 8
 * @author Manuel Geier (1126137)
 * 
 */
public class ClearFactory implements Factory {

	/**
	 * erzeugt eine neue ClearFactory.
	 */
	public ClearFactory() {
	}

	/**
	 * erzeugt eine neue ClearOperation und gibt diese zurück.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new ClearOperation
	 */
	public Operation create(Scanner scanner) {

		return new ClearOperation();

	}

}
