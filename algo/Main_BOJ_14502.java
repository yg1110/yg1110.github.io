import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 14502 연구소 DFS, BFS 시뮬레이션
public class Main_BOJ_14502 {

	static int N; // 세로
	static int M; // 가로
	static int[][] map1;// 원본
	static int[][] map2;// 실행본
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map1 = new int[N][M];
		map2 = new int[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				map1[i][j] =  map2[i][j] = sc.nextInt();
			}
		}
		sc.close();
//		모든 빈 정점에서 벽 세워 보기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map1[i][j] == 0) { //원본에서 현재 정점이 빈 공간이면 벽 세우고 재귀 호출
					map2[i][j] = 1;
					dfs(1);
					map2[i][j] = 0; // 백트래킹
				}
			}
		}
		
		
		System.out.println(result);
		
	}
	static void dfs(int cnt) {
//		종료 조건 cnt = 3 (벽을 3개 세웠으면)
		if(cnt == 3) {
//			바이러스 퍼트리기
			bfs();
			return;
		}
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map2[i][j] == 0) {
					map2[i][j]  = 1;
					dfs(cnt + 1);
					map2[i][j]  = 0; //백트래킹
				}
			}
		}
	}
	private static void bfs() {
		int[][] vMap = new int[N][M]; // 바이러스맵
//		기존 맵 복사 하기(Deep Copy)
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				vMap[i][j] = map2[i][j];
			}
		}
//		바이러스들 큐에 삽입해 놓기
		Queue<Point> q = new LinkedList<Point>();
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(vMap[i][j] == 2) {
					q.offer(new Point(j,i));
				}
			}
		}
//		바이러스 퍼트리기
		Point p;
		int nx,ny;
		while(!q.isEmpty()) {
			p = q.poll();			
			for(int i = 0 ; i < 4; i++) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if(nx < 0 || nx >= M || ny < 0 || ny >= N) { // 영역 벗어나면 무시하기
					continue;
				}
				if(vMap[ny][nx] != 0) { // 안정영역 아니면 무시하기
					continue;
				}
				vMap[ny][nx] = 2; //바이러스 퍼트리기
				q.offer(new Point(nx, ny));
				
			}
		}
//		안전 영역 갯수 세기
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(vMap[i][j] == 0) {
					cnt++;
				}
			}
		}		
		result = Math.max(result, cnt);	
		
	}
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}				
	}
}
