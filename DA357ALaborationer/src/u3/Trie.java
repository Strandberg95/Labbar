package u3;

import java.util.HashMap;

public class Trie {
	private TrieNode root;

	
	public Trie(){
		root = new TrieNode();
	}
	
	public void add(String input){
		HashMap<Character,TrieNode> children = root.getChildren();

		for(int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
	
			if(i == input.length()-1){
				children = addSequence(children,c,true);
			}else{
				children = addSequence(children,c,false);				
			}
		}
	}
	
	private HashMap addSequence(HashMap<Character,TrieNode> children,
			char input, boolean isLeaf){
		TrieNode t;
		if(children.containsKey(input)){
			t = children.get(input);
		}else {
			t = new TrieNode(input);
			children.put(input, t);
		}
		t.setisLeaf(isLeaf);
		return t.getChildren();
	}
	
	public boolean search(String word){
		TrieNode t = searchNode(word);
		
		if(t != null && t.getIsLeaf()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean startsWith(String prefix){
		if(searchNode(prefix) == null){
			return false;
		}else
			return true;
	}
	
	
	private TrieNode searchNode(String str){
		HashMap<Character, TrieNode> children = root.getChildren();
		TrieNode t = null;
		
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(children.containsKey(c)){
				t = children.get(c);
				children = t.getChildren();
			}else{
				return null;
			}
		}
		return t;
	}
	public int longestMatch(String str){
//		int count = 0; 
		String search = "";
		int count = 0;
		for(int i = 0; i < str.length(); i++){
			search += str.charAt(i);
			System.out.println(search);
			if(!startsWith(search)){
				break;
			}else{
				count = (i + 1);				
			}
		}
		return count;
	}
}
