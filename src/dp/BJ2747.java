package dp;

import java.util.Scanner;

/**
 * 백준 2747 
 * BottomUp
 */
public class BJ2747 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] D = new int[46];
		D[0] = 0;
		D[1] = 1;
		
		for(int i=2; i<=45; i++) {
			D[i] = D[i-1] + D[i-2];
		}
		
		System.out.println(D[n]);
	}
}