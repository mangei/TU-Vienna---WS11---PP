
/**
 * Eine einfache Metrik für Bilder, die die Anzahl unterschiedlicher Zeichen vergleicht.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 *
 */
public class UniqueCharsMetric implements Metric<AsciiImage> {

	/**
	 * liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem 
	 * Bild. Zur Berechnung der Anzahl unterschiedlicher Zeichen eines Bildes kann die Methode 
	 * getUniqueChars() aus Runde 4 herangezogen werden.
	 * 
	 * @param i1 AsciiImage 1
	 * @param i2 AsciiImage 2
	 * @return  liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild
	 */
	public int distance(AsciiImage i1, AsciiImage i2) {
		
		String diffChars = "";
		
		for(char i1c : i1.getCharset().toCharArray()) {
			if(i2.getCharset().indexOf(i1c) == -1) {
				diffChars += i1c;
			}
		}
		
		for(char i2c : i2.getCharset().toCharArray()) {
			if(i1.getCharset().indexOf(i2c) == -1) {
				diffChars += i2c;
			}
		}
		
		return diffChars.length();
	}
	
}
