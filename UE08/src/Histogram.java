import java.util.HashMap;

/**
 * Histogram
 * 
 * @author Manuel Geier (1126137)
 * @version 8
 *
 */
public class Histogram {

	private static final char BAR_CHAR = '#';
	private static final char BACKGROUND_CHAR = '.';
	private static final String HISTOGRAM_BASIC_CHARSET = "0123456789" + BAR_CHAR + BACKGROUND_CHAR;
	private static final int VALUE_LABEL_WIDTH = 3;
	private static final int HISTOGRAM_IMAGE_HEIGHT = 16;
	private static final int HISTOGRAM_BAR_HEIGHT = HISTOGRAM_IMAGE_HEIGHT - 1;
	
	/**
	 * Returns a histogram from the image
	 * @param img image basis for the histogram
	 * @return histogram image
	 */
	public static AsciiImage getHistogram(AsciiImage img) {
		
		// image char count
		int imageCharCount = img.getHeight() * img.getWidth();
		
		// combine the histogram charset with the image charset
		String histogramCharset = mergeCharset(HISTOGRAM_BASIC_CHARSET, img.getCharset());
		
		// new histogram image
		AsciiImage histogramImage = new AsciiImage(VALUE_LABEL_WIDTH + img.getCharset().length(), HISTOGRAM_IMAGE_HEIGHT, histogramCharset);
		
		// store the occourences of the characters in a map
		HashMap<Character, Integer> imageCharOccourenceMap = new HashMap<Character, Integer>();
		
		
		// draw bottom labels and initialize the map
		char[] imageCharset = img.getCharset().toCharArray();
		for(int i = 0, l = imageCharset.length; i < l; i++) {
			
			histogramImage.setPixel(VALUE_LABEL_WIDTH + i, HISTOGRAM_BAR_HEIGHT, imageCharset[i]);
			imageCharOccourenceMap.put(imageCharset[i], 0);
			
		}
		
		
		// calculate occourences and store the maximum occourence value
		int maxOccourence = 0;
		for(int y = 0, h = img.getHeight(); y < h; y++) {
			for(int x = 0, w = img.getWidth(); x < w; x++) {
				
				// get the character
				Character ch = img.getPixel(x, y);
				
				// increase the count of the character
				int newValue = imageCharOccourenceMap.get(ch) + 1;
				imageCharOccourenceMap.put(ch, newValue);
				
				// store the max occourence value
				if(newValue > maxOccourence) {
					maxOccourence = newValue;
				}
			}
		}
		
		
		// max occourence of a character in percent
		double maxOccourencedPerc = maxOccourence * 100 / imageCharCount;
		
		// step between two values
		double step = maxOccourencedPerc / (double) HISTOGRAM_BAR_HEIGHT;
		
		// list, which contains all values per row in percent
		double[] percPerRow = new double[HISTOGRAM_BAR_HEIGHT];
		
		
		// calculate the steps and store the value
		double maxOccourenceStepPerc = maxOccourencedPerc;
		for(int y = 0; y < HISTOGRAM_BAR_HEIGHT; y++) {
		
			percPerRow[y] = maxOccourenceStepPerc;
			maxOccourenceStepPerc -= step;
		}
		
		
		// draw occourence value labels
		for(int y = 0; y < HISTOGRAM_BAR_HEIGHT; y+=2) {

			// round the value and 
			int rowValue = (int) Math.round(percPerRow[y]);
			char[] rowValueArr = Integer.toString(rowValue).toCharArray();
			
			// draw digits right aligned
			for(int i = VALUE_LABEL_WIDTH-1, j = rowValueArr.length-1; i >= 0 && j >= 0; i--, j--) {
				histogramImage.setPixel(i, y, rowValueArr[j]);
			}
		}
		
		
		// draw histogram bars for each image char
		for(int i = 0, l = imageCharset.length; i < l; i++) {
			
			// get the calculated value out of the map
			int imageCharOccourenceValue = imageCharOccourenceMap.get(imageCharset[i]);
			
			// calculate percent
			double imageCharOccourenceValuePercent = imageCharOccourenceValue * 100.0 / imageCharCount;
			
			// draw the bar
			for(int y = HISTOGRAM_BAR_HEIGHT-1; y >= 0 && imageCharOccourenceValuePercent > 0.0; y--, imageCharOccourenceValuePercent -= step) {
				histogramImage.setPixel(VALUE_LABEL_WIDTH + i, y, BAR_CHAR);
			}
		}
		
		
		return histogramImage;
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
