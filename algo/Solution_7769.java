import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// SWEA 7699. 수지의 수지 맞는 여행 
public class Solution_7769 {
    static int TC, R, C;
    static char[][] map;
    static boolean[][] visited;
    static int result;
    static Set<Character> set; // 중복을 허용하지 않는 자료 구조를 이용함
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
     
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        TC = sc.nextInt();
         
        for(int tc=1; tc<=TC; tc++) {
            R = sc.nextInt();
            C = sc.nextInt();
             
            map = new char[R][];
            visited = new boolean[R][C];
             
            for(int i=0; i<R; i++) {
                map[i] = sc.next().toCharArray();
            }
             
            result = 0;
            set = new HashSet<>();
            set.add(map[0][0]);
            dfs(0,0,0);
             
            System.out.println("#"+tc+" "+result);
        }
    }
     
    static void dfs(int x, int y, int cnt) {
        set.add(map[y][x]);
//        visited[i][j] = true;
         
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
             
            if(ny<0 || ny>=R || nx < 0 || nx >= C) {
                continue;
            }
            if(set.contains(map[ny][nx])) {
            	continue;
            }
            dfs(nx,ny, cnt+1);
        }
         
        if(set.size()>result) {
            result = set.size();
        }
        set.remove(map[y][x]);
//        visited[i][j] = false;
//      if(cnt>result)
//          result = cnt+1;
        return;
    }
}