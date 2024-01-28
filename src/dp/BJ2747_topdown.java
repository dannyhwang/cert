package dp;

import java.util.Scanner;

/**
 * 백준 2747 
 * TopDown
 */

public class BJ2747_topdown {
	
	static int[] D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		D = new int[46];
		D[0] = 0;
		D[1] = 1;
		
		fibo(n);
		System.out.println(D[n]);
	}
	
	public static int fibo(int n) {
		if(D[n] != 0 || n==0)
			return D[n];
		return D[n] = fibo(n-1) + fibo(n-2);
	}
}