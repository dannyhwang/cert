package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class BJ10844 {
	public static int N;
	public static long[][] dp;
	
	public static void main(String[] args) {
		init();
		Arrays.fill(dp[0], 1);
		dp[0][0] = 0;
		
		for(int i=1; i<N; i++)
			for(int j=0; j<=9; j++) {
				if(j==0) dp[i][j] = dp[i-1][j+1] % 1000000000;
				else if(j==9) dp[i][j] = dp[i-1][j-1] % 1000000000;
				else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
			}
		
		System.out.println(Arrays.stream(dp[N-1]).sum() % 1000000000);
	}
	
	public static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new long[N][10];
		sc.close();
	}
}


/*
백준 쉬운 계단 수(10844)

문제
45656이란 수를 보자.

이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.

N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.

입력
첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

출력
첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

예제 입력 1 
1
예제 출력 1 
9
예제 입력 2 
2
예제 출력 2 
17
*/