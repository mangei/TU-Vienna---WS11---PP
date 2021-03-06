
/**
 * FactoryException extends Exception
 * Diese Klasse erweitert Exception und wird zum Behandeln aller Fehlerf�lle, die in einer 
 * Factory, also beim Einlesen von Parametern oder dem Erzeugen eines Befehls, auftreten, 
 * eingesetzt. Sie k�nnen bei Bedarf auch noch weitere Konstruktoren definieren.
 * 
 * @author Manuel Geier (1126137)
 * @version 8
 *
 */
public class FactoryException extends Exception {

	/**
	 * erzeugt eine leere FactoryException. Ruft den entsprechenden Super-Konstruktor in 
	 * der Klasse Exception auf.
	 */
	public FactoryException() {
	}
	
	/**
	 * erzeugt eine FactoryException mit der entsprechenden Fehlerbeschreibung. Ruft den 
	 * entsprechenden Super-Konstruktor in der Klasse Exception auf.
	 * 
	 * @param message Nachricht
	 */
	public FactoryException(String message) {
		super(message);
	}
	
}
