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
		int[] Wi = new int[100];
		int N = 0;
		int M = 0;
		
		//TestCase 만큼 반복
		for(int i=0 ; i<T ; i++) {
			
			NM = (br.readLine()).split(" ");
			N = Integer.parseInt(NM[0]);
			M = Integer.parseInt(NM[1]);
			
			
			Ws = (br.readLine()).split(" ");
			for(int j=0 ; j<N ; j++){
				
			}
			
		}
		
		
	}
}