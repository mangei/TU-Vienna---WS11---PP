import java.util.HashMap;
import java.util.Scanner;

/**
 * Uebungsrunde 10
 *
 * @version 10
 * @author Manuel Geier (1126137)
 * 
 */
public class AsciiShop {
	
	/**
	 * Reads data and operations and prints resolutions. Only this method
	 * reads directly from System.in and prints on System.out.
	 *
	 * @param args program arguments (not needed)
	 */
	public static void main(String[] args) {
		
		// all saved metrics
		MetricSet<AsciiImage> metricSet = new MetricSet<AsciiImage>();
		
		// operation factory map
		HashMap<String,Factory> factoryMap = new HashMap<String, Factory>();
		factoryMap.put("clear", 		new ClearFactory());
		factoryMap.put("load", 			new LoadFactory());
		factoryMap.put("replace", 		new ReplaceFactory());
		factoryMap.put("filter", 		new FilterFactory());
		factoryMap.put("binary", 		new BinaryFactory());
		factoryMap.put("save", 			new SaveFactory(metricSet));
		factoryMap.put("create", 		new CreateFactory());
		
		// search operation factory map
		HashMap<String,Factory> searchFactoryMap = new HashMap<String, Factory>();
		searchFactoryMap.put("pixelcount", 	new SearchFactory(metricSet, new PixelCountMetric()));
		searchFactoryMap.put("uniquechars", new SearchFactory(metricSet, new UniqueCharsMetric()));
		
		Scanner scanner = new Scanner(System.in);		// Scanner to read the input;
		
		boolean createOperationPerformed = false;		// create operation performed flag
		boolean errorOperationFailed = false;			// operation failed error flag
		boolean errorInputMismatch = false;				// input mismatch flag
		boolean errorUnknownCommand = false;			// unknown command flag
		
		AsciiImage img = null;							// actual image
		AsciiStack imgStack = new AsciiStack();			// image stack
		
		// loop operations, as long as no error occoured
		while(!errorOperationFailed && !errorInputMismatch && !errorUnknownCommand && scanner.hasNext()) {
			String operation = scanner.next();
			
			if(createOperationPerformed || "create".equals(operation)) {
					
				// get the factory
				Factory factory = factoryMap.get(operation);
				
				if(factory != null) {
					try {
						
						// create the operation
						Operation op = factory.create(scanner);
						
						try {
							
							// place a copy of the image in the stack
							if(img != null) {
								imgStack.push(new AsciiImage(img));
							}
							
							// perform operation
							img = op.execute(img);
							
							if(!createOperationPerformed && "create".equals(operation)) {
								createOperationPerformed = true;
							}
							
						} catch (OperationException e) {
							errorOperationFailed = true;
						}
						
					} catch (FactoryException e) {
						errorInputMismatch = true;
					}
					
				} else if ("search".equals(operation)) {
				// search operation
				
					if(metricSet.size() > 0 ) {
					
						String searchOperation = scanner.next();
						
						// get the factory
						Factory searchFactory = searchFactoryMap.get(searchOperation);
						
						if(searchFactory != null) {
							try {
								
								// create the operation
								Operation sOp = searchFactory.create(scanner);
								
								try {
									// place a copy of the image in the stack
									imgStack.push(new AsciiImage(img));
									
									// perform operation
									img = sOp.execute(img);
									
								} catch (OperationException e) {
									errorOperationFailed = true;
								}
								
							} catch (FactoryException e) {
								errorInputMismatch = true;
							}
							
						}
					} else {
						errorOperationFailed = true;
					}
				 
				} else if ("printsaved".equals(operation)) {
				// printsaved operation
				
					if(metricSet.size() > 0) {
						
						for(AsciiImage ai : metricSet) {
							System.out.println(ai);
						}
						
					} else {
						System.out.println("NO SAVED IMAGES");
					}
					
				} else if ("print".equals(operation)) {
				// print operation
				
					System.out.println(img.toString());
				 
				} else if ("undo".equals(operation)) {
				// undo operation
				
					AsciiImage prevImg = imgStack.pop();
					if(prevImg == null) {
						System.out.println("STACK EMPTY");
					} else {
						img = prevImg;
					}
					
				} else {
					errorUnknownCommand = true;
				}
			
			} else {
				errorUnknownCommand = true;
			}
		}
		
		// Check errors and print message
		if(errorOperationFailed) {
			System.out.println("OPERATION FAILED");
		}
		if(errorInputMismatch) {
			System.out.println("INPUT MISMATCH");
		}
		if(errorUnknownCommand) {
			System.out.println("UNKNOWN COMMAND");
		}
	
	}
	
}