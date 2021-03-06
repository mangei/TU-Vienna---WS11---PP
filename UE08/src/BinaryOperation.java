import java.util.Scanner;

/**
 * This Class provides a method to replace all occurrences of a specified char by another specified
 * char. It implements the Operation interface.
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class BinaryOperation implements Operation {

	private char threshold;
	
	/**
	 * erzeugt eine neue BinaryOperation mit dem entsprechenden Schwellwert.
	 * 
	 * @param threshold Schwellwert
	 */
	public BinaryOperation(char threshold) {
		this.threshold = threshold;
	}

	/**
	 * gibt ein neues AsciiImage zur�ck, das das Bin�rbild des �bergebenen AsciiImage ist. 
	 * Zur Umwandlung in ein Bin�rbild werden alle Zeichen, die im Zeichensatz des Bildes 
	 * vor dem Schwellwert kommen, auf das dunkelste Zeichen gesetzt, alle Zeichen ab dem 
	 * Schwellwert werden auf das hellste Zeichen gesetzt. Sollte das Schwellwertzeichen 
	 * nicht im Zeichensatz des AsciiImage vorkommen, so wird eine OperationException geworfen.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain
	 *            unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 * @throws OperationException
	 *         	  Tritt beim Laden ein Fehler auf (zu wenige oder zu viele Daten bzw. ung�ltige 
	 *            Zeichen), so wird eine OperationException mit einer entsprechenden Fehlermeldung 
	 *            geworfen.
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		String charset = img.getCharset();
		int thresholdIdx = charset.indexOf(threshold);
		
		if(thresholdIdx == -1) {
			throw new OperationException("threshold not in charset");
		}
		
		char darkestColor = charset.charAt(0);
		char brightestColor = charset.charAt(charset.length()-1);
		
		AsciiImage result = new AsciiImage(img);

		// set the new image pixel data
		for (int y = 0, h = img.getHeight(); y < h; y++) {
			for (int x = 0, w = img.getWidth(); x < w; x++) {
				if(charset.indexOf(result.getPixel(x, y)) < thresholdIdx) {
					result.setPixel(x, y, darkestColor);
				} else {
					result.setPixel(x, y, brightestColor);
				}
			}
		}

		return result;

	}

}
