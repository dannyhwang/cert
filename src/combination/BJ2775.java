package combination;

import java.util.Scanner;

/**
 * 백준 2775 
 * Doit 078
 */
public class BJ2775 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[][] D = new int[15][15];
		for(int i=1; i<15; i++) {
			D[0][i] = i;
		}
		
		for(int i=1; i<15; i++) {
			for(int j=1; j<15; j++) {
				D[i][j] = D[i][j-1] + D[i-1][j];
			}
		}
		
		for(int i=0; i<T; i++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			System.out.println(D[k][n]);
		}
	}
}