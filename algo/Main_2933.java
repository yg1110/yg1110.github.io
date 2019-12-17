import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_2933 {

	static int R,C;
	static int N;
	static char[][] map;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		map = new char [R][C];

		for (int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}

		N = sc.nextInt();
		

		for (int i = 0; i < N; i++) {
			int L = R-sc.nextInt();

			// 미네랄 파괴
			broke(L,i);
			// 클러스터 내리
			solve();
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static void solve() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		ArrayList<Node> cluster = new ArrayList<>();

		// 땅에 붙어있는 클러스터 체크 
		for (int j = 0; j < C; j++) {
			if(map[R-1][j] =='.' || visited[R-1][j])
				continue;

			visited[R-1][j] = true;
			q.add(new Node(R-1, j));

			while(!q.isEmpty()) {
				Node cur = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];

					if(!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] =='.')
						continue;

					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				}
			}
		}

		// 공중에 떠 있는 클러스터 찾기. (땅에서부터 시작해서 방문하지 못한 미네랄을 검색)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visited[i][j] && map[i][j] == 'x') {
					map[i][j] = '.';
					cluster.add(new Node(i, j));
				}
			}
		}

		if(cluster.isEmpty()) {
			return ;
		}

		// 얼마나 내릴 수 있는지 체크.
		boolean down = true;
		while(down) {
			for(Node node : cluster) {
				int nx = node.x +1;
				int ny = node.y;

				if(!isRange(nx, ny) || map[nx][ny] == 'x') {
					down = false;
					break;
				}
			}
			if(down) {
				for(Node node : cluster) {
					node.x++;
				}
			}
		}

		// mark
		for(Node node : cluster) {
			map[node.x][node.y] = 'x';
		}

	}

	static boolean isRange(int x, int y) {
		if( x < 0 || x >= R || y < 0 || y >= C) return false;
		return true;
	}

	static void broke (int L, int i) {
//		미네랄 부수기 ( 왼쪽 오른쪽 방향에서 하나만 ) 
		if(i % 2 == 0) {
			for (int j = 0; j < C; j++) {
				if(map[L][j] == 'x') {
					map[L][j] = '.';
					break;
				}
			}    
		}
		else {
			for (int j = C-1; j >= 0; j--) {
				if(map[L][j] == 'x') {
					map[L][j] = '.';
					break;
				}
			}
		}
	}
	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}


