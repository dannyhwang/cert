package combination;

import java.util.Scanner;

public class BJ1010 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int Q = sc.nextInt();
		int[][] D = new int[30][30];
		
		for(int i=1; i<30; i++) {
			D[i][i] = 1;
			D[i][1] = i;
		}
		
		for(int i=2; i<30; i++) {
			for(int j=2; j<i; j++) {
				D[i][j] = D[i-1][j-1] + D[i-1][j];
			}
		}
		
		for(int i=0; i<Q; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			System.out.println(D[M][N]);
		}
	}
}