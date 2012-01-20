
/**
 * This Class provides a method to create a new AsciiImage. It implements the Operation interface.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class CreateOperation implements Operation {

	private int width;
	private int height;
	private String charset;
	
	/**
	 * erzeugt eine neue CreateOperation, die ein neues Bild mit angegebener Bildgöße und 
	 * Zeichensatz erzeugt. Alle Pixel werden mit dem "hellsten" Zeichen, d.h. dem Zeichen 
	 * mit größten Index in charset initialisiert.
	 * 
	 * @param width width of the new AsciiImage
	 * @param height height of the new AsciiImage
	 * @param charset charset of the new AsciiImage
	 */
	public CreateOperation(int width, int height, String charset) {
		this.width = width;
		this.height = height;
		this.charset = charset;
	}
	
	/**
	 * gibt ein neues AsciiImage zurück. Der Parameter wird ignoriert (beispielsweise kann auch 
	 * null übergeben werden).
	 * 
	 * @param img
	 *            not in use. will be ignored.
	 * @return A new AsciiImage
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		return new AsciiImage(width, height, charset);

	}

}
