import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_1948 {

	static int n;  //도시의 수
	static int m ; //도로의 수
	static int[] inCost;
	static int[] inDegree;
	static ArrayList<Edge>[] list1,list2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		inCost = new int[n+1];
		inDegree = new int[n+1];
		list1 = new ArrayList[n+1];
		list2 = new ArrayList[n+1];
		for(int i = 1; i < n+1; i++) {
			list1[i] = new ArrayList<Edge>();
			list2[i] = new ArrayList<Edge>();
		}
		int a, b, c;
		for(int i =0; i < m; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			
			list1[a].add(new Edge(a, b, c));
			inDegree[b]++;
			list2[b].add(new Edge(b, a, c));
		}
		int start = sc.nextInt();
		int end = sc.nextInt();
		sc.close();
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()){
			int cur = q.poll();
			for(int i =0; i < list1[cur].size(); i++) {
				int tempTo = list1[cur].get(i).to;
				inDegree[tempTo]--;
//				비용을 구해서 최대값 저장해 놓기
				inCost[tempTo] = Math.max(inCost[tempTo], inCost[cur] + list1[cur].get(i).cost);
				if(inDegree[tempTo] == 0) {
					q.add(tempTo);
				}
			}
		}
		q.clear();
		int cnt = 0;
		q.add(end);
		while(!q.isEmpty()){
			int cur = q.poll();
			for(int i =0; i < list2[cur].size(); i++) {
				int tempTo = list2[cur].get(i).to;
				if(list2[cur].get(i).cost == inCost[cur] - inCost[tempTo]) {
					cnt++;
//					방문한 적이 없는 정점만 다시 방문해 봄
					if(!q.contains(tempTo)) {
						q.add(tempTo);	
					}
					
				}
			}
		}
		System.out.println(inCost[end]);
		System.out.println(cnt);


	}
	
	static class Edge{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
	}

}