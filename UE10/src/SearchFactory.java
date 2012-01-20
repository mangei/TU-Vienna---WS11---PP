import java.util.Scanner;

/**
 * Diese Factory erzeugt SearchOperation.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class SearchFactory implements Factory {

	private MetricSet<AsciiImage> saved;
	private Metric<AsciiImage> m;
	
	/**
	 * erzeugt eine neue SearchFactory. saved ist eine Referenz auf ein MetricSet, in dem 
	 * sich die gespeicherten Bilder befinden.
	 * 
	 * @param saved gespeicherten Bilder
	 * @param m Metric-Vergleichsoperation
	 */
	public SearchFactory(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
		this.saved = saved;
		this.m = m;
	}

	/**
	 * Erzeugt eine neue SearchOperation. Dazu wird zunächst mit dem angegebenen Scanner 
	 * ein String eingelesen, der angibt, welche Metrik benutzt werden soll ("pixelcount" 
	 * oder "uniquechars"). Kann dieser String nicht eingelesen werden, oder ist der 
	 * eingelesene String unbekannt, wird eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new SearchOperation
	 */
	public Operation create(Scanner scanner) throws FactoryException {

		return new SearchOperation(saved, m);
	}

}
