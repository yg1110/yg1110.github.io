import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
// 백준 15683번 감시 (DFS 시뮬레이션)
public class Main_BOJ_15683 {
	static int N; //세로
	static int M; //가로
	static int[][] map;
	static ArrayList<CCTV> list  = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new CCTV(j, i, map[i][j]));
				}
			}
		}
//		첫번째 시작위치와 기본 맴 정보 전달
		dfs(0, map);
		System.out.println(result);
	}
	static void dfs(int idx, int[][] prev) {
		int[][] v = new int[N][M];
//		모든 CCTV 다 판단해 본 경우
		if(idx == list.size()) {
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(prev[i]));
//			}
//			prev 배열에서 관리되지 않는 공간 카운트 하기
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(prev[i][j] == 0) {
						cnt++;
					}
				}
			}
//			최소값 구하기
			result = Math.min(result, cnt);
			return;
		}
		CCTV cctv = list.get(idx);
		int x = cctv.x;
		int y = cctv.y;
		switch(cctv.type) {
		case 1 : // 4방향 회전하면서 모두 검사
			for(int d = 0 ; d < 4; d++) {
//				기존 맵 하드복사하여 기본 맴 값으로 전송하기
				for(int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
//				CCTV 방범 체크해주기
				detect(v, x, y, d);
//				dfs 다음 CCTV로 재귀호출
				dfs(idx+1, v);
			}			
			break;
		case 2 : // 2방향 회전하면서 모두 검사
			for(int d = 0 ; d < 2; d++) {
				for(int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				detect(v, x, y, d);  	// 상하 
				detect(v, x, y, d+2);	// 좌우
				dfs(idx+1, v);
			}			
			break;		
		case 3 : // 4방향 회전하면서 모두 검사
			for(int d = 0 ; d < 4; d++) {
				for(int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				detect(v, x, y, d);
				detect(v, x, y, (d+1) %4);
				dfs(idx+1, v);
			}			
			break;		
		case 4 : // 4방향 회전하면서 모두 검사
			for(int d = 0 ; d < 4; d++) {
				for(int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				detect(v, x, y, d);
				detect(v, x, y, (d+1) %4);
				detect(v, x, y, (d+2) %4);
				dfs(idx+1, v);
			}			
			break;
		case 5 : // 4방향 회전하면서 모두 검사
			for(int d = 0 ; d < 4; d++) {
				for(int i = 0; i < N; i++) {
					v[i] = Arrays.copyOf(prev[i], M);
				}
				detect(v, x, y, 0);
				detect(v, x, y, 1);
				detect(v, x, y, 2);
				detect(v, x, y, 3);				
				dfs(idx+1, v);
			}			
			break;		
		}
		
	}
	static void detect(int[][] vMap, int x, int y, int dir) {
//		dir 0 : 좌, 1: 상, 2 : 우, 3 : 하  => 한방향으로 번호 나열
		switch(dir) {
		case 0 : //좌
			for(int i = x; i >= 0; i--) {				
				if(vMap[y][i] == 6) {
					break;
				}
				vMap[y][i] = 9;
			}
			break;
		case 1 :  //상
			for(int i = y; i >= 0; i--) {				
				if(vMap[i][x] == 6) {
					break;
				}
				vMap[i][x] = 9;
			}			
			break;		
		case 2 : //우
			for(int i = x; i < M; i++) {				
				if(vMap[y][i] == 6) {
					break;
				}
				vMap[y][i] = 9;
			}
			break;		
		case 3 : //하
			for(int i = y; i < N; i++) {				
				if(vMap[i][x] == 6) {
					break;
				}
				vMap[i][x] = 9;
			}			
			break;
		}
	}
	static class CCTV {
		int x,y;
		int type;
		public CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
	}

}
