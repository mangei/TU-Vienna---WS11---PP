
/**
 * Operation Exception
 * 
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class OperationException 
	extends Exception
{

	/**
	 * Erzeugt eine leere OperationException. Ruft den entsprechenden Super-Konstruktor 
	 * in der Klasse Exception auf.
	 */
	public OperationException() {
		super();
	}
	
	/**
	 * Erzeugt eine OperationException mit der entsprechenden Fehlerbeschreibung. 
	 * Ruft den entsprechenden Super-Konstruktor in der Klasse Exception auf.
	 * 
	 * @param message
	 * 			Fehlerbeschreibung
	 */
	public OperationException(final String message) {
		super(message);
	}
	
}
