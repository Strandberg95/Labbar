package u3;

import java.util.HashMap;

public class TrieNode {
	private char data;
	private HashMap<Character, TrieNode> children = new HashMap<Character,TrieNode>();
	private boolean isLeaf;
	
	public TrieNode(char data){
		this.data = data;
	}
	
	public TrieNode(){
		
	}
	
	public HashMap getChildren(){
		return children;
	}

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public void setisLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
