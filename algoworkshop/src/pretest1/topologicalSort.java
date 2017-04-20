package pretest1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalSort {
	
	public final static int MAX_N = 100000;
	public final static int MAX_M = 100000;
	
	public static void main(String args[]) throws IOException {
		
		
//		System.out.println("Please Enter A Number (0 < N < 100)");
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
//		int N = Integer.parseInt(br.readLine());
//		
//		for(int i=0 ; i<N ; i++) {
//			System.out.println("HelloWorld!");
//		}
		
		
		
//		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/algostudy/src/WinterHeat/input.txt"));
		int T = Integer.parseInt(br.readLine());
		
		//System.out.println("Test Case : " + T);
		
		String[] NM;
		String[] Ws;
		String[] CPs;
		
		int[] Wi = new int[MAX_N];        // 가구별 필요온도
		int[] indegree = new int[MAX_N];  // 진입차수
		int[][] CPi = new int[MAX_M][2];  // 간선정보
		int N = 0;                      // 가구수
		int M = 0;                      // 간선수
		long totalTime = 0;
		int nodeTime = 0;
		int connectedNode = 0;
		int connectedNode2 = 0;
		
		//TestCase 만큼 반복
		for(int i=0 ; i<T ; i++) {
			
//			System.out.println("Test Case : " + (i+1));
			
			totalTime = 0;
			
			NM = (br.readLine()).split(" ");
			
			//가구수
			N = Integer.parseInt(NM[0]);
//			System.out.println(" Node : " + N);
			
//			for(int j=0 ; j<MAX_N ;j++) {
//				indegree[j] = 0;
//			}
			
			//간선수
			M = Integer.parseInt(NM[1]);
//			System.out.println(" Line : " + M);
			
			Ws = (br.readLine()).split(" ");
			
			// 온도
			for(int j=0 ; j<N ; j++){
				Wi[j] = Integer.parseInt(Ws[j]);
			}
			
			// 간선정보
			for(int j=0 ; j<M ; j++){
				CPs = (br.readLine()).split(" ");
				CPi[j][0] = Integer.parseInt(CPs[0])-1;
				CPi[j][1] = Integer.parseInt(CPs[1])-1;
				
				indegree[CPi[j][0]] = indegree[CPi[j][0]] + 1;
				indegree[CPi[j][1]] = indegree[CPi[j][1]] + 1;
			}
			
			// 1.인접가구가 1인 노드를 큐에 삽입
			Queue<Integer> searchQ = new LinkedList<>();
			
			for(int j=0 ; j<N ; j++){
				
//				System.out.println(" Node" + (j+1) + " indegree : " + indegree[j]);
				if(indegree[j] == 1) {
					searchQ.offer(j);
//					System.out.println(" Node" + (j+1) + " indegree 1 --> Queue" );
				}
			}
			
			// 2.큐에서 가구를 꺼내서 온도처리하고 연결된 가구도 처리 후 연결을 끊고 진입차수를 줄인다. 진입차수가 0인 경우 큐에 삽입
			int leaf = 0;
			
//			System.out.println(" == Queue 작업 ==" );
			while(!searchQ.isEmpty()) {
				leaf = searchQ.poll();
				
				nodeTime = Wi[leaf];
				
//				System.out.println(" Queue Node" + (leaf+1) + " nodeTime "  + nodeTime);
				
				if(nodeTime > 0) {
					totalTime = totalTime + nodeTime;
					Wi[leaf] = 0;
				} else {
					nodeTime = 0;
				}
				
				
				
				// 연결된 부모Node를 찾기
				for(int j=0 ; j<M ; j++) {
					
					
					if(CPi[j][0] == leaf || CPi[j][1] == leaf) {
						
						if(CPi[j][0] == leaf) {
							connectedNode = CPi[j][1];
						} else {
							connectedNode = CPi[j][0];
						}
						
						if(connectedNode < 0) continue;
						
//						System.out.println("  " + (leaf+1) + " Parent Node : "  + (connectedNode+1));
						
						// 부모 온도 차감
						Wi[connectedNode] = Wi[connectedNode] - nodeTime;
						
						// 연결끊기
						if(CPi[j][0] == leaf) {
							CPi[j][0] = -1;
						} else {
							CPi[j][1] = -1;
						}
						
						// 연결된 부모Node의 진입차수 차감
						indegree[connectedNode] = indegree[connectedNode] - 1;
						
						// 연결된 가구의 진입차수가 1인 경우 큐에 삽입
						if(indegree[connectedNode] == 1) {
							searchQ.offer(connectedNode);
//							System.out.println("  Connected Node" + (connectedNode+1) + " indegree 1 --> Queue" );
						}
						
						break;
					}
					
				}
				
				
				// 부모Node와 연결된 Node 온도 차감
				for(int j=0 ; j<M ; j++) {
					
					
					if(CPi[j][0] == connectedNode || CPi[j][1] == connectedNode) {
						
						if(CPi[j][0] == connectedNode) {
							connectedNode2 = CPi[j][1];
						} else {
							connectedNode2 = CPi[j][0];
						}
						
						// 연결된 가구의 필요온도 차감
						if(connectedNode2 >= 0) {
//							System.out.println("  " + (connectedNode+1) + " Connect Node2 : "  + (connectedNode2+1));
							Wi[connectedNode2] = Wi[connectedNode2] - nodeTime;
						}
						
						
					}
				}

			}
			
			
			System.out.println("#" + (i+1) + " " + totalTime);
			
			
		}
	}
}
