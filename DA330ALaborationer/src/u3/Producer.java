package u3;
/**
 * This class represent the producer 
 * in the Producer/Consumer pattern 
 * @author Christoffer Strandberg
 *
 */
public class Producer extends Thread {
	
	private Buffer buffer;
	private String text;
	private boolean isTextEncrypted;
	private TextCallbackInterface callback;
	
	/**
	 * The constructor takes a buffer, the text to be inputed and
	 * a callback point in which to return information and a boolean
	 * that tells the producer if the input is encrypted
	 * or not.
	 * @param buffer - the buffer
	 * @param text - the text to be inputed
	 * @param callback - the point in which to return information
	 * @param isTextEncrypted - a boolean that tells us if 
	 * the information is encrypted or not
	 */
	public Producer(Buffer buffer, String text,TextCallbackInterface callback,
			boolean isTextEncrypted){
		this.buffer = buffer;
		this.text = text;
		this.isTextEncrypted = isTextEncrypted;
		this.callback = callback;
	}
	
	/**
	 * This method is used to input text into
	 * the buffer
	 */
	public void run(){
		if(!isTextEncrypted){
			for(int i = 0; i < text.length(); i++){
				char curChar = text.charAt(i);
				buffer.addChar(curChar);
				callback.callbackText(curChar+"");
			}
		}else{
			String[] subStrings = text.split(" ");
			for(int i = 0; i < subStrings.length; i++){
				String curStr = subStrings[i];
				buffer.addEncryptedText(curStr);
				callback.callbackText(curStr + " ");
			}
		}
	}
}
