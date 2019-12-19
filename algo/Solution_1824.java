import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA 1824. 혁진이의 프로그램 검증
public class Solution_1824 {

	static int r,c;
	static char[][] map;
	static boolean canFinish; 
	static boolean[][][][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Job{
		int r,c,dir,mem;
		public Job(int r, int c, int dir, int mem) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.mem = mem;
		}		
	}
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input1824.txt"));
		BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(reader.readLine().trim());
		StringTokenizer st ;
		String s;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(reader.readLine()," ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new char[r][c];
			canFinish = false;			
			visited = new boolean[r][c][4][16];
			boolean isFinish = false;
			for(int i = 0; i < r; i++) {
				s = reader.readLine().trim();				
				if(s.contains("@")) {
					canFinish = true;
				}
				map[i] = s.toCharArray();
			}
			if(canFinish) {
				Queue<Job> q = new LinkedList<Job>();
				q.offer(new Job(0,0,0,0));
				Job job;
				int nr,nc,ndir,nmem;
				loop:while(!q.isEmpty()) {
					job = q.poll();
					nr = job.r;
					nc = job.c;
					ndir = job.dir;
					nmem = job.mem;
//					System.out.println(job + " r : " + r + ", c : " + c);
					if(visited[nr][nc][ndir][nmem]) {continue;}
					visited[nr][nc][ndir][nmem] = true;
					switch(map[nr][nc]) {
					case '@':
						isFinish = true;						
						break loop;
					case '<':
						ndir = 1;
						break;
					case '>':
						ndir = 0;
						break;
					case '^':
						ndir = 2;
						break;
					case 'v':
						ndir = 3;
						break;
					case '_':
						ndir = nmem==0?0:1;
						break;
					case '|':
						ndir = nmem==0?3:2;
						break;
					case '+':
						nmem = nmem==15?0:nmem+1;
						break;
					case '-':
						nmem = nmem==0?15:nmem-1;
						break;
					case '.':case '?':
						break;
					default:
						nmem = map[nr][nc]-'0';
						break;
					}
					if(map[nr][nc] == '?') {
						for(int i = 0 ; i < 4; i++) {
							int nnr = nr + dy[i];
							int nnc = nc + dx[i];
							nnr = nnr == r?0:(nnr == -1? r-1:nnr);
							nnc = nnc == c?0:(nnc == -1? c-1:nnc);
							
							Job job2 = new Job(nnr,nnc, i,nmem);
							q.offer(job2);
						}
					}else {
						int nnr = nr +dy[ndir];
						int nnc = nc +dx[ndir];
						nnr = nnr == r?0:(nnr == -1? r-1:nnr);
						nnc = nnc == c?0:(nnc == -1? c-1:nnc);
						Job job1 = new Job(nnr,nnc, ndir,nmem);
						q.offer(job1);
					}
				}
				
			}
			System.out.println("#"+t + " " +(isFinish?"YES":"NO"));
		}
	}
}