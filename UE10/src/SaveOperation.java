
/**
 * This Class provides a method to save an AsciiImage. It implements the Operation interface.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class SaveOperation implements Operation {

	private MetricSet<AsciiImage> saved;
	
	/**
	 * erzeugt eine neue SaveOperation. saved ist eine Referenz auf ein MetricSet, in dem die 
	 * Bilder gespeichert werden sollen.
	 * 
	 * @param saved gespeicherte Bilder
	 * @param m neue Metrik
	 */
	public SaveOperation(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}
	
	/**
	 * speichert das spezifizierte Bild, d.h., stellt sicher, dass das spezifizierte Bild der 
	 * dem Konstruktor übergebenen Set hinzugefügt wurde (Ist in diesem bereits ein Bild img2, 
	 * so dass img2.equals(img), oder ist img == null wird kein neues Element hinzugefügt). 
	 * Die Rückgabe der Methode ist eine Kopie des spezifizierten Bildes img.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		saved.add(img);
		
		return new AsciiImage(img);
	}
	
	/**
	 * liefert die Collection mit gespeicherten Bildern.
	 * @return
	 */
	public MetricSet<AsciiImage> getSaved() {
		return saved;
	}
	

}
