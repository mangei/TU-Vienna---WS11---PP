import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Diese generische Klasse implementiert ein spezielles Set auf der Basis von LinkedHashSet 
 * mit der Besonderheit, dass in dem MetricSet mit Hilfe einer Metrik nach Objekten gesucht 
 * werden kann, die einem spezifizierten Objekt ähnlich sind. Beachten Sie dazu das Interface 
 * Metric. Die Klasse soll als Erweiterung der Klasse LinkedHashSet<E> definiert werden. Ein 
 * LinkedHashSet<E> ist Untertyp von HashSet<E> mit der Besonderheit, dass die Einfügereihenfolge 
 * der Elemente erhalten bleibt (siehe Hinweise). Es gibt Methoden, die von der Klasse 
 * LinkedHashSet bzw. deren direkten und indirekten Oberklassen geerbt werden und hier nicht 
 * explizit angegeben sind. Die für diese Aufgabe nützlichen geerbten Methoden sind im Folgenden 
 * angeführt. Für eine vollständige Auflistung der Methoden, siehe java.util.LinkedHashSet und 
 * Oberklasse java.util.HashSet.
 * 
 * @version 10
 * @author Manuel Geier (1126137)
 *
 * @param <E>
 */
public class MetricSet<E> extends LinkedHashSet<E> {

	/**
	 * initialisiert ein leeres MetricSet.
	 */
	public MetricSet() {
	}
	
	/**
	 * initialisiert das MetricSet mit den Elementen aus c.
	 *
	 * @param c
	 */
	public MetricSet(Collection<? extends E> c) {
		addAll(c);
	}
	
	/**
	 * liefert ein neues MetricSet zurück, in dem nur die Elemente enthalten sind, die die 
	 * minimale Distanz zum spezifizierten Element e haben. Das kann auch nur ein Element sein. 
	 * m ist die Metrik, die als Distanzmaß benutzt werden soll.
	 * 
	 * @param e Basiselement
	 * @param m die Metrik, die als Distanzmaß benutzt werden soll
	 * @return neues MetricSet zurück; Elemente welche minimale Distanz zur Basis haben
	 */
	public MetricSet<E> search(E e, Metric<? super E> m) {
		
		MetricSet<E> newMetricSet = new MetricSet<E>();
		E[] metrics = (E[]) super.toArray();
		int[] mVals = new int[size()];
		int min = -1;
		
		// errechne alle werte
		for(int i=0; i<size(); i++) {
			
			mVals[i] = m.distance(e, metrics[i]);
					
			if(i == 0) {
				min = mVals[i];
			} else {
				if(mVals[i] < min) {
					min = mVals[i];
				}
			}
		}
		
		// fuege die minimalen elemente in die neue metrik ein
		for(int i=0; i<size(); i++) {
			if(mVals[i] == min) {
				newMetricSet.add(metrics[i]);
			}
		}
		
		return newMetricSet;
	}
	
}
