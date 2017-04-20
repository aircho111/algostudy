/*
 * #1 17
 * #2 32
 * #3 14
 * #4 38
 * #5 37
 */

package pretest1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionDFS {
	
	static ArrayList<ArrayList<Integer>> edges;
	static int[] W ;
	static int[] indegree;
	static int[][] CPi;
	static boolean[] visit;
	
	public static void dfs(int i){
        visit[i] = true;
        System.out.print(i);
        
        for(int j : edges.get(i)){  // 배열 null check
            if(visit[j] == false){
                dfs(j);
            }
        }
    }

	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("C:/workplace/gitrepo/algoworkshop/src/pretest1/input.txt");
		Scanner sc = new Scanner(file);
//		Scanner sc = new Scanner(System.in);
		
		int T = 0;
		int N = 0;
		int M = 0;
		
		int a = 0;
		int b = 0;

		
		T = sc.nextInt();
			
		for( int i=0 ; i<T ; i++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			W = new int[N];
			indegree = new int[N];
			
			visit = new boolean[N+1];
			edges = new ArrayList<ArrayList<Integer>>();
			
			edges.add(new ArrayList<Integer>());
			for( int j=0 ; j<N ; j++) {
				W[j] = sc.nextInt();
				edges.add(new ArrayList<Integer>());
			}
			
			for( int j=0 ; j<M ; j++) {
				a = sc.nextInt();
				b = sc.nextInt();
				
				indegree[a-1]++;
				indegree[b-1]++;
				edges.get(a).add(b);
				edges.get(b).add(a);
			}
			
			System.out.println("Test Case : " + i);
			dfs(1);
			System.out.println("");
			
		}
		
		sc.close();
		
	}
}