import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 2623 음악프로그램

public class Main_BOJ_2623 {
	static int N, M; // N-> 가수, M-> pd 수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}
		int[] inDegree = new int[N+1];
//		입력처리하는 부분
		int cur, num;
		
		int from, to;
		for(int i = 0 ; i < M ; i++) {
			int cnt = sc.nextInt();
			from = sc.nextInt();
			for(int c = 0 ; c < cnt-1 ; c++) {
				to = sc.nextInt();
				list[from].add(to);
				inDegree[to]++;
				from = to;
			}			
		}
		sc.close();
//		process 1
//		진입차수가 0일 것을 모두 q에 넣는다
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i< inDegree.length; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
		}
//		처음부터 진입차수가 0인 것이 없으면 바로 0을 출력하고 종료
		if(q.size() == 0) {
			System.out.println(0);
			return;
		}
//		큐에 있는 것을 하나씩 빼서 출력 리스트에 추가시키고, 그것과 연결된
//		진입차수를 1 감소 시키고 감소시킨 노드의 진입차수가0이면 큐를 삽입
//		큐가 빌때까지
		ArrayList<Integer> result = new ArrayList<>();
		while(!q.isEmpty()) {
			cur = q.poll();
			result.add(cur);
			for(int i = 0; i < list[cur].size(); i++) {
				inDegree[list[cur].get(i)]--;
				if(inDegree[list[cur].get(i)] == 0) {
					q.add(list[cur].get(i));
				}
			}			
		}
		
		if(result.size() != N) {
			System.out.println(0);
			return;
		}
		for(Integer n : result) {
			System.out.println(n);
		}
//		큐가 비어있고, 전체 리스트가 출력되지 않았다면 입력되는 것이 없고
//		내부 사이클이 존재한다고 할 수 있음으로 0 출력하고 종료
//		그렇지 않다면 리스트 출력


	}

}
