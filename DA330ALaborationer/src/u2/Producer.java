package u2;

import java.util.Random;

import javax.swing.JTextArea;

/**
 * This class represents the producer
 * in the consumer/producer pattern.
 * @author Christoffer Strandberg
 */
public class Producer extends Thread {
	private BufferInterface buffer;
	private char[] input;
	private JTextArea textArea;
	private Random rdm;
	
	/**
	 * The constructor takes a text and buffer which is 
	 * used to insert the text letter by letter 
	 * @param buffer - the buffer
	 * @param input - the text to be inputed
	 * @param textArea - the textArea in which to display
	 * the input text
	 */
	public Producer(BufferInterface buffer, char[] input,JTextArea textArea){
		this.buffer = buffer;
		this.input = input;
		this.textArea = textArea;
		this.rdm = new Random();
	}

	/**
	 * The run-method is used to insert the text to the
	 * buffer letter by letter
	 */
	public void run(){
		for(int i = 0; i < input.length; i++){
			
			try {
				Thread.sleep(rdm.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			textArea.append("added: " + input[i] + "\n");
			buffer.putChar(input[i]);
		}
	}
}
