package pretest2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Solution2 {
	private static final int DIV = 100000123;
	private static String[] dayPlan;
	private static ArrayList<Integer> al_gd[];
	private static int[] me;
	
	private static int[][] mg;
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
			

			
			// 3. 기간(K) 동안 일자별 전력공급 발전기 번호
			dayPlan = (br.readLine().trim()).split(" ");
			
			al_gd = new ArrayList[M];      // 발전기별 에너지 공급일
			day_on_cnt = new int[K];       // 일별 On 되는 기계수
			int g = 0;
			
			for(int i=0 ; i<K ; i++) {
				
				g = Integer.parseInt(dayPlan[i]); // 일자별 발전기
				
				if(al_gd[g-1] == null) {
					al_gd[g-1] = new ArrayList<Integer>();
				}
				al_gd[g-1].add(i+1);    // 발전기에 에너지가 공급되는 일자 저장
				
				day_on_cnt[i] = 0;
				
			}
			
			// 4. 붕어빵 기계에 연결된 발전기(3개) 및 필요한 에너지량(P)	
			mg = new int[N][3];
			me_for_on = new int[N];       // 기계별 작동에 필요한 에너지량
			String[] tmp = new String[4];
			int turn_on_day;
			for(int i=0 ; i<N ; i++) {
				tmp = (br.readLine().trim()).split(" ");
				mg[i][0] = Integer.parseInt(tmp[0]);  //연결된 발전기 #1
				mg[i][1] = Integer.parseInt(tmp[1]);  //연결된 발전기 #2
				mg[i][2] = Integer.parseInt(tmp[2]);  //연결된 발전기 #3
				
				me_for_on[i] = Integer.parseInt(tmp[3]);  //필요한 에너지량
				
				turn_on_day = searchFirstOpDay(i);
				
//				System.out.println("*Machine" +  (i+1) + " turn-on day : " + turn_on_day);
				
				if(turn_on_day > 0) {
					day_on_cnt[turn_on_day-1]++; //해당 기계가 최초작동하는 일자를 구하여 기계수를 증가시킴
				}
			}
			
			long daily_score = 0;
			long total_score = 0;
			
			int daily_on_cnt = 0;
			int total_on_cnt = 0;
			
			for(int i=0 ; i<K ; i++) {
				daily_on_cnt = day_on_cnt[i];
				
				total_on_cnt = total_on_cnt + daily_on_cnt;
				
				System.out.println("*DAY" +  (i+1) + " turn-on machine cnt : " + daily_on_cnt);
				
//				System.out.println("*DAY" +  (i+1) + " total operating machine cnt : " + total_on_cnt);
				
				daily_score = total_on_cnt * (i+1);
				
				total_score = total_score + daily_score;
			}

			
			System.out.println("#" + (t+1) + " " + total_score%DIV) ;
			
		} /* 테스트케이스 */
		
		
	}
	
	private static int searchFirstOpDay(int n) {

        int first = 1;
        int last = dayPlan.length;

		int mid;
		
		int need_energy = me_for_on[n];
		
		
		int g;
		ArrayList<Integer> gd ;
		int mid_energy=0;
		int max_day = 0;
		
		while(first <= last) {
			mid = (first+last)/2;
			
			// mid일자까지 기계에 제공되는 에너지
			mid_energy = 0;
			
			for(int i=0 ; i<3 ; i++) {
				g = mg[n][i];
				gd = al_gd[g-1];
				
				if(gd != null) {
					
					//--> 여기도 이진탐색으로
					for(int j=0 ; j<gd.size() ; j++) {
						
						if(gd.get(j) <= mid) {
							mid_energy++;
							
							if(max_day < gd.get(j)) max_day = gd.get(j);
						}
						
					}
				}
			}
			
			if(need_energy == mid_energy) {
				
				if(mid==19)
					System.out.println("*DAY" +  mid + " turn-on machine : " + (n+1) + " max_day : " + max_day);
				
				return mid;
				
			} else {
				
				if(need_energy < mid_energy) {
					last = mid-1;
				} else {
					first = mid+1;
				}
			}
			
			
		}
		return 0;
	}
	
}
