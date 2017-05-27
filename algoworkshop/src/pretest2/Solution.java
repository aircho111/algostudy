package pretest2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Solution {
	private static final int DIV = 100000123;
	private static String[] dayPlan;
	private static ArrayList<Integer> al_gm[];
	private static int[] me;
	
//	private static int[][] mg;
	private static int[] me_for_on;
	private static int on_cnt;
	private static long score_sum;
	private static int[] day_on_cnt;
	
	public static void main(String[] args) throws Exception {
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/gitrepo/algoworkshop/src/pretest2/sample_input.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/algostudy/src/pretest2/sample_input2.txt"));
		// 1. 테스트케이스 수
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t=0 ; t<T ; t++) {
			
			// 2. 붕어빵기계수 : N, 발전기수 : M, 기간 : K
			//    1 <= N,M,K <= 100,000
			String[] rl = (br.readLine().trim()).split(" ");
			int N = Integer.parseInt(rl[0]);  // 붕어빵기계수
			int M = Integer.parseInt(rl[1]);  // 발전기수
			int K = Integer.parseInt(rl[2]);  // 기간
			
			on_cnt = 0;
			score_sum = 0;
			//mg = new int[N][4];          // 기계별 연결된 발전기 및 필요 에너지
			
			al_gm = new ArrayList[M];
			
//			for(int i=0 ; i<M ; i++) {
//				al_gm[i] = new ArrayList<Integer>();
//			}

			day_on_cnt = new int[K];   // 일자별 작동되는 기계수
			me = new int[N];             // 기계별 공급된 에너지
			me_for_on = new int[N];   // 기계별 작동에 필요한 에너지
			
			for(int i=0 ; i<N ; i++) {
				me[i] = 0;
			}
			
			// 3. 기간(K) 동안 일자별 전력공급 발전기 번호
			dayPlan = (br.readLine().trim()).split(" ");
			
			// 4. 붕어빵 기계에 연결된 발전기(3개) 및 필요한 에너지량(P)
			//    발전기에 연결된 기계도 저장
			String[] tmp = new String[4];
			for(int i=0 ; i<N ; i++) {
				tmp = (br.readLine().trim()).split(" ");
//				mg[i][0] = Integer.parseInt(tmp[0]);  //연결된 발전기 #1
//				mg[i][1] = Integer.parseInt(tmp[1]);  //연결된 발전기 #2
//				mg[i][2] = Integer.parseInt(tmp[2]);  //연결된 발전기 #3
//				mg[i][3] = Integer.parseInt(tmp[3]);  //필요한 에너지량
				
				me_for_on[i] = Integer.parseInt(tmp[3]);  //필요한 에너지량
				
				if(al_gm[Integer.parseInt(tmp[0])-1] == null) {
					al_gm[Integer.parseInt(tmp[0])-1] = new ArrayList<Integer>();
				}
				al_gm[Integer.parseInt(tmp[0])-1].add(i+1);    // 발전기에 연결된 기계
				
				if(al_gm[Integer.parseInt(tmp[1])-1] == null) {
					al_gm[Integer.parseInt(tmp[1])-1] = new ArrayList<Integer>();
				}
				al_gm[Integer.parseInt(tmp[1])-1].add(i+1);    // 발전기에 연결된 기계
				
				if(al_gm[Integer.parseInt(tmp[2])-1] == null) {
					al_gm[Integer.parseInt(tmp[2])-1] = new ArrayList<Integer>();
				}
				al_gm[Integer.parseInt(tmp[2])-1].add(i+1);    // 발전기에 연결된 기계
				
			}
			
			long daily_score = 0;
			long total_score = 0;
			int g = 0;
			int daily_on_cnt = 0;
			int total_on_cnt = 0;
			
			for(int i=0 ; i<K ; i++) {
				// 해당일에 공급되는 발전기
				g = Integer.parseInt(dayPlan[i]);
//				System.out.println("*DAY" + (i+1) + " generator : " + g);
				
				daily_on_cnt = 0;
				
				// 해당 발전기에 연결된 붕어빵 기계
				ArrayList<Integer> almc = al_gm[g-1];
				
				// 해당 발전기에 연결된 붕어빵기계 에너지 증가하고 작동여부 체크
				if(almc != null) { 
					for(int j=0 ; j < almc.size() ; j++) {
						me[almc.get(j)-1]++;
						if(me[almc.get(j)-1] == me_for_on[almc.get(j)-1]) {
							// 작동 기계수 증가
							daily_on_cnt++;	
						}
					}
				}
				
				System.out.println("*DAY" +  (i+1) + " turn-on machine cnt : " + daily_on_cnt);
				
				total_on_cnt = total_on_cnt + daily_on_cnt;
				
//				System.out.println("*DAY" +  (i+1) + " total operating machine cnt : " + total_on_cnt);
				
				daily_score = total_on_cnt * (i+1);
				
				total_score = total_score + daily_score;
			}
			
			System.out.println("#" + (t+1) + " " + total_score%DIV) ;
			
		} /* 테스트케이스 */
		
		
	}
	
//	private static long partialsum(int day) {
//		long partsum = 0;
//		long dayscore = 0;
//		int dayon_cnt = 0;
//		int total_on_cnt = 0;
//		
//		if(day < 1) return partsum;
//		
//		// 해당일에 공급되는 발전기
//		int g = Integer.parseInt(dayPlan[day-1]);
//		
//		System.out.println("*DAY" + day + " generator : " + g);
//		
//		// 해당 발전기에 연결된 붕어빵 기계
//		ArrayList<Integer> almc = al_gm[g-1];
//		
//		// 해당 발전기에 연결된 붕어빵기계 에너지 증가하고 작동여부 체크
//		for(int i=0 ; i < almc.size() ; i++) {
//			me[almc.get(i)-1]++;
//			if(me[almc.get(i)-1] == mg[almc.get(i)-1][3]) {
//				// 작동 기계수 증가
//				dayon_cnt++;
//				
//			}
//		}
//		
//		// 해당일 작동된 기계수 저장
//		day_on_cnt[day-1] = dayon_cnt;
//		
//		System.out.println("*DAY" + day + " turn-on machine cnt : " + dayon_cnt);
//		
//		// 그날까지 작동된 기계수 계산
//		for(int i=0 ; i<day ; i++){
//			total_on_cnt = total_on_cnt + day_on_cnt[i];
//		}
//		
//		dayscore = total_on_cnt * day;
//		partsum = dayscore + partialsum(day-1);
//		
//		
//		
//		return partsum;
//	}
	
}
