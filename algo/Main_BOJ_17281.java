import java.util.Scanner;
// 백준 17281 야구게임
public class Main_BOJ_17281 {
	static int N ; // 이닝수
	static int[][] data; // 이닝별 데이터

	//	순열 생성변수
	static boolean[] v = new boolean[9+1];
	static int[] player = new int[9+1];

	//	출력값 초기화
	static int result = -1; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		//		이닝별 타자별 타수 상황 입력
		data = new int[N][9+1];
		for(int i = 0 ; i < N; i++) {
			for(int j = 1 ; j <= 9; j++) {
				data[i][j] = sc.nextInt();
			}			
		}
		sc.close();
//		4번에 1번 타자 정보로 고정한 후 나머지 모든 타자를 순열로 생성하기
		player[4] = 1;
		perm(1);

		System.out.println(result);

	}
	static void perm(int idx) {
//		4번엔 1번 선수 고정
		if(idx == 4) {
			perm(idx + 1);
			return;
		}
//		최종선수까지 순열구했으면
		if(idx == 10) {
			int score = play();
			result = Math.max(result, score);
			return;
		}
//		순열 구하기
		for(int i = 2; i < 10; i++) {
			if(v[i]) {
				continue;
			}
			player[idx] = i;
			v[i] = true;
			perm(idx+1);
			v[i] = false;
		}
	}

	static int play() {
		int score = 0;
		int out = 0;
		boolean[] roo = new boolean[4];
		int num = 1; //타자 번호
		for(int i = 0 ; i < N; i++) { // N 이닝만큼 게임하기
//			이닝 초기화
			out = 0;
			roo = new boolean[4];
			
//			3아웃까지 반복하기
			loop :while(true) { 				
//				현재 번호의 안타 상황
				int state = data[i][player[num]];
//				다음 번호 얻어오기
				if(num == 9) {
					num = 1;
				}else {
					num++;
				}
//				각 안타 상황에 따라 진루 및 점수 계산하기
				switch(state) {
				case 0 : //  아웃
					out++;
					if(out == 3) {
						break loop;
					}
					break;
				case 1 :  // 1 루타
					if(roo[3]) {
						score++;
						roo[3] = false;
					}
					for(int r = 3; r > 1; r--) {
						roo[r] = roo[r-1];
					}
					roo[1] = true;
					break;
				case 2 : // 2 루타
					if(roo[3]) {
						score++;
						roo[3] = false;
					}
					if(roo[2]) {
						score++;
						roo[2] = false;
					}
					if(roo[1]) {
						roo[3] = true;
						roo[1] = false;
					}
					roo[2] = true;
					break;
				case 3 : // 3 루타
					if(roo[3]) {
						score++;
						roo[3] = false;
					}
					if(roo[2]) {
						score++;
						roo[2] = false;
					}
					if(roo[1]) {
						score++;
						roo[1] = false;
					}
					roo[3] = true;
					break;
				case 4 : // 4 루타(홈런)
					score++;
					for(int r = 1; r <=3; r++) {
						if(roo[r]) {
							score++;
							roo[r] = false;
						}
					}
					break;
				}
			}
		}
		return score;
	}
}