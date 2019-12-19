import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_1707 {

	static  int V; // 정점의 갯수
	static int E ; // 간선의 갯수
	static ArrayList<Integer> list[] = null;
//	체크 배열
	
	static int check[] = null;
//	Tip: 두수의 연산과 형변환 쉽게 1과 -1로 타입 결정
	static final int TYPE1 = 1;
	static final int TYPE2 = -1;
	
	static boolean result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		for(int k = 0; k < K; k++) {
			V = sc.nextInt();
			E = sc.nextInt();
			list = new ArrayList[V+1];
			for(int i = 0 ; i < list.length; i++) {
				list[i] = new ArrayList<Integer>();
			}
			int a, b;
			for(int i = 0; i < E; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				list[a].add(b);
				list[b].add(a);
			}
//			파단할 체크 배열 선언
			check= new int[V+1];
//			result 값 초기화
			result = true;
//			모든 정점에서 시작하여 판단 필요(연결되지 앟은 그래프인 경우도 있을 수 있음)
			for(int i = 1; i < V + 1; i++) {
//				하나의 정점이라도 이분 그래드팍 아니면 전체 그래프는 이분 그래프가 아니다
				if(!result) {
					break;
				}
//				이미 판단된 정점은 배제하고 아니면 판단 함수 호출한다.
				if(check[i] == 0) {
//					dfs(i,TYPE1);
					bfs(i, TYPE1);
				}
				
			}
			
			System.out.println(result ? "YES" : "NO");
		}	
		sc.close();
	}
	static void bfs(int idx, int type) {
		Queue<Integer> q = new LinkedList<Integer>();
//		첫번째 정점의 임의틔 타입을 넣어준다.
		check[idx] = type;
		q.offer(idx);
		int cur;
		while(!q.isEmpty()) {
//			정점을 뽑고 정점과 연결된 나머지 정점을 판단한다.			
			cur = q.poll();
			for(int v : list[cur]) {
//				방문한 적이 없는 정점이면 현재 정점의 반대 Type을 넣어주고,큐에 삽입 후 다음 정점으로 넘어간다.
				if(check[v] == 0) {
					check[v] = -check[cur];
					q.offer(v);
					continue;
				}
//				인접한 정점이 같은 값이면 이분 그래프가 아님으로 종료한다				
				if(check[v] + check[cur] != 0) {
					result = false;
					return;
				}
			}
		}
	}
	static void dfs(int idx, int type) {
//		현재 위치를 기존 타입으로 칠한다.
		check[idx] = type;
//		현재 정점과 연결된 나머지 정점을 판단한다
		for(int v : list[idx]) {
//			연결된 정점이 기존 타입과 같은 것으로 결정되어 있으면 이분 그래프가 아님으로 멈춤
			if(check[v] == type) {
				result = false;
				return;
			}
//			방문하지 않았던 정점이면 다른 타입으로 재귀호출한다
			if(check[v] == 0) {
				dfs(v, -type); //
			}
		}		
	}
}
