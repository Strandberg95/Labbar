package u1_TextSökning;

public class TextSearch {
	
	public TextSearch(String text, String search){
//		for(int i = 0; i < text.length(); i++){
//			
//		}
		System.out.println("textlength: " + text.length());
		System.out.println("searchlength: " + search.length());
		
		System.out.println(searchForSequence(text,search,0,0,0));
		
	}
	
	public boolean searchForSequence(String text, String search, int textIndex,int searchIndex, int count){
		try{
			if(search.charAt(search.length() - 1) ==  search.charAt(searchIndex) && searchIndex == (search.length()-1) 
					&& text.charAt(textIndex) == search.charAt(searchIndex)){
				System.out.println(count += 1);
				return true;
			}
			if(text.charAt(textIndex) == search.charAt(searchIndex)){
				return searchForSequence(text,search,(textIndex += 1),(searchIndex += 1),count += 1);
			}else{
				if(searchIndex > 0){
					return searchForSequence(text,search,textIndex,0,count += 1);
				}else{
					return searchForSequence(text,search,textIndex += 1,0,count += 1);
				}
			}
			
		}catch(StringIndexOutOfBoundsException e){
			return false;
		}


	}
	
	public static void main(String[] args){
		new TextSearch("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCD","ABCD");
	}
}
