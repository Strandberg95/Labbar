package u5_LZ77;

public class Tuple {
	int offset;
	int stringLen;
	String nextChar;
	
	public Tuple(int offset, int stringLen, String nextChar){
		this.offset = offset;
		this.stringLen = stringLen;
		this.nextChar = nextChar;
	}
	
	public String toString(){
		return offset + "," + stringLen + "," + nextChar;
	}
}
