package u3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * This class represent a synchronized Buffer that
 * stores regular text and encrypted text
 * @author Christoffer Strandberg
 */
public class Buffer {
	
	private Queue qText;
	private Queue qEncrypted;
	
	private Semaphore tSlotsEmpty;
	private Semaphore tSlotsFull;
	
	private Semaphore eSlotsEmpty;
	private Semaphore eSlotsFull;
	
	/**
	 * The constructor takes the size of the
	 * queues as a parameter
	 * @param size - the size of the queues
	 */
	public Buffer(int size){
		qText = new LinkedList<Character>();
		qEncrypted = new LinkedList<String>();
		
		tSlotsEmpty = new Semaphore(size);
		tSlotsFull = new Semaphore(0);
		
		eSlotsEmpty = new Semaphore(size);
		eSlotsFull = new Semaphore(0);
	}
	
	/**
	 * This method is used to add a Non-encrypted character 
	 * to the queue. If the queue is full the current thread
	 * will wait for a slot to be free
	 * @param inChar - the character to add
	 */
	public void addChar(char inChar){
		try {
			tSlotsEmpty.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(qText){
			qText.add(inChar);			
		}
		tSlotsFull.release();
	}
	/**
	 * This method is used to add a encrypted character
	 * to the queue in binary as a string. If the queue 
	 * is full the current thread will wait for a slot to 
	 * be free
	 * @param inText- the string to add
	 */
	public void addEncryptedText(String inText){
		try {
			eSlotsEmpty.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(qEncrypted){
			qEncrypted.add(inText);			
		}
		eSlotsFull.release();
	}
	
	/**
	 * This method is used to remove the first
	 * character in the non-encrypted queue
	 * @return the first character in the text 
	 * queue
	 * @throws InterruptedException 
	 */
	public char removeChar() throws InterruptedException{
		char tempChar;
		try{
			tSlotsFull.acquire();
			synchronized(qText){
				tempChar = (char) qText.remove();			
			}
			return tempChar;					
		}finally{
			tSlotsEmpty.release();			
		}

	}
	
	/**
	 * This method is used to remove the first
	 * string in the encrypted queue
	 * @return the first string in the encrypted
	 * queue
	 * @throws InterruptedException 
	 */
	public String removeEncryptedText() throws InterruptedException{
		String tempStr;
		try{
			eSlotsFull.acquire();
			synchronized(qEncrypted){
				tempStr = (String) qEncrypted.remove();			
			}			
			return tempStr;	
		}finally{			
			eSlotsEmpty.release();
		}

	}
	
	/**
	 * This method is used to clear the buffer
	 * from any text still stored. It is
	 * mostly used as a safety measure.
	 */
	public void clearBuffer(){
		qText.clear();
		qEncrypted.clear();
	}
}
