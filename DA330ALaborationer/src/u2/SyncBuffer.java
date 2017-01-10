package u2;

import java.util.Random;

/**
 * This class represents a synchronized buffer used
 * in transferring letters from a producer to 
 * a consumer 
 * @author Christoffer Strandberg
 */
public class SyncBuffer implements BufferInterface {
	
	private char currentChar;
	private int size;
	private boolean hasNext;
	
	/**
	 * The constructor takes a size
	 * which represent the amount of
	 * characters in the text to be inputed
	 * @param size - the amount of characters to
	 * be inputed
	 */
	public SyncBuffer(int size){
		this.size = size;
	}

	/**
	 * This method is used to retrieve the current
	 * character saved in the buffer
	 */
	public synchronized void putChar(char newChar) {
		if(hasNext){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		currentChar = newChar;
		hasNext = true;
		notifyAll();
	}

	/**
	 * This method is used to change the current
	 * character stored in the buffert
	 */
	public synchronized char getChar() {
		if(!hasNext){
			try {
				wait();
			} catch (InterruptedException e) {}
		}

		hasNext = false;
		notifyAll();
		return currentChar;
	}

	/**
	 * This method is used to retrieve 
	 * the amount of characters in the text
	 * to be transferred
	 */
	public synchronized int getSize() {
		return size;
	}
}
