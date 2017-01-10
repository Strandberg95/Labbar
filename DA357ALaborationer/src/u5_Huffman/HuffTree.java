package u5_Huffman;

public abstract class HuffTree implements Comparable {
	
	int frequency;
	
	public int getFrequency(){
		return frequency;
	}
	
	public int compareTo(Object obj){
		HuffTree theTree = (HuffTree)obj;
		
		if(frequency == theTree.frequency){
			return (hashCode() - theTree.hashCode());
		}else{
			return frequency - theTree.frequency;
		}
		
	}

}
