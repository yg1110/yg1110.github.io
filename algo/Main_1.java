package noname;
import java.util.Scanner;
public class Main_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int v = sc.nextInt();	// 정점 개수
			int[] color = new int[v+1];	// 정점의 진영 구분
			int e = sc.nextInt();	// 간선 개수
			boolean check = true;
			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if(color[a] == 0) {	// 앞부분이 미정일 때
					if(color[b] == 0 || color[b] == 2)
						color[a] = 1;	// 완전 초기값 포함
					else if(color[b] == 1)	color[a] = 2;
				}
				if(color[b] == 0) {	// 뒷부분이 미정일 때
					if(color[a] == 1)	color[b] = 2;
					else if(color[a] == 2)	color[b] = 1;
				}
				if(check && color[a] == color[b])	// 서로 같은 부분이면 끝
					check = false;
			}
			if(check)	System.out.println("YES");
			else	System.out.println("NO");
		}
	}
}
