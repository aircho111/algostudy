package pretest1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
	public final static int MAX_N = 100000;
	public final static int MAX_M = 100000;
	
	static ArrayList<ArrayList<Integer>> edges;
	
	static String[] NM;
	static String[] Ws;
	static String[] CPs;

	static int[] Wi = new int[MAX_N];        // 가구별 필요온도
	static int[] indegree = new int[MAX_N];  // 진입차수
	static int[][] CPi = new int[MAX_M][2];  // 간선정보
	static int N = 0;                      // 가구수
	static int M = 0;                      // 간선수
	static long totalTime = 0;
	static int nodeTime = 0;
	static int connectedNode = 0;
	static int connectedNode2 = 0;

	public static void main(String[] args) throws Exception {

		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("C:/workplace/gitrepo/algoworkshop/src/pretest1/sample_input.txt"));

		int T = Integer.parseInt(br.readLine());

		//System.out.println("Test Case : " + T);

		Wi = new int[MAX_N];        // 가구별 필요온도
		indegree = new int[MAX_N];  // 진입차수
		CPi = new int[MAX_M][2];  // 간선정보
		N = 0;                      // 가구수
		M = 0;                      // 간선수
		totalTime = 0;
		nodeTime = 0;
		connectedNode = 0;
		connectedNode2 = 0;

		//TestCase 만큼 반복
		for(int i=0 ; i<T ; i++) {

			//			System.out.println("Test Case : " + (i+1));

			totalTime = 0;


			NM = (br.readLine()).split(" ");

			//가구수
			N = Integer.parseInt(NM[0]);
			//			System.out.println(" Node : " + N);
			
			edges = new ArrayList<ArrayList<Integer>>();
			for(int j = 0; j <= N; j++) {
				edges.add(new ArrayList<Integer>());
			}

			for(int j=0 ; j<MAX_N ;j++) {
				indegree[j] = 0;
			}

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
				
				edges.get(CPi[j][0]).add(CPi[j][1]);
				edges.get(CPi[j][1]).add(CPi[j][0]);
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

				if(edges.get(leaf).isEmpty()) {
					connectedNode = -1;
				} else {
					connectedNode = edges.get(leaf).get(0);	
				}
				
				if(connectedNode >= 0) {
					// 부모 온도 차감
					Wi[connectedNode] = Wi[connectedNode] - nodeTime;
					
					if(i == 0) {
//						System.out.println("leaf : " + leaf);
//						System.out.println("connectedNode : " + connectedNode);
//						System.out.println(edges.get(leaf).toString());
					}
					
					// 연결끊기
					edges.get(leaf).remove(Integer.valueOf(connectedNode));
					edges.get(connectedNode).remove(Integer.valueOf(leaf));
					
					if(i == 0) {
//						System.out.println(edges.get(leaf).toString());
					}
					
					// 연결된 부모Node의 진입차수 차감
					indegree[connectedNode] = indegree[connectedNode] - 1;

					// 연결된 가구의 진입차수가 1인 경우 큐에 삽입
					if(indegree[connectedNode] == 1) {
						searchQ.offer(connectedNode);
						//							System.out.println("  Connected Node" + (connectedNode+1) + " indegree 1 --> Queue" );
					}
				}

//				// 연결된 부모Node를 찾기
//				for(int j=0 ; j<M ; j++) {
//					if(CPi[j][0] == leaf || CPi[j][1] == leaf) {
//						if(CPi[j][0] == leaf) {
//							connectedNode = CPi[j][1];
//						} else {
//							connectedNode = CPi[j][0];
//						}
//
//						if(connectedNode < 0) continue;
//						//						System.out.println("  " + (leaf+1) + " Parent Node : "  + (connectedNode+1));
//						// 부모 온도 차감
//						Wi[connectedNode] = Wi[connectedNode] - nodeTime;
//
//						// 연결끊기
//						if(CPi[j][0] == leaf) {
//							CPi[j][0] = -1;
//						} else {
//							CPi[j][1] = -1;
//						}
//
//						// 연결된 부모Node의 진입차수 차감
//						indegree[connectedNode] = indegree[connectedNode] - 1;
//
//						// 연결된 가구의 진입차수가 1인 경우 큐에 삽입
//						if(indegree[connectedNode] == 1) {
//							searchQ.offer(connectedNode);
//							//							System.out.println("  Connected Node" + (connectedNode+1) + " indegree 1 --> Queue" );
//						}
//						break;
//					}
//				}
//

				if(connectedNode >= 0) {
					for(int j = 0; j < edges.get(connectedNode).size(); j++) {
						connectedNode2 = edges.get(connectedNode).get(j);
						
						// 연결된 가구의 필요온도 차감
						if(connectedNode2 >= 0) {
							//							System.out.println("  " + (connectedNode+1) + " Connect Node2 : "  + (connectedNode2+1));
							Wi[connectedNode2] = Wi[connectedNode2] - nodeTime;
						}
					}
				}
				
//				// 부모Node와 연결된 Node 온도 차감
//				for(int j=0 ; j<M ; j++) {
//
//
//					if(CPi[j][0] == connectedNode || CPi[j][1] == connectedNode) {
//
//						if(CPi[j][0] == connectedNode) {
//							connectedNode2 = CPi[j][1];
//						} else {
//							connectedNode2 = CPi[j][0];
//						}
//
//						// 연결된 가구의 필요온도 차감
//						if(connectedNode2 >= 0) {
//							//							System.out.println("  " + (connectedNode+1) + " Connect Node2 : "  + (connectedNode2+1));
//							Wi[connectedNode2] = Wi[connectedNode2] - nodeTime;
//						}
//
//
//					}
//				}

			}


			System.out.println("#" + (i+1) + " " + totalTime);


		}
		
		br.close();
		
	}
}
