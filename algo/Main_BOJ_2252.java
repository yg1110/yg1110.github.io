import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_2252 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N, M;
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] inDegree = new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i = 0; i <=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		int x, y;
		for(int i = 0; i < M; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			list[x].add(y);
			inDegree[y]++;  //진입차수 증가
		}
//		위상 정렬하기
		Queue<Integer> q = new LinkedList<>();		
//		진입차수가 0 인 것을 모두 큐에 삽입한다
		for(int i = 1; i <=N; i++) {
			if(inDegree[i] == 0) {
				q.add(i);
			}
		}
//		큐가 빌때까지  자신이 가르키고 있는 좌표들을 방문하여 indegree값을 -1 해주고 만약 0이라면 큐에 넣어준다.
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(!q.isEmpty()) {
			int current = q.poll();
			result.add(current);
			for(int i = 0 ; i < list[current].size(); i++) {
				int temp = list[current].get(i);
				inDegree[temp]--;
				if(inDegree[temp] ==0) {
					q.add(temp);
				}
			}
		}
		for(Integer i : result) {
			System.out.print(i + " ");
		}
		System.out.println();

	}

}
