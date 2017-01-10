package u3;

/**
 * This class is used to encrypt or 
 * decrypt data received from the buffer
 * @author Christoffer strandberg
 */
public class Encrypter extends Thread {
	
	private Buffer buffer;
	private boolean isTextEncrypted;
	
	/**
	 * The constructor takes a buffer and 
	 * a boolean that tells the encrypter if
	 * the data is encrypted or not
	 * @param buffer - the buffer to retrieve
	 * information from
	 * @param isTextEncrypted - the boolean
	 * that tell
	 */
	public Encrypter(Buffer buffer, boolean isTextEncrypted){
		this.buffer = buffer;
		this.isTextEncrypted = isTextEncrypted;
	}
	
	/**
	 * This method is used to encrypt or
	 * decryot data retrieved from the buffer
	 * to then put it back in the buffer
	 */
	public void run(){
		if(!isTextEncrypted){
			while(true){
				char curChar;
				try {
					curChar = buffer.removeChar();
					String text = "0" + Integer.toBinaryString(0x100 + curChar).substring(2);
					buffer.addEncryptedText(text);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else{
			while(true){
				String curText;
				try {
					curText = buffer.removeEncryptedText();
					int charCode = Integer.parseInt(curText, 2);
					char curChar = new Character((char)charCode);
					buffer.addChar(curChar);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
