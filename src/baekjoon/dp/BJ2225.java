package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2225 {
	public static int N, K;
	public static long[][] dp;
	
	public static void main(String[] args) {
		init();
		Arrays.fill(dp[1], 1);
		for(int i=1; i<=K; i++)
			dp[i][0] = 1;
		
		for(int i=2; i<=K; i++)
			for(int j=1; j<=N; j++)
				dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000000;
		
		System.out.println(dp[K][N]%1000000000);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new long[K+1][N+1];
		for(int i=0; i<=K; i++)
			Arrays.fill(dp[i], 0);
		sc.close();
	}
}


/*
백준 합분해(2225)

문제
0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.

덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 
또한 한 개의 수를 여러 번 쓸 수도 있다.

입력
첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.

출력
첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.

예제 입력 1 
20 2
예제 출력 1 
21
예제 입력 2 
6 4
예제 출력 2 
84
*/
