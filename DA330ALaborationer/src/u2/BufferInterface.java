package u2;

/**
 * This interface creates a contract
 * between the buffers to make sure that
 * both buffers have the same basic methods.
 * It also allows us to use the same reference 
 * @author Christoffer Strandberg
 */
public interface BufferInterface {
	
	public void putChar(char newChar);
	
	public char getChar();
	
	public int getSize();
}
