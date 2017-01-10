package u5_LZ77;

import java.util.ArrayList;
import java.util.Iterator;

public class LZ77v01 {
	
	private String rawData = "How much wood would a woodchuck chuck" +
	"If a woodchuck could chuck wood?"
	+ "He would chuck, he would, as much as he could,"
	+ "And chuck as much as a woodchuck would"
	+ "If a woodchuck could chuck wood.";
	
	//A collection of Tuple objects
	private ArrayList<Tuple> encodedData = new ArrayList<Tuple>();
	
	//A reference to a single Tuple object
	private Tuple thisTuple;
	
	//A substring that represents the search window
	private String searchSubstring;
	
	//Working variables
	private int matchLen;
	private int matchLoc;
	private int charCnt;
	private int searchWindowStart;
	private int lookAheadWindowEnd;
	
	/*Modify the following values to change the length of
	 * the search window and/or the lookAhead window.
	 * Optimum values are one less than even powers of 2 
	 */
	private int searchWindowLen = 31;
	private int lookAheadWindowLen = 7;
	
	
	public LZ77v01(){
		
	}
	
	public static void main(String[] args){
		new LZ77v01().doIt();
	}
	
	public void doIt(){
	    System.out.println("123456789012345678901234567890"
                + "12345678901234567890123456789");
	    
	    display59(rawData);
	    System.out.println();
	    
	    charCnt = 0;
	    while(charCnt < rawData.length()){
	    	searchWindowStart = (charCnt-searchWindowLen >=0)
	    						? charCnt-searchWindowLen
	    								:0;
	    	
	    	lookAheadWindowEnd = (charCnt+lookAheadWindowLen < rawData.length())
	    										? charCnt+lookAheadWindowLen
	    												:rawData.length();
	    	
	    	//Display the contents of the search window.
	        System.out.print(rawData.substring(
	                         searchWindowStart,charCnt) + " - ");
	        //Display the contents of the lookAheadWindow on the
	        // same line as the search window, separated by a
	        // dash.
	        System.out.print(rawData.substring(
	                        charCnt,lookAheadWindowEnd) + " - ");
	        
	        if(charCnt == 0){
	        	//Begin with an empty search window
	        	searchSubstring = "";
	        	
	        }else{
	        	searchSubstring = rawData.substring(searchWindowStart,charCnt);
	        }
	        
	        matchLen = 1;
	        String searchTarget = rawData.substring(charCnt,charCnt + matchLen);
	        if(searchSubstring.indexOf(searchTarget)!=-1){
	        	matchLen++;
	        	while(matchLen <= lookAheadWindowLen){
	        		searchTarget = rawData.substring(charCnt,charCnt+matchLen);
	        		matchLoc = searchSubstring.indexOf(searchTarget);
	        		
	        		if((matchLoc != -1) && ((charCnt + matchLen)
	        			< rawData.length())){
	        			matchLen++;
	        			
	        		}else{
	        			break;
	        		}
	        	}
	        	matchLen--;
	        	
	        	matchLoc = searchSubstring.indexOf(rawData.substring(charCnt,charCnt + matchLen));
	        	charCnt += matchLen;
	        	
	        	//caluclate offset
	        	int offset = (charCnt < (searchWindowLen + matchLen))
	        						  ? charCnt - matchLoc - matchLen
	        						  : searchWindowLen - matchLoc;
	        	
	        	//Get and save the next non-matching character in 
	        	//the lookAhead window
	        	String nextChar = rawData.substring(charCnt,charCnt+1);
	        	
	        	//Instatiate and populate the Tuple object.
	        	thisTuple = new Tuple(offset,matchLen,nextChar);
	        	encodedData.add(thisTuple);
	        }else{
	        	//A match was not found for the next character.
	        	//Encapsulate the following information in a Tuple
	        	//1. 0
	        	//2. 0
	        	//3. The non-matching character
	        	String nextChar = rawData.substring(charCnt,charCnt+1);
	        	thisTuple = new Tuple(0,0,nextChar);
	        	encodedData.add(thisTuple);
	        }
	        charCnt++;
	        System.out.println(thisTuple + " - ");
	        
	        if(thisTuple.stringLen > 0){
	        	//The Tuple contains a pointer to a character or
	        	// string in the search window. Expand and 
	        	// display it.
	        	int start = charCnt - 1 - thisTuple.stringLen - thisTuple.offset;
	        	
	        	int end = charCnt - 1 - thisTuple.offset;
	        	System.out.println(rawData.substring(start,end) + thisTuple.nextChar);
	        }else{
	        	//The tuple contains a character for which no
	        	// match was found. Display it.
	        	System.out.println(thisTuple.nextChar);
	        }
	    }
	    StringBuffer reconData = new StringBuffer();
	    Iterator <Tuple> iterator = encodedData.iterator();
	    
	    //Iterate on the collection
	    while(iterator.hasNext()){
	    	Tuple nextTuple = iterator.next();
	    	if(nextTuple.stringLen == 0){
	    		//There was no match for this character
	    		//Just append it to the StringBuffer.
	    		reconData.append(nextTuple.nextChar);
	    	}else{
	    		for(int cnt = 0; cnt < nextTuple.stringLen; cnt++){
	    			char workingChar = reconData.charAt(reconData.length() - nextTuple.offset);
	    			reconData.append(workingChar);
	    		}
	    		reconData.append(nextTuple.nextChar);
	    	}
	    }
	    
	    //Display a scale on the screen.
	    System.out.println("n123456789012345678901234567890"
	                       + "12345678901234567890123456789");
	    
	    //Now display the reconstructed message 59 characters
	    // to the line.
	    display59(new String(reconData));
	    
	    //Analyze the compression
	    System.out.println("Analyze the compression.");
	    System.out.println("Assume:");
	    System.out.println(
	               " 8 bits required per raw data character.");
	    
	    //Calculate the number of bits required to contain the
	    // largest values that can occur in the values for
	    // offset and stringLen in the Tuple.  Assume that the
	    // character in the Tuple is contained in 8 bits.
	    int offsetBitsRequired = getBitsRequired(
	                                          searchWindowLen);
	    int stringLenBitsRequired = getBitsRequired(
	                                       lookAheadWindowLen);
	    int tupleBitsRequired = 8 + offsetBitsRequired 
	                                   + stringLenBitsRequired;
	    
	    System.out.println(" Maximum offset value of " 
	                      + searchWindowLen + " char requires "
	                      + offsetBitsRequired + " bits.");
	    System.out.println(" Maximum stringLen value of " 
	                   + lookAheadWindowLen + " char requires "
	                   + stringLenBitsRequired + " bits.");
	    System.out.println(
	                   " Character in Tuple requires 8 bits.");
	    
	    System.out.println(" " + tupleBitsRequired 
	                            + " bits required per Tuple.");
	    int msgLength = rawData.length()*8;
	    System.out.println("Raw data length = " + msgLength 
	                                            + " bits.");
	    System.out.println("Number Tuples: " 
	                                     + encodedData.size());
	    int tupleLength = encodedData.size()*tupleBitsRequired;
	    System.out.println("Total Tuple length = " 
	                                 + tupleLength + " bits.");
	    System.out.println("Compression factor = " 
	                          + (double)msgLength/tupleLength);
	}
	
	public void display59(String data){
		for(int cnt = 0; cnt < data.length(); cnt += 59){
			if((cnt + 59) < data.length()){
		        System.out.println(data.substring(cnt,cnt+59));
			}else{
		        System.out.println(data.substring(cnt));
			}
		}
	}
	
	  //Method to return the number of bits required to
	  // contain a given integer value.  (There must be a
	  // better way to do this, such as finding the log to the
	  // base 2.)
	  int getBitsRequired(int value){
	    if(value < 2){
	      return 1;
	    }else if(value < 4){
	      return 2;
	    }else if(value < 8){
	      return 3;
	    }else if(value < 16){
	      return 4;
	    }else if(value < 32){
	      return 5;
	    }else if(value < 64){
	      return 6;
	    }else if(value < 128){
	      return 7;
	    }else if(value < 256){
	      return 8;
	    }else{
	      System.out.println(
	         "Bit conversion too large, terminating program.");
	      System.exit(1);
	      return 0;
	    }
	  }
	
	
}
