import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Median Operation
 * 
 * @version 9
 * @author Manuel Geier (1126137)
 * 
 */
public class MedianOperation extends FilterOperation {

	/**
	 * Creates a new MedianOperation.
	 */
	public MedianOperation() {
	}
	
	/**
	 * f�hrt mit dem �bergebenen Block den Medianfilter aus. Die Pixel des Blocks werden 
	 * nach ihrem Helligkeitswert sortiert. Der Median (also das in der sortierten Liste 
	 * in der Mitte stehende Zeichen) f�r diesen Block wird als Ergebnis zur�ckgegeben.
	 * 
	 * @param values block
	 * @return Medianwert
	 */
	public int filter(int[] values) {
		
		List<Integer> pixelList = new ArrayList<Integer>();
		for(int i : values) {
			pixelList.add(i);
		}
		
		// order list
		Collections.sort(pixelList);
		
		// return center pixel
		return pixelList.get(4);
	}
}
