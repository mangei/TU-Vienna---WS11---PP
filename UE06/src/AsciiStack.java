
/**
 * Uebungsrunde 6
 *
 * @author Manuel Geier (1126137)
 */
public class AsciiStack {

	private AsciiImage[] imgStack;		// stack
	private int capacity;				// max capacity of the stack
	private int increment;				// in-/decrement value of the capacity
	private int size;					// current size of the stack

	/**
	 * Creates and initializes the stack
	 * @param increment the capacity of the stack is increased/decreased by this value
	 */
	public AsciiStack(int increment) {
		this.increment = increment;
		capacity = increment;
		size = 0;
		imgStack = new AsciiImage[capacity];
	}
	
	/**
	 * Returns the capacity of the stack.
	 * @return capacity
	 */
	public int capacity() {
		return capacity;
	}
	
	/**
	 * Returns true if the stack is empty, otherwise false
	 * @return true if the stack is empty, otherwise false
	 */
	public boolean empty() {
		return size > 0 ? false : true;
	}
	
	/**
	 * Returns the last element within the stack or null if the stack is empty and removes the element from the stack.
	 * @return AsciiImage returns the last element or null if the stack is empty
	 */
	public AsciiImage pop() {
		// return the last element or null if the stack is empty
		if(!empty()) {
			
			// load the recent element
			AsciiImage retImg = imgStack[--size];
			// set it to null
			imgStack[size] = null;
			
			// check if we need to decrease the stack (minimum is the specified increment value)
			if(size < capacity-increment && capacity-increment >= increment) {
				decreaseStack();
			}
			
			return retImg;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the last element or null. The element will not be removed from the stack.
	 * @return AsciiImage returns the last element or null if the stack is empty
	 */
	public AsciiImage peek() {
		// return the last element or null if the stack is empty
		if(!empty()) {
			return imgStack[size-1];
		} else {
			return null;
		}
	}
	
	/**
	 * Adds a new element to the stack.
	 * @param img image to be pushed on the stack
	 */
	public void push(AsciiImage img) {
		// check the capacity of the stack
		if(size >= capacity) {
			increaseStack();
		}
		
		// add the element to the stack and increase the size
		imgStack[size] = img;
		size++;
	}
	
	/**
	 * Retuns the currenct size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Increases the stack capacity by the increment value
	 */
	private void increaseStack() {
		// create new stack
		AsciiImage[] newImgStack = new AsciiImage[capacity += increment];
		
		// copy old images
		for(int i=0; i<size; i++) {
			newImgStack[i] = imgStack[i];
		}
		
		// set the new stack
		imgStack = newImgStack;
	}
	
	/**
	 * Decreases the stack capacity by the increment value
	 */
	private void decreaseStack() {
		// create new stack
		AsciiImage[] newImgStack = new AsciiImage[capacity -= increment];
		
		// copy old images
		for(int i=0; i<size || i<capacity-increment; i++) {
			newImgStack[i] = imgStack[i];
		}
		
		// set the new stack
		imgStack = newImgStack;
	}
}