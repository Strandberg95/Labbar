package u3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is used to read 
 * text from a file 
 * @author Christoffer strandberg
 */
public class FileReader {
	
	private InputStream inputStream;
	
	/**
	 * This method is used to read the text
	 * from a specific text file to then
	 * return the info to the class using it.
	 * @param URL - The location of the file
	 * @return - The text in the file
	 */
	public String readFile(String URL){
		 
		String returnText = "";
		try {
			inputStream = new FileInputStream(URL);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			
			while(reader.ready()){
				returnText += reader.readLine();
			}
			System.out.println("FileReader: File Read!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return returnText;
	}

}
