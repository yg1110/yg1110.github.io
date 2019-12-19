import java.util.Scanner;
public class Main_BOJ_11723 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		StringBuilder result=new StringBuilder();
		int T=sc.nextInt();
		int S=0;
		int x;
		for(int i=0; i<T; i++){
			String s=sc.next();
			switch(s) {
			case "add" :
				x=sc.nextInt();
				S=S|(1<<x);
				break;
			case "remove" :
				x=sc.nextInt();
				S= S & ~(1<<x);
				break;
			case "check" :
				x = sc.nextInt();
				if((S&(1<<x))>0) {
					result.append("1\n");
				}else {
					result.append("0\n");
				}
				break;
			case "toggle" :
				x=sc.nextInt();
				S = S ^ (1<<x);
				break;
			case "all" :
				S=(1<<21) -1;
				break;
			default :
				S = 0;

			}
		}
		System.out.println(result);

	}

}
