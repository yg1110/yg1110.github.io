package noname;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
	static int[] dj;
	static List<String> friend;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());	// ���� ����
		for (int i = 0; i < M; i++) {
			int F = Integer.parseInt(br.readLine());	// ģ�� ���� ��
			String[][] link = new String[F][2];	// ���� ����
			Set<String> member = new HashSet<>();	// ��ü ���
			int length = 0;
			int[] plus = new int[F];
			for (int j = 0; j < F; j++) {
				st = new StringTokenizer(br.readLine());
				link[j][0] = st.nextToken();
				member.add(link[j][0]);
				link[j][1] = st.nextToken();
				member.add(link[j][1]);
				plus[j] = member.size() - length;	// �� �� �þ��
				length = member.size();
			}
			
			friend = new ArrayList<>(member);
			dj = new int[friend.size()];	// ���� �� �ִ� �ִ� ��� ���� F+1
			for (int j = 1; j < dj.length; j++)
				dj[j] = j;	// �Ҽӵ� ����
			int p = -1;
			for (int j = 0; j < F; j++) {
				switch(plus[j]) {
				case 2:	// ���ο� ����
					union(p+1, p+2);	p += 2;
					break;
				case 1:	// ���� ���տ� �� ��� �߰�
					int temp = search(link[j][0], p);
					if(temp < 0)	temp = search(link[j][1], p);
					union(temp, p+1);	p++;
					break;
				case 0:	// ���� ���� ��ġ��
					int t1 = search(link[j][0], p);
					int t2 = search(link[j][1], p);
					union(t1, t2);
					break;
				}
				
				int count = 1;
				for (int k = 0; k < p; k++) {
					if(find(k) == find(p))	count++;
				}
				sb.append(count + "\n");
			}
		}
		System.out.println(sb);
	}
	
	static int find(int x) {
		if(dj[x] == x)	return x;
		return dj[x] = find(dj[x]);
	}
	static void union(int x, int y) {
		if(find(x) != find(y))
			dj[find(y)] = find(x);
	}
	static int search(String s, int p) {
		for (int i = 0; i <= p; i++) {
			if(s.equals(friend.get(i)))
				return i;
		}
		return -1;	// �� ã����
	}
}