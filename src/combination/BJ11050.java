package combination;

import java.util.Scanner;

public class BJ11050 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] D = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			D[i][0] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			D[i][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<N; j++) {
				D[i][j] = D[i-1][j-1] + D[i-1][j];
			}
		}
		System.out.println(D[N][K]);
	}
}