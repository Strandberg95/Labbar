package u3;

import javax.swing.JTextArea;

/**
 * This class represents a controller in the MCV
 * pattern that this project is implemented as
 * @author Christoffer Strandberg
 *
 */
public class Controller {
	
	private Buffer buffer;
	private ConsumerCallback consumerCallback;
	private ProducerCallback producerCallback;
	
	private Consumer consumer;
	private Producer producer;
	private Encrypter encrypter;
	private FileReader reader;
	
	private int bufferSize;
	
	/**
	 * The constructor takes a textarea to be given 
	 * as parameter to ConsumerCallback and one to be
	 * given to ProducerCallback 
	 * @param txtDst - the textArea that will display 
	 * the encrypted text
	 * @param txtSrc - the textArea that will display 
	 * the original text
	 * @param bufferSize - the size of the buffer
	 */
	public Controller(JTextArea txtDst, JTextArea txtSrc, int bufferSize){
		this.consumerCallback = new ConsumerCallback(txtDst);
		this.producerCallback = new ProducerCallback(txtSrc);
		this.reader = new FileReader();
		this.bufferSize = bufferSize;
		
	}
	
	/**
	 * This method is used to start the encryption
	 * of the text. It takes a location of a file 
	 * and a boolean that tells the method if the
	 * text is encrypted or not. The method later
	 * proceeds to read the file and starts the process
	 * @param URL - the text files location
	 * @param isTextEncrypted - the boolean that
	 * tells the method if the text is encrypted 
	 * or not
	 */
	public void processText(String URL,boolean isTextEncrypted){
		stopThreads();
		buffer = new Buffer(bufferSize);
		String text = reader.readFile(URL);
		producer = new Producer(buffer,text,producerCallback,isTextEncrypted);
		consumer = new Consumer(buffer,consumerCallback,isTextEncrypted);
		encrypter = new Encrypter(buffer,isTextEncrypted);
		startThreads();
	}
	
	/**
	 * This method is used to stop the current
	 * threads. It is mainly used as a safety measure
	 * when restarting the process in a program that
	 * is already running
	 */
	private void stopThreads(){
		try{
			if(producer.isAlive()){
				producer.interrupt();
			}
		}catch(NullPointerException e){}

		try{
			if(consumer.isAlive()){
				consumer.interrupt();
			}
		}catch(NullPointerException e){}

		try{
			if(encrypter.isAlive()){
				encrypter.interrupt();
			}
		}catch(NullPointerException e){}
	}
	
	/**
	 * This method is used to start the threads
	 */
	private void startThreads(){
		producer.start();
		encrypter.start();
		consumer.start();
	}
	
	/**
	 * This method is used to clear the current
	 * text shown in the textAreas 
	 */
	public void clearText(){
		consumerCallback.clearText();
		producerCallback.clearText();
	}
	
}
