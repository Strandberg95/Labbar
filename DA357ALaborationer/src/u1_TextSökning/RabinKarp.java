package u1_TextSökning;

public class RabinKarp {
	
	private int d = 256;

	public RabinKarp(char pat[],char[] txt, int q){
		search(pat,txt,q);
	}
	
	public void search(char pat[], char txt[], int q){
		int m = pat.length;
		int n = txt.length;
		
		int i, j;
		int p = 0; // Hash value for pattern
		int t = 0; // hash value for txt
		int h = 1;
		
		//the value of h would be "pow(d, M-1)%q"
		for(i = 0; i < m-1; i++){
			h = (h*d)%q;
		}
		
		//Calculate the hash value of pattern and first
		//window of text
		for (i = 0; i < m; i++)
		{
			p = (d*p + pat[i])%q;
			t = (d*t + txt[i])%q;
		}
		
		//Slide the pattern over text one by one
		for(i = 0; i <= n - m; i++){
			//Check the hash values of current window of text
			//and pattern. If the hash values match then only
			//check for characters one by one
			if(p == t){
				for(j = 0; j < m; j++){
					if(txt[i+j] != pat[j])
						break;
				}
				if(j == m)
					System.out.println("Pattern found at index " + i);
			}
			
			//Calculate hash value for next window of text:Remove
			//leading digit, add trailing digit
			if(i < n-m){
				t = (d*(t - txt[i]*h) + txt[i+m])%q;
				
				//We might get negative value of t, coverting it
				//to positive
				if(t < 0){
					t = (t + q);					
				}
			}
		}
	}
	
	public static void main(String[] args){
		String pattern = "dinken";
		String text = "De kallar dinken dinken";
		new RabinKarp(pattern.toCharArray(),text.toCharArray(),101);
	}
	
}
