import java.util.HashMap;
import java.util.Scanner;

/**
 * Uebungsrunde 9
 *
 * @version 9
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
		
		HashMap<String,Factory> factoryMap = new HashMap<String, Factory>();
		factoryMap.put("clear", 		new ClearFactory());
		factoryMap.put("load", 			new LoadFactory());
		factoryMap.put("replace", 		new ReplaceFactory());
		factoryMap.put("filter", 		new FilterFactory());
		factoryMap.put("binary", 		new BinaryFactory());
		
		Scanner scanner = new Scanner(System.in);		// Scanner to read the input;
		
		boolean createOperationPerformed = false;		// create operation performed flag
		boolean errorOperationFailed = false;			// operation failed error flag
		boolean errorInputMismatch = false;				// input mismatch flag
		boolean errorUnknownCommand = false;			// unknown command flag
		
		AsciiImage img = null;
		AsciiStack imgStack = new AsciiStack();
		
		// loop operations, as long as no error occoured
		while(!errorOperationFailed && !errorInputMismatch && !errorUnknownCommand && scanner.hasNext()) {
			String operation = scanner.next();
			
			if("create".equals(operation)) {
			// create operation
				
				if(!createOperationPerformed) {
				
					// width attribute
					if(scanner.hasNextInt()) {
						int imageWidth = scanner.nextInt();
						
						// check width
						if(imageWidth > 0) {
						
							// height attribute
							if(scanner.hasNextInt()) {
								int imageHeight = scanner.nextInt();
							
								// check height
								if(imageHeight > 0) {
								
									// height attribute
									if(scanner.hasNext()) {
										String imgCharset = scanner.next();
										
										img = new AsciiImage(imageWidth, imageHeight, imgCharset);
										createOperationPerformed = true;
											
									}  else {
										errorInputMismatch = true;
									}
								} else {
									errorInputMismatch = true;
								}
							}  else {
								errorInputMismatch = true;
							}
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
				} else {
					errorUnknownCommand = true;
				}
				
			} else if(createOperationPerformed) {
			// all operations where the create operation is mandantory
				
				// get the factory
				Factory factory = factoryMap.get(operation);
				
				if(factory != null) {
					try {
						
						// create the operation
						Operation op = factory.create(scanner);
						
						try {
							// place a copy of the image in the stack
							imgStack.push(new AsciiImage(img));
							
							// perform operation
							img = op.execute(img);
							
						} catch (OperationException e) {
							errorOperationFailed = true;
						}
						
					} catch (FactoryException e) {
						errorInputMismatch = true;
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
				errorInputMismatch = true;
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