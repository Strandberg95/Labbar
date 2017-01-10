package u2;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents a buffer (that is not
 * synchronized) used in transferring letters from 
 * a producer to a consumer
 * @author Christoffer Strandberg
 */
public class ASyncBuffer implements BufferInterface {
	
	private char currentChar;
	private int size;
	
	/**
	 * The constructor takes a size
	 * which represent the amount of
	 * characters in the text to be inputed
	 * @param size - the amount of characters to
	 * be inputed
	 */
	public ASyncBuffer(int size){
		this.size = size;
	}

	/**
	 * This method is used to retrieve the current
	 * character saved in the buffer
	 */
	public void putChar(char newChar) {
		currentChar = newChar;
	}

	/**
	 * This method is used to change the current
	 * character stored in the buffert
	 */
	public char getChar() {
		return currentChar;
	}

	/**
	 * This method is used to retrieve 
	 * the amount of characters in the text
	 * to be transferred
	 */
	public int getSize() {
		return size;
	}
}
