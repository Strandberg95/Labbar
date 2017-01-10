package u3;

public class Tester {
	public Tester(){
		Trie trie = new Trie();
		
		trie.add("ay lmao");
		trie.add("apan kan suga min röv");
				
		System.out.println(trie.longestMatch("ay lmao"));
		
	}
	
	public static void main(String[] args){
		new Tester();
	}
}
