package u1_TextSökning;

public class KMP {
	
	public KMP(){
		String string = "Ay lmao";
		String find = "lmao";
		kmp_matcher(string.toCharArray(),find.toCharArray());
	}
	
	private static int kmp_Search(String S, String W){
		int m = 0;
		int i = 0;
		int[] T = new int[S.length()];
		
		while((m + i) < S.length()){
			if(W.charAt(i) == S.charAt(m+i)){
				if(i == W.length() - 1)
					return m;
				i = i+1;
			}else{
				if(T[i] > -1){
					m = m + i - T[i];
					i = T[i];
				}else{
					m = m + 1;
					i = 0;
				}
			}
		}
		return S.length();
	}
	
	private void kmp_Table(char[] W, int[] T){
		int pos = 2;
		int cnd = 0;
		
		T[0] = -1;
		T[1] = 0;
		
		while(pos < W.length){
			if(W[pos-1] == W[cnd]){
				T[pos] = cnd + 1;
				cnd = cnd + 1;
				pos = pos + 1;
			}else if(cnd > 0){
				cnd = T[cnd];
				T[pos] = 0;
			}else{
				T[pos] = 0;
				pos = pos + 1;
			}
		}
	}
	
	
	private int[] prefix(char[] P){
		
		int m = P.length;
		int[] pi = new int[P.length];
		pi[1] = 0;
		int i = 0;
		
		for(int j = 2; j < m; j++){
			while(i > 0 && P[i + 1] != P[j]){
				i = pi[i];
			}
			if(P[i + 1] == P[j])
				i = i+1;
			
			pi[j] = i;
		}
		return pi;
		
		
//		int m = p.length();
//		int[] prefix = new int[p.length()];
//		prefix[1] = 0;
//		int k = 0;
//		for(int q = 2; q < m; q++){
//			while(k > 0 && k + 1 != q){
//				k = prefix[k];
//			}
//			if(k+1 == q)
//				k = (k+1);
//			prefix[q] = k;
//		}
//		return prefix;
	}
	
	private void kmp_matcher(char[] T, char[] P){
		int n = T.length;
		int m = P.length;
		int[] pi = prefix(P);
		int i = 0;
		for(int j = 1; j < n; j++){
			while(i > 0 && P[i+1] != T[j]){
				i = pi[i];
			}
			if(P[i+1] == T[j]){
				i = i+1;
			}
			if(i == m){
				System.out.println((j - m));
				i = pi[i];
			}
		}
		
	}
	
	
	public static void main(String[] args){
		new KMP();
	}

}
