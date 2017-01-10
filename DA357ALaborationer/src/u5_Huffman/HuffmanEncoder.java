package u5_Huffman;

import java.util.ArrayList;
import java.util.Hashtable;

public class HuffmanEncoder {
	String rawData;
	TreeSet<HuffTree>theTree = new TreeSet<HuffTree>();
	ArrayList <Byte> binaryEncodedData = new ArrayList<Byte>();
	Hashtable <Character,Integer> frequencyData = new Hashtable<Character,Integer>();
	StringBuffer code = new StringBuffer();
	Hashtable <Character,String> huffEncodeTable;
	String stringEncodedData;
	Hashtable <String,Byte>encodingBitMap = new Hashtable<String,Byte>();
	
	ArrayList<Byte> encode(String rawData, Hashtable<Character,String>huffEncodeTable){
		this.rawData = rawData;
		this.huffEncodeTable = huffEncodeTable;
		
		/*
	    System.out.println("nRaw Data as Bits");
	    displayRawDataAsBits();
		*/
		createFreqData();
	}
	
	public void createFreqData(){
		for(int cnt = 0; cnt < rawData.length(); cnt++){
			char key = rawData.charAt(cnt);
			if(frequencyData.containsKey(key)){
				int value = frequencyData.get(key);
				value += 1;
				frequencyData.put(key, value);
			}else{
				frequencyData.put(key, 1);
			}
		}
	}

}
