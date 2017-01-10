package u5_Huffman;

import java.util.Hashtable;

public class Huffman {
	
	public static void main(String[] args){
		Hashtable<Character,String>huffEncodeTable;
		
	    String rawData = "BAGHDAD, Iraq Violence increased "
	    	    + "across Iraq after a lull following the Dec. 15 "
	    	    + "parliamentary elections, with at least two dozen "
	    	    + "people including a U.S. soldier killed Monday in "
	    	    + "shootings and bombings mostly targeting the Shiite-"
	    	    + "dominated security services. The Defense Ministry "
	    	    + "director of operations, Brig. Gen. Abdul Aziz "
	    	    + "Mohammed-Jassim, blamed increased violence in the "
	    	    + "past two days on insurgents trying to deepen the "
	    	    + "political turmoil following the elections. The "
	    	    + "violence came as three Iraqi opposition groups "
	    	    + "threatened another wave of protests and civil "
	    	    + "disobedience if allegations of fraud are not "
	    	    + "properly investigated.";
	    		
	    		System.out.println("Raw Data");
	    		display(48);
	    		
	    		int rawDataLen = rawData.length();
	    		
	    		System.out.println("Number raw data bits: "
	    							+ rawData.length() * 8);
	    		
	    		HuffmanEncoder encoder = new HuffmanEncoder();
	    		
	    		huffEncodeTable = new Hashtable<Character,String>();
	    		
	    		ArrayList<Byte> binaryEncodedData = encoder.encode(rawData,huffEncodeTable);
	    		
	    		
	    
	}

}
