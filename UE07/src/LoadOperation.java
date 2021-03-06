import java.util.Scanner;

/**
 * This Class provides a method to replace all occurrences of a specified char by another specified
 * char. It implements the Operation interface.
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class LoadOperation implements Operation {

	private String data;
	
	/**
	 * erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten. Diese Bilddaten 
	 * liegen als String vor, wobei die Bildzeilen durch Zeilenumbr�che (�\n�) getrennt sind.
	 * @param data
	 */
	public LoadOperation(String data) {
		this.data = data;
	}

	/**
	 * Gibt ein neues AsciiImage zur�ck, das von Gr��e und Zeichensatz dem �bergebenen AsciiImage 
	 * entspricht und in das die Daten geladen wurden. Tritt beim Laden ein Fehler auf (zu wenige 
	 * oder zu viele Daten bzw. ung�ltige Zeichen), so wird eine OperationException mit einer 
	 * entsprechenden Fehlermeldung geworfen.
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

		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		String charset = img.getCharset();
		
		// check image height and width
		Scanner sc = new Scanner(data);
		
		int rowCount = 0;
		while(sc.hasNext()) {
			
			if(++rowCount > imgHeight) {
				throw new OperationException("loaded image does not match the origin image height: " + rowCount + " instead of " + imgHeight);
			}
			
			int l = sc.next().length();
			if(l != imgWidth) {
				throw new OperationException("loaded image does not match the origin image width: " + l + " instead of " + imgWidth);
			}
			
		}
		
		// check image height
		if(rowCount < imgHeight) {
			throw new OperationException("loaded image does not match the origin image height: " + rowCount + " instead of " + imgHeight);
		}
		
		// check charset
		for(char c : data.toCharArray()) {
			if(c != '\n' && charset.indexOf(c) < 0) {
				throw new OperationException("loaded image does not match the defined charset");
			}
		}
		
		AsciiImage result = new AsciiImage(img);

		// set the new image pixel data
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				result.setPixel(x, y, data.charAt((imgWidth + 1) * y + x));
			}
		}

		return result;

	}

}
