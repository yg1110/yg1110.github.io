package noname;
import java.util.Scanner;
public class Main_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int v = sc.nextInt();	// ���� ����
			int[] color = new int[v+1];	// ������ ���� ����
			int e = sc.nextInt();	// ���� ����
			boolean check = true;
			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if(color[a] == 0) {	// �պκ��� ������ ��
					if(color[b] == 0 || color[b] == 2)
						color[a] = 1;	// ���� �ʱⰪ ����
					else if(color[b] == 1)	color[a] = 2;
				}
				if(color[b] == 0) {	// �޺κ��� ������ ��
					if(color[a] == 1)	color[b] = 2;
					else if(color[a] == 2)	color[b] = 1;
				}
				if(check && color[a] == color[b])	// ���� ���� �κ��̸� ��
					check = false;
			}
			if(check)	System.out.println("YES");
			else	System.out.println("NO");
		}
	}
}
