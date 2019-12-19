import java.util.Arrays;
import java.util.Scanner;

// 백준 17779 게리맨더링 2
public class Main_BOJ_17779 {
	static int N;
	static int[][] A;
	
	//5등분하기 위해서 마름모 모양 만들기	
	//모든 마름모조건을 검수하기 → 4중 포문
	//5번 영역찾기(가운데 영역) 표시
	//1,2,3,4,5번 영역별로 인구수 더하기
	
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				A[i][j] = sc.nextInt();
			}
		}
//		모든 정점에서 모든 마름모 가능한 영역으로 마믈모 생성 → 4중 포문
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N;j++) {
				for(int k = 1; k <= N-j; k++) { // 마름모 만들기 위해서 1보다는 크거나 같고,  N-j 보다는 작거나 같아야함
					for(int l = 1; l < N-j; l++) {
//						 마름모를 만들 수 있은 값이면 마름모를 만듬
						if(j - k >= 0 && j + k < N && i + k + l < N) {
							result = Math.min( result, solve(i,j,k,l) );
						}
					}
				}
			}
		}
		System.out.println(result);
	}

	private static int solve(int r, int c, int d1, int d2) {
//		d1 + d2 만큼의 세로 영역의 가운데에 제5영역을 만들어 둔다
		boolean[][] check5 = new boolean[N][N];
		int nr = r;
		int nc = c;
		int end = 1; // 최소값 1 설정(마름모의 꼭지점 칠하기)
		for(int i = 0; i <= d1 + d2; i++) {
//			각 행마다 5영역의 갯수 만큼 true 값 설정
//			System.out.println(nr + ", " + nc + ", ");
			for(int j = 0; j < end; j++) {
				check5[nr][nc+j] = true;
			}
			nr++;  // 다음행으로 진행
			if(i < d1) { //왼쪽으로 갈수 있는 만큼 아직 못갔으면
				nc--;
			}else {      //왼쪽으로 모두 갔으면 다시 꺽어져서 증가
				nc++;
			}
			if(i < d1 && i < d2) { // 왼쪽도 증가하고 오른쪽도 증가하면 end값은 2씩 증가    /  \
				end += 2;
			}else if(i >= d1 && i >= d2) { // 왼쪽은 튕겨 나오고 오른쪽은 감소하면 2씩 감소  \  /
				end -=2;
			}/*else{  //  마름모가 같은 방향으로 설정되면 end값 변경 없음   \   \
				continue;
			}*/
		}
//		구역별 합계 처리
		int[] cnt = new int[5];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
//				1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//				2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//				3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//				4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
//				1구역이면 1구역 영역에 값 누적하기
				if(i >= 0 && i < r + d1 && j >= 0 && j <= c && !check5[i][j]) { 
					cnt[0] += A[i][j];
				}else if(i >= 0 && i <= r + d2 && j > c && j < N && !check5[i][j]) { // 2구역이면 2구역 영역에 값 누적하기
					cnt[1] += A[i][j];
				}else if(i >= r + d1 && i < N && j >= 0 && j < c - d1 + d2 && !check5[i][j]) { // 3구역이면 3구역 영역에 값 누적하기
					cnt[2] += A[i][j];
				}else if(i > r + d2 && i < N && j >= c - d1 + d2 && j < N && !check5[i][j] ) { // 4구역이면 4구역 영역에 값 누적하기
					cnt[3] += A[i][j];
				}else {// 5구역이면 5구역 영역에 값 누적하기 
					cnt[4] += A[i][j];
				}
			}
		}
//		구역별 값 정렬
		Arrays.sort(cnt);
//		구역별 최대값-최소값을 반환
		return cnt[4] - cnt[0];
	}
	

}
