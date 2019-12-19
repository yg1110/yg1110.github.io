import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 1941 소문난 칠공주
public class Main_BOJ_1941_2 {
	static char[][] data = new char[5][5];
	static boolean[] v = new boolean[25]; // 방문체크
	//	4방위 위치 변위값
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	//	결과값 구현
	static int result = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = null;
		for(int i = 0 ; i < 5; i++) {
			s = sc.next();
			for(int j = 0 ; j < 5; j++) {
				data[i][j] = s.charAt(j);
			}
		}
		//		2차원 고정 배열을 1차원 숫자로 접근하기
		//		모든 정점에서 주변으로 이다솜파를 만들 수 있는지 검사하기
		for(int i = 0 ; i < 25; i++) {
			dfs(i,1, 0);
		}		
		System.out.println(result);
	}
	//	cnt:선택 인원수, scnt : 이다솜파 인원수
	static void dfs(int num, int cnt, int scnt) {
		if(data[num / 5][num % 5] =='S') {
			scnt += 1;
		}
		v[num] = true;
		//		7명이 선택되었으면
		if(cnt == 7) {//이다솜파가 4명 이상이면
			if(scnt >= 4) {//전체가 연결되어 있나 파악해서
				if(solve(num/5, num%5)) {//경우의 수 1 증가
					result += 1;
				}
			}
//			백트래킹
			v[num] = false;
			return;
		}
		for(int i = num+1 ; i < 25; i++) {
			if(!v[i]) {
				dfs(i, cnt + 1, scnt);
			}
		}
		//		백트래킹
		v[num] = false;
	}
	private static boolean solve(int y, int x) {
		boolean[][] v1 = new boolean[5][5];
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x,y));
		v1[y][x] = true;
		int vCnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			//			4방위 탐색
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				//				경계 범위 벗어나면 무시
				if(nx < 0 || nx >=5 || ny < 0 || ny >= 5) {
					continue;
				}
				//				이미 방문한 정점은 무시
				if(v1[ny][nx]) {
					continue;
				}
				//				선택된 인원이 아니면 무시
				if(!v[ny*5 + nx]) {
					continue;
				}
				//				새로운 방문체크 해주고 큐에 삽입
				v1[ny][nx] = true;
				q.offer(new Point(nx, ny));
				//				연결된 카운트 갯수 추가
				vCnt++;
			}
		}
		return vCnt == 7 ? true : false;
	}
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
