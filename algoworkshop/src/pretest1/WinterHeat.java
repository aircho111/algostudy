package pretest1;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WinterHeat {
	public static void main(String[] args) throws IOException {
		
//		System.out.println("Please Enter A Number (0 < N < 100)");
//		InputStreamReader r = new InputStreamReader(System.in);
//		BufferedReader br = new BufferedReader(r);
//		int N = Integer.parseInt(br.readLine());
//		
//		for(int i=0 ; i<N ; i++) {
//			System.out.println("HelloWorld!");
//		}
		
		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/gitrepo/algoworkshop/src/pretest1/input.txt"));
		int T = Integer.parseInt(br.readLine());
		
		System.out.println("Test Case : " + T);
		
		String[] NM;
		String[] Ws;
		String[] CPs;
		
		int[] Wi = new int[100];
		int[][] CPi = new int[100][2];
		int N = 0;
		int M = 0;
		
		//TestCase 만큼 반복
		for(int i=0 ; i<T ; i++) {
			
			NM = (br.readLine()).split(" ");
			
			//가구수
			N = Integer.parseInt(NM[0]);
			
			//간선수
			M = Integer.parseInt(NM[1]);
			
			
			Ws = (br.readLine()).split(" ");
			
			// 온도
			for(int j=0 ; j<N ; j++){
				Wi[j] = Integer.parseInt(Ws[j]);
			}
			
			// 간선정보
			for(int j=0 ; j<M ; j++){
				CPs = (br.readLine()).split(" ");
				CPi[j][0] = Integer.parseInt(CPs[0]);
				CPi[j][1] = Integer.parseInt(CPs[1]);
			}
			
			
			
			
		}
		
		
	}
}
