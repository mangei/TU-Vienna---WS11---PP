
/**
 * This Class provides a method to search for an AsciiImage. It implements the Operation interface.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class SearchOperation implements Operation {

	private MetricSet<AsciiImage> saved;
	private Metric<AsciiImage> m;
	
	/**
	 * initialisiert diese SearchOperation mit einer angegebenen Metrik. saved ist eine Referenz 
	 * auf ein MetricSet, in dem sich die gespeicherten Bilder befinden. m ist die Metrik.
	 * 
	 * @param saved gespeicherten Bilder
	 * @param m Metric-Vergleichsoperation
	 */
	public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
		this.saved = saved;
		this.m = m;
	}
	
	/**
	 * liefert ein Bild mit minimaler Distanz zum spezifizierten Bild und liefert dieses als Kopie 
	 * zurück. Gibt es mehrere gespeicherte Bilder mit minimaler Distanz, wird irgendeines dieser 
	 * Bilder geliefert. Ist saved leer, wird eine OperationException geworfen.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		MetricSet<AsciiImage> newMetricSet = saved.search(img, m);
		
		return (AsciiImage) newMetricSet.toArray()[0];

	}

}
