package pretest2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/gitrepo/algoworkshop/src/pretest2/sample_input.txt"));
		
		// 1. 테스트케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0 ; t<T ; t++) {
			
			// 2. 붕어빵기계수 : N, 발전기수 : M, 기간 : K
			//    1 <= N,M,K <= 100,000
			String[] rl = (br.readLine()).split(" ");
			int N = Integer.parseInt(rl[0]);  // 붕어빵기계수
			int M = Integer.parseInt(rl[1]);  // 발전기수
			int K = Integer.parseInt(rl[2]);  // 기간
			
			// 3. 기간(K) 동안 일자별 전력공급 발전기 번호
			String[] dayPlan = (br.readLine()).split(" ");
			
			// 4. 붕어빵 기계에 연결된 발전기(3개) 및 필요한 에너지량(P)
			String[][] generators = new String[N][4];
			String[] tmp = new String[4];
			for(int i=0 ; i<N ; i++) {
				tmp = (br.readLine()).split(" ");
				generators[i][0] = tmp[0];
				generators[i][1] = tmp[1];
				generators[i][2] = tmp[2];
				generators[i][3] = tmp[3];
			}
			
			String g = "";
			int[][] e = new int[N][K];
			for(int k=0 ; k<K ; k++) {
				g = dayPlan[k];
				for(int j=0 ; j<N ; j++) {
					if( g.equals(generators[j][0]) ||
					    g.equals(generators[j][1]) ||
                        g.equals(generators[j][2]) 
                      ) {
						e[j][k]++;
					}
				}
			}
			
		}
		
		
	}
}