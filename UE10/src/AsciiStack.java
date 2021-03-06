
/**
 * Uebungsrunde 7
 *
 * @version 7
 * @author Manuel Geier (1126137)
 * 
 */
public class AsciiStack {

	private AsciiStackNode first;		// points to the first node of the stack

	/**
	 * Creates an empty stack
	 */
	public AsciiStack() {
	}
	
	/**
	 * Returns true if the stack is empty, otherwise false
	 * @return true if the stack is empty, otherwise false
	 */
	public boolean empty() {
		return first == null;
	}
	
	/**
	 * Returns the last element within the stack or null if the stack is empty and removes the element from the stack.
	 * @return AsciiImage returns the last element or null if the stack is empty
	 */
	public AsciiImage pop() {
		// return the last element or null if the stack is empty
		if(!empty()) {
			
			AsciiStackNode firstNode = first;		// store the current first node
			first = first.getNext();				// set the new first node
			
			return firstNode.getImage();			// return the image of the stored first node
		}
		
		return null;
	}
	
	/**
	 * Returns the last element or null. The element will not be removed from the stack.
	 * @return AsciiImage returns the last element or null if the stack is empty
	 */
	public AsciiImage peek() {
		// return the last element or null if the stack is empty
		return (!empty()) ? first.getImage() : null;
	}
	
	/**
	 * Adds a new element to the stack.
	 * @param img image to be pushed on the stack
	 */
	public void push(AsciiImage img) {
		first = new AsciiStackNode(img, first);		// add a new node at the beginning
	}
	
	/**
	 * Retuns the currenct size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return (first != null) ? first.size() : 0;
	}
	
	private class AsciiStackNode {
		
		private AsciiImage image;
		private AsciiStackNode next;
		
		public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
			this.image = image;
			this.next = next;
		}
		
		public AsciiImage getImage() {
			return image;
		}
		
		public AsciiStackNode getNext() {
			return next;
		}
		
		public int size() {
			return (next != null) ? next.size() + 1 : 1;
		}
	}
}