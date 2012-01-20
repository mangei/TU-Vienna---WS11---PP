import java.util.Scanner;

/**
 * Diese Factory erzeugt SaveOperation.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class SaveFactory implements Factory {

	private MetricSet<AsciiImage> saved;
	
	/**
	 * erzeugt eine neue SaveFactory. saved ist eine Referenz auf ein MetricSet, dem durch 
	 * eine SaveOperation Bilder hinzugefügt werden sollen.
	 * 
	 * @param saved gespeicherte Bilder
	 */
	public SaveFactory(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}

	/**
	 * Erzeugt eine neue SaveOperation.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new SaveOperation
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		return new SaveOperation(saved);
	}

}
