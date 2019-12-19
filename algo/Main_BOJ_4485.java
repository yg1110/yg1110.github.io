import java.util.PriorityQueue;
import java.util.Scanner;

//백준 : 4485 녹색 옷 입은 애가 젤다지?
public class Main_BOJ_4485 {
	static int N;
	static int[][] map;
	static int[][] dist;
	static int result;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int idx = 0;
		while(true) {
			N = sc.nextInt();
			if(N == 0) {
				break;				
			}
			map = new int[N][N];
			dist = new int[N][N];
			result = 0;
			idx++;
			for(int i = 0 ;i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					map[i][j] = sc.nextInt();
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			result = solve();
			System.out.println("Problem " + idx + ": " + result);
		}
	}
	static int solve() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(0,0,map[0][0]));
		dist[0][0] = map[0][0];
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(dist[node.y][node.x] < node.cost) {
				continue;
			}
			for(int i = 0 ; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if(nx < 0 || nx >= N || ny <0 || ny >= N) {
					continue;
				}
				if(dist[ny][nx] > dist[node.y][node.x] + map[ny][nx]) {
					dist[ny][nx] =  dist[node.y][node.x] + map[ny][nx];
					q.offer(new Node(nx,ny, map[ny][nx]));
				}
			}
			
		}
		return dist[N-1][N-1];
	}
	
	static class Node implements Comparable<Node>{
		int x, y;
		int cost;
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}
		
	}

}
