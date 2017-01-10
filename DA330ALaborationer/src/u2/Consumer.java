package u2;

import java.util.Random;

import javax.swing.JTextArea;

/**
 * This class represent the consumer in 
 * the producer/consumer pattern
 * @author Christoffer Strandberg
 */
public class Consumer extends Thread {
	private BufferInterface buffer;
	private JTextArea textArea;
	private Random rdm;
	
	/**
	 * The constructor takes a buffer and
	 * a textArea. The buffer is used to
	 * retrieve information to show in the
	 * textArea
	 * @param buffer - the buffer
	 * @param textArea - the textAre where 
	 * the information is shown
	 */
	public Consumer(BufferInterface buffer,JTextArea textArea){
		this.buffer = buffer;
		this.textArea = textArea;
		this.rdm = new Random();
	}
	
	/**
	 * The run-method is used to retrieve 
	 * information from the buffer to then
	 * show show it in the textArea
	 */
	public void run(){
		for(int i = 0; i < buffer.getSize(); i++){
			try {
				Thread.sleep(rdm.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			textArea.append("Retrieved: " + buffer.getChar() + "\n");
		}
	}
	
}
