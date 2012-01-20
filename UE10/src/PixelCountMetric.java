
/**
 * Eine einfache Metrik für Bilder, die Bildgrößen vergleicht.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 *
 */
public class PixelCountMetric implements Metric<AsciiImage> {

	/**
	 * liefert den Absolutbetrag der Differenz der Bildgrößen von i1 und i2. Mit Bildgröße ist das 
	 * Produkt von Höhe mal Breite des Bildes gemeint.
	 * 
	 * @param i1 AsciiImage 1
	 * @param i2 AsciiImage 2
	 * @return Absolutbetrag der Differenz der Bildgrößen von i1 und i2
	 */
	public int distance(AsciiImage i1, AsciiImage i2) {
		
		return Math.abs(i1.getHeight() * i1.getWidth() - i2.getHeight() * i2.getWidth());
	}
	
}
