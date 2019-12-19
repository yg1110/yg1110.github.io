import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//백준 11559 Puyo Puyo  BFS형 시뮬레이션
public class Main_BOJ_11559 {
	static char[][] map = new char[12][6];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
//	없애야 할 Point 누적 리스트 관리
	static ArrayList<Point> list = new ArrayList<Point>();
//	출력값 초기화
	static int result = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s;
		for(int i = 0; i < 12; i++) {
			s = sc.next();
			for(int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		sc.close();
//		시뮬레이션		
		boolean finish = false;
		while(!finish) {
//			종료 할 수 있는 조건을 true로 설정한다.
			finish = true;
//			아래로 이동시키기
			down();
//			모든 정점에서 4개 이상 연결된 것들을 찾아서 지운다
//			지운게 한번 이상이면 다시 떨어뜨려서 반복한다.
			for(int i = 0 ; i < 12; i++) {
				for(int j = 0 ; j < 6; j++) {
					if(map[i][j] =='.') { // 공백이면 무시
						continue;
					}
//					bfs로 같은 값으로 연결된 정점 갯수를 별도의 공간에 모은다.
					Queue<Point> q = new LinkedList<Point>();
					boolean[][] v = new boolean[12][6];
					int cnt = 0;
					q.offer(new Point(j,i));
					v[i][j] = true;
					Point p;
					list.clear();
					int nx, ny;
					while(!q.isEmpty()) {
						p = q.poll();
//						연결된 갯수 누적하기
						cnt++;
						list.add(new Point(p.x, p.y));
						for(int d = 0 ; d < 4; d++) {
							nx = p.x + dx[d];
							ny = p.y + dy[d];
//							범위 벗어나면 무시
							if(nx < 0 || nx >= 6 || ny < 0 || ny >= 12) {
								continue;
							}
//							이미 방문한 정점 무시
							if(v[ny][nx]) {
								continue;
							}
//							서로 다른 색상이면 무시
							if(map[p.y][p.x] != map[ny][nx]) {
								continue;
							}
							q.offer(new Point(nx, ny));
							v[ny][nx] = true;
						}
					}
//					같은 색상으로 연결된것이 4 이상이면 그 위치를 지운다.
//					계속 반복 할 수 있도록 finish 변수를 false로 바꾼다.
					if(cnt >= 4) {
						for(Point p1 : list) {
							map[p1.y][p1.x] = '.';
						}
						finish = false;
					}
				}
			}
			result++;
		}
		
		System.out.println(result-1);  // result값이 무조건 1증가가 발생함으로 1 감소해서 출력
		

	}
	private static void down() {
//		아래쪽 부터 이동하기
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] == '.') { // . 이면 무시
					continue;
				}
//				아래로만 떨구기
				int ny = i;
				char pp = map[i][j];
				map[i][j] = '.'; // 현재 위치 빈 공간으로 변경
				while(true) {
					if(ny>=11) {// 땅에 떨어졌으면
						break;
					}
					if(map[ny+1][j] !='.') { // 빈공간이 아니면 무시
						break;
					}
					
					ny++; //아래로 한칸 이동
				}				
				map[ny][j] = pp;
			}
		}
		
	}
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

}
