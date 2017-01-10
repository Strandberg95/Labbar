package u3;

import javax.swing.JTextArea;

/**
 * This class is used to add data 
 * to a textArea showing which data
 * the consumer is processing. 
 * @author Christoffer Strandberg
 */
public class ConsumerCallback implements TextCallbackInterface {
	
	private JTextArea txt;
	
	/**
	 * The constructor take the current Textarea
	 * as parameter in which the object later 
	 * on will append text to
	 * @param txt - the textarea
	 */
	public ConsumerCallback(JTextArea txt){
		this.txt = txt;
	}

	/**
	 * This method is used to append text
	 * to the textArea when called
	 */
	public void callbackText(String text) {
		txt.append(text);
	}
	
	/**
	 * This method is used to clear
	 * the textArea
	 */
	public void clearText(){
		txt.setText("");
	}

}
