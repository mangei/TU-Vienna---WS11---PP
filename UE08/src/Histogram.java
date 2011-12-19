import java.util.HashMap;

/**
 * Histogram
 * 
 * HINWEIS: NICHT FERTIG PROGRAMMIERT
 * 
 * @author Manuel Geier (1126137)
 * @version 8
 *
 */
public class Histogram {

	private static String charsetBasic = "0123456789#.";
	
	public static AsciiImage getHistogram(AsciiImage img) {
		
		String charsetExt = mergeCharset(charsetBasic, img.getCharset());
		
		// New histogram image
		AsciiImage histoImg = new AsciiImage(3 + img.getCharset().length(), 16, charsetExt);
		
		// Store the occourences of the characters
		HashMap<Character, Integer> histoMap = new HashMap<Character, Integer>();
		
		
		// Draw bottom labels and initialize the map
		char[] chArr = img.getCharset().toCharArray();
		for(int i = 0, l = chArr.length; i < l; i++) {
			
			histoImg.setPixel(3+i, 15, chArr[i]);
			histoMap.put(chArr[i], 0);
			
		}
		
		// calculate occourences
		int maxOccourence = 0;
		for(int y = 0, h = img.getHeight(); y < h; y++) {
			for(int x = 0, w = img.getWidth(); x < w; x++) {
				Character ch = img.getPixel(x, y);
				int newValue = histoMap.get(ch) + 1;
				histoMap.put(ch, newValue);
				
				if(newValue > maxOccourence) {
					maxOccourence = newValue;
				}
				
			}
		}
		
		/**
		 * percPerRow: list, which contains all values per row in percent
		 * maxOccourence: max occourence of a character
		 * maxOccourencedPerc: max occourence of a character in percent
		 * 
		 */
		double maxOccourencedPerc = maxOccourence * 100 / (img.getHeight()*img.getWidth());
		double step = maxOccourencedPerc / 16d;
		
		double[] percPerRow = new double[16];
		
		// draw step labels
		double maxOccourenceStepPerc = maxOccourencedPerc;
		for(int y = 0; y < 16; y++) {
		
			percPerRow[y] = maxOccourenceStepPerc;
			maxOccourenceStepPerc -= step;
		}
		
		for(int y = 0; y < 16; y+=2) {
			
			System.out.println(percPerRow[y]);

			int rowValue = (int) Math.round(percPerRow[y]);
			
			if(rowValue >= 100) {
				histoImg.setPixel(0, y, (""+rowValue).charAt((""+rowValue).length()-3));
			}
			if(rowValue >= 10) {
				histoImg.setPixel(1, y, (""+rowValue).charAt((""+rowValue).length()-2));
			}
			if(rowValue >= 1) {
				histoImg.setPixel(2, y, (""+rowValue).charAt((""+rowValue).length()-1));
			}
			
			maxOccourenceStepPerc -= step * 2;
		}
		
		
		// draw histo bars
		for(int i = 0, l = chArr.length; i < l; i++) {
			
			int histoVal = histoMap.get(chArr[i]);
			
			for(int y = 15; y >= 0 && histoVal >= 0; y--) {
				histoImg.setPixel(3+i, y, '#');
				histoVal -= step;
			}
		}
		
		return histoImg;
	}
	
	/**
	 * Merges one charset with another. The new characters from charset2 will be added at 
	 * the beginning of the first charset1.
	 * 
	 * @param charset1 charset1
	 * @param charset2 charset2
	 * @return merged charset
	 */
	private static String mergeCharset(String charset1, String charset2) {
		
		for(char ch : charset2.toCharArray()) {
			if(charset1.indexOf(ch) == -1) {
				charset1 = ch + charset1;
			}
		}
		
		return charset1;
	}
}
