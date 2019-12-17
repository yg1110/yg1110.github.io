import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 1600 말이 되고픈 원숭이
public class Main_BOJ_1600 {
	static int K, W, H;
	static int[][] map;
	static int[][][] dp; //이동 방문 체크 배열
	static int result = Integer.MAX_VALUE;
//	몽키 이동
	static int[] mx = {0,0,-1,1};
	static int[] my = {-1,1,0,0};
//	말 이동
	static int[] hx = {-2, -1, 1, 2, 2,  1, -1, -2};
    static int[] hy = {1,   2, 2, 1,-1, -2, -2, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][W];
//		말 치트키 적용횟수 메모이제이션 값
		dp = new int[K+1][H][W];
		for(int i = 0 ; i < H; i++) {
			for(int j = 0 ; j < W; j++) {
				map[i][j] = sc.nextInt();
//				메모이제이션 최대값으로 초기화
				for(int k = 0 ; k < K+1; k++) {
					dp[k][i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		bfs();
		
		System.out.println(result);
		sc.close();
	}
	static void bfs() {
		Queue<Monkey> q = new LinkedList<>();
		q.offer(new Monkey(0, 0, 0, 0));
		Monkey m;
		while(!q.isEmpty()) {
			m = q.poll();
//			기본적인 몽키 이동 실행
			moveMonkey(m, q);
			
			if(m.k < K) {	//말로 이동이 가능하면 말 이동 방법 실행
				moveHorse(m, q);
			}
		}
		//전체 실행 후 모든 치트키  사용 경우에 최소값 구하기
		for(int k = 0;  k < K + 1; k++) {
			result = Math.min(result, dp[k][H-1][W-1]);
		}
		result = result == Integer.MAX_VALUE? -1 : result;
	}
	private static void moveMonkey(Monkey m, Queue<Monkey> q) {
		int nx, ny;
		for(int i = 0 ; i < 4; i++) {
			nx = m.x + mx[i];
			ny = m.y + my[i];
			if(nx < 0 || nx >= W || ny < 0 || ny >= H) {
				continue;				
			}
			if(map[ny][nx] == 1 ) {
				continue;
			}
//			이동 횟수 증가
			int cnt = m.c + 1;
//			기존에 이동된 횟수보다 적으면 무시
			if(dp[m.k][ny][nx] <=  cnt) {
				continue;
			}
//			새로운 이동정보  큐에 삽입
			q.offer(new Monkey(nx, ny, m.k, cnt));
//			현재까지 이동횟수 메모이제이션
			dp[m.k][ny][nx] = cnt;
		}
		
	}
	private static void moveHorse(Monkey m, Queue<Monkey> q) {
		int nx, ny;
//		말 치트키 사용 횟수 증가
		int nK = m.k+1;
		for(int i = 0 ; i < 8; i++) {
			nx = m.x + hx[i];
			ny = m.y + hy[i];
			if(nx < 0 || nx >= W || ny < 0 || ny >= H) {
				continue;				
			}
			if(map[ny][nx] == 1 ) {
				continue;
			}
			int cnt = m.c + 1;			
			if(dp[nK][ny][nx] <=  cnt) {
				continue;
			}
			q.offer(new Monkey(nx, ny, nK, cnt));
			dp[nK][ny][nx] = cnt;
		}		
	}	
	static class Monkey {
		int x, y;
		int k; // 말로 이동횟수
		int c; // 이동횟수
		public Monkey(int x, int y, int k, int c) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.c = c;
		}		
	}
}
