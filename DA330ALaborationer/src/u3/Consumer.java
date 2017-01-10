package u3;

/**
 * This class represents the consumer
 * in the producer/consumer pattern
 * @author Christoffer Strandberg
 */
public class Consumer extends Thread {
	
	private Buffer buffer;
	private TextCallbackInterface callback;
	private boolean isTextEncrypted;
	
	/**
	 * The constructor takes a buffer, a callback point
	 * in which to return information and a boolean
	 * that tells the consumer if the input is encrypted
	 * or not.
	 * @param buffer - the buffer
	 * @param callback - the point in which to return information
	 * @param isTextEncrypted - a boolean that tells us if 
	 * the information is encrypted or not
	 */
	public Consumer(Buffer buffer, TextCallbackInterface callback, boolean isTextEncrypted){
		this.buffer = buffer;
		this.callback = callback;
		this.isTextEncrypted = isTextEncrypted;
	}
	
	/**
	 * This method will retrieve information from 
	 * the buffer and then sends it to ConsumerCallback
	 * via callback to then be displayed
	 */
	public void run(){
		System.out.println("Consumer: Starting thread");
		if(!isTextEncrypted){
			while(true){
				String text;
				try {
					text = buffer.removeEncryptedText();
					callback.callbackText(text+" ");
				} catch (InterruptedException e) {
					
				}
			}			
		}else{
			while(true){
				char text;
				try {
					text = buffer.removeChar();
					callback.callbackText(text+"");
				} catch (InterruptedException e) {
					
				}
				
			}	
		}
	}
}
