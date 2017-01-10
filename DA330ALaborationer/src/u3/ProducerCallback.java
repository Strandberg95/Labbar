package u3;

import javax.swing.JTextArea;

/**
 * This class is used to add data 
 * to a textArea showing which data
 * the producer is processing. 
 * @author Christoffer Strandberg
 */
public class ProducerCallback implements TextCallbackInterface{
	
	private JTextArea txt;
	
	/**
	 * The constructor take the current Textarea
	 * as parameter in which the object later 
	 * on will append text to
	 * @param txt - the textarea
	 */
	public ProducerCallback(JTextArea textArea){
		this.txt = textArea;
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
