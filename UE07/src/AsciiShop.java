import java.util.Scanner;

/**
 * Uebungsrunde 7
 *
 * @version 7
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
				
				if("load".equals(operation)) {
				// load operation
					
					if(scanner.hasNext()) {
						
						String endStr = scanner.next();
						
						String loadImage = "";
						boolean eof = false;
						while(!eof) {
							if(scanner.hasNext()) {
								
								String line = scanner.next();
								
								// Check if it is the end string
								if(line.equals(endStr)) {
									eof = true;
									
								} else {
									// add the line
									loadImage += line + '\n';
										
								}
							} else {
								eof = true;
							}
						}
						
						// check if the load operation was valid
						try {
							// place a copy of the image in the stack
							imgStack.push(new AsciiImage(img));
							
							// perform replace operation
							img = new LoadOperation(loadImage).execute(img);
						} catch (OperationException e) {
							errorOperationFailed = true;
						}
						
					} else {
						errorInputMismatch = true;
					}
				
				}  else if ("replace".equals(operation)) {
				// replace operation
					
					// read oldChar attribute
					if(scanner.hasNext()) {
						String oldCharStr = scanner.next();
						
						if(oldCharStr.length() == 1) {
							char oldChar = oldCharStr.charAt(0);
						
							// read newChar attribute
							if(scanner.hasNext()) {
								String newCharStr = scanner.next();
								
								if(newCharStr.length() == 1) {
									char newChar = newCharStr.charAt(0);
									
									// place a copy of the image in the stack
									imgStack.push(new AsciiImage(img));
									
									try {
										// perform replace operation
										img = new ReplaceOperation(oldChar, newChar).execute(img);
									} catch (OperationException e) {
										errorOperationFailed = true;
									}
									
								} else {
									errorInputMismatch = true;
								}
							} else {
								errorInputMismatch = true;
							}
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
					
				} else if ("clear".equals(operation)) {
				// clear operation
				
					// place a copy of the image in the stack
					imgStack.push(new AsciiImage(img));
				
					// perform clear operation
					img = new ClearOperation().execute(img);
				
				} else if ("print".equals(operation)) {
				// print operation
				
					System.out.println(img.toString());
				 
				} else if ("filter".equals(operation)) {
				// clear operation
				
					// read newChar attribute
					if(scanner.hasNext()) {
						String filter = scanner.next();
						
						if("median".equals(filter)) {
							
							// place a copy of the image in the stack
							imgStack.push(new AsciiImage(img));
							
							// perform median filter operation
							img = new MedianOperation().execute(img);
							
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
				
				} else if ("undo".equals(operation)) {
				// undo operation
				
					AsciiImage prevImg = imgStack.pop();
					if(prevImg == null) {
						System.out.println("STACK EMPTY");
					} else {
						img = prevImg;
					}
				
				} else if ("line".equals(operation)) {
				// line operation
					
					// read x0 attribute
					if(scanner.hasNextInt()) {
						int x0 = scanner.nextInt();
						
						// read y0 attribute
						if(scanner.hasNextInt()) {
							int y0 = scanner.nextInt();
							
							// read x1 attribute
							if(scanner.hasNextInt()) {
								int x1 = scanner.nextInt();
								
								// read y1 attribute
								if(scanner.hasNextInt()) {
									int y1 = scanner.nextInt();
							
									// read c attribute
									if(scanner.hasNext()) {
										String cStr = scanner.next();
										
										if(cStr.length() == 1) {
											char c = cStr.charAt(0);
											
											// check attributes
											if(x0 >= 0 && x1 >= 0 && 
												x0 < img.getWidth() && x1 < img.getWidth() &&
												y0 >= 0 && y1 >= 0 &&
												y0 < img.getHeight() && y1 < img.getHeight()) {
												
												// place a copy of the image in the stack
												imgStack.push(new AsciiImage(img));
												
												// perform line operation
												img = new LineOperation(x0, y0, x1, y1, c).execute(img);
												
											} else {
												errorOperationFailed = true;
											}
										} else {
											errorInputMismatch = true;
										}
									} else {
										errorInputMismatch = true;
									}
								} else {
									errorInputMismatch = true;
								}
							} else {
								errorInputMismatch = true;
							}
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
					
				} else if ("grow".equals(operation)) {
				// grow operation
				
					// read c attribute
					if(scanner.hasNext()) {
						String cStr = scanner.next();
						
						if(cStr.length() == 1) {
							char c = cStr.charAt(0);
						
							// place a copy of the image in the stack
							imgStack.push(new AsciiImage(img));
						
							// perform grow operation
							img = new GrowRegionOperation(c).execute(img);
							
						} else {
							errorInputMismatch = true;
						}
					} else {
						errorInputMismatch = true;
					}
				
				} else if ("transpose".equals(operation)) {
				// transpose operation
				
					// place a copy of the image in the stack
					imgStack.push(new AsciiImage(img));
				
					// perform transpose operation
					img = new TransposeOperation().execute(img);
				
				} else if("fill".equals(operation)) {
				// fill operation
					
					// read x attribute
					if(scanner.hasNextInt()) {
						int x = scanner.nextInt();
						
						// read y attribute
						if(scanner.hasNextInt()) {
							int y = scanner.nextInt();
							
							// read c attribute
							if(scanner.hasNext()) {
								String cStr = scanner.next();
								
								if(cStr.length() == 1) {
									char c = cStr.charAt(0);
									
									// check attributes
									if(x >= 0 && x < img.getWidth() && y >= 0 && y < img.getHeight()) {
										
										// place a copy of the image in the stack
										imgStack.push(new AsciiImage(img));
										
										// perform fill operation
										img = new FillOperation(x, y, c).execute(img);
										
									} else {
										errorOperationFailed = true;
									}
								} else {
									errorInputMismatch = true;
								}
							} else {
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