

/**
 * Diese Klasse gl�ttet ein Bild mit einem 3x3-Mittelwertfilter.
 * 
 * @version 9
 * @author Manuel Geier (1126137)
 * 
 */
public class AverageOperation extends FilterOperation {

	/**
	 * Creates a new MedianOperation.
	 */
	public AverageOperation() {
	}

	/**
	 * f�hrt mit dem �bergebenen Block den Mittelwertfilter aus. Daf�r wird das arithmetische 
	 * Mittel (vgl. Arithmetisches_Mittel) der Helligkeitswerte bestimmt. Das Ergebnis wird 
	 * mathematisch gerundet und als Ergebnis f�r diesen Block zur�ckgegeben.
	 * 
	 * @param values block
	 * @return Mittelwert 
	 */
	public int filter(int[] values) {
		int n = 0;
		for(int i: values) n += i;
		return (int) Math.round(n / (double)values.length);
	}
}
