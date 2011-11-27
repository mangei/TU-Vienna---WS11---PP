import java.util.Scanner;

/**
 * Das Programm BarPlot soll Balkenbeschriftungen und Werte paarweise einlesen 
 * und ein horizontales Balkendiagramm ausgeben.
 * 
 * @author Manuel Geier (1126137)
 */
public class BarPlot {
	
	/**
	 * liest die Daten und Befehle ein und gibt das Ergebnis aus. Allein 
	 * diese Methode liest direkt von System.in ein und gibt direkt auf 
	 * System.out aus.
	 *
	 * @param args uebergebene Argumente (werden nicht benoetigt)
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);	// Eingabeleser
		String label;					// Beschriftung
		int valueInt;					// ganze Zahl
		double valueDouble;				// Gleitkommazahl
		
		// liest das naec
		while(scanner.hasNext()) {
			label = scanner.next();
			
			// liest den naechsten ganzzahligen Wert
			if(scanner.hasNextInt()) {
				valueInt = scanner.nextInt();
				
				// pruefe ob die Zahl im gueltigen Bereich liegt
				if(valueInt >= 0 && valueInt <= 30) {
					
					// Zeichne den Balken
					System.out.println(drawBar(drawLabel(label,8), valueInt));
					
				} else {
					System.out.println("INPUT ERROR");
					break;
				}
			} else {
				
				// wenn der Wert nicht ganzzahlig ist, lies eine Gleitkommazahl
				if(scanner.hasNextDouble()) {
					valueDouble = scanner.nextDouble();
					
					// pruefe ob die Zahl im gueltigen Bereich liegt
					if(valueDouble >= 0.0 && valueDouble <= 1.0) {
						
						// Zeichne den Balken
						System.out.println(drawBar(drawLabel(label,8), valueDouble));
						
					} else {
						System.out.println("INPUT ERROR");
						break;
					}
					
				} else {
				// kein gueltiger wert
					System.out.println("INPUT ERROR");
					break;
				}
			}
		}
	}
	
	/**
	 * Liefert einen String der Laenge n zurueck der nur aus dem Zeichen c 
	 * besteht (beispielsweise liefert repeat('+',4) den String "++++" 
	 * zurueck).
	 *
	 * @param c das Zeichen
	 * @param n Anzahl der Wiederholungen
	 * @return generierte Zeichenkette
	 */
	static String repeat(char c, int n) {
		String str = "";
		for(int i = 0; i < n; i++) {
			str += c;
		}
		return str;
	}
	
	/**
	 * Liefert einen String zurueck der label beinhaltet aber genau n Zeichen 
	 * lang ist. Wenn label zu lange ist wird es abgeschnitten, wenn label 
	 * zu kurz ist, wird der Rueckgabewert mit Leerzeichen aufgefuellt. 
	 * Beispielsweise liefert drawLabel("abc",5) den String "abc  " zurueck.
	 * 
	 * @param label Beschriftung
	 * @param n Laenge der zu generierenden Beschriftung
	 * @return generierte Zeichenkette
	 */
	static String drawLabel(String label, int n) {
		// Laenge des Textes
		int labelLength = label.length();
		
		if(labelLength > n) {
		// Wenn der Text groesser als n ist, wird er abgeschnitten
			return label.substring(0,n);
			
		} else {
			if(labelLength < n) {
			// Wenn der Text kleiner als n ist, wird er mit Leerzeichen aufgefuellt
				int fillLength = n - labelLength;
				return label + repeat(' ', fillLength);
				
			} else {
			// Der Text entspricht genau der Laenge n
				return label;	
			}
		}
	}
	
	/**
	 * Generiert eine Zeile des Balkendiagramms. value bezeichnet dabei die 
	 * absolute Laenge des Balkens.
	 * 
	 * @param label Beschriftung vor dem Balkendiagramm
	 * @param value Ganzzahliger Wert zwischen 0 und 30
	 * @return generiertes Balkendiagramm
	 */
	static String drawBar(String label, int value) {
		int differenz = 30 - value;
		return label + 
			"|" + 
			repeat('#', value) + repeat(' ', differenz) + 
			"|";
	}
	
	/**
	 * Generiert eine Zeile des Balkendiagramms. value bezeichnet die 
	 * prozentuelle Laenge des Balkens. Wird ein Prozentwert als Balken 
	 * dargestellt muss eine ganzzahlige Balkenlaenge berechnet werden. 
	 * Diese soll durch Runden zur naechstgelegenen Ganzzahl entstehen.
	 * 
	 * @param label Beschriftung vor dem Balkendiagramm
	 * @param value Gleitkommawert zwischen 0.0 und 1.0
	 * @return generiertes Balkendiagramm
	 */
	 static String drawBar(String label, double value) {
	 	 // Wandelt den Gleitkommawert in einen ganzzahligen Wert um
	 	 // und ruft die andere Methode auf
	 	 return drawBar(label,(int) Math.round(new Double(value * 30)));
	 }
}